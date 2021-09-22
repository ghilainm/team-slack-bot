package com.poppy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.PipelineSchedule;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitlabService {

    private final GitLabApi gitLabApi;
    private final GitlabOpenApiClient gitLabApiClient;
    private final GitlabProperties gitlabProperties;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public GitlabScheduleResponse getSchedule(GitlabScheduleInfo gitlabScheduleInfo) {
        PipelineSchedule pipelineSchedule = gitLabApi.getPipelineApi().getPipelineSchedule(gitlabScheduleInfo.getProjectId(), gitlabScheduleInfo.getScheduleId());
        Optional<TestReport> testReport = getTestReport(gitlabScheduleInfo.getProjectId(), pipelineSchedule.getLastPipeline().getId());
        return GitlabScheduleResponse.builder()
                .schedule(pipelineSchedule)
                .scheduleId(gitlabScheduleInfo)
                .testReport(testReport.orElse(null))
                .build();
    }

    public List<GitlabScheduleResponse> getSchedulesForContext(String contextKey) {
        if (!gitlabProperties.getSchedules().containsKey(contextKey)) {
            return Collections.emptyList();
        }
        List<GitlabScheduleInfo> scheduleConfiguration = gitlabProperties.getSchedules().get(contextKey);
        return scheduleConfiguration.stream()
                .map(this::getSchedule)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Optional<TestReport> getTestReport(String projectId, int pipelineId) {
        Response response = gitLabApiClient.get(null, "projects", projectId, "pipelines", pipelineId, "test_report_summary");
        if (response.getStatus() != 200) {
            return Optional.empty();
        } else {
            // {"total":{"time":288.217,"count":14,"success":12,"failed":2,"skipped":0,"error":0,"suite_error":null},"test_suites":[{"name":"gradle-system-test","total_time":288.217,"total_count":14,"success_count":12,"failed_count":2,"skipped_count":0,"error_count":0,"build_ids":[500979],"suite_error":null}]}
            String responseText = response.readEntity(String.class);
            JsonNode total = objectMapper.readTree(responseText).get("total");
            return Optional.ofNullable(objectMapper.treeToValue(total, TestReport.class));
        }
    }
}
