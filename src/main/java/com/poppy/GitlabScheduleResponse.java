package com.poppy;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gitlab4j.api.models.PipelineSchedule;

import java.util.Optional;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class GitlabScheduleResponse {

    private final GitlabScheduleIdentifier scheduleId;
    private final PipelineSchedule schedule;
    private final TestReport testReport;

    public Optional<TestReport> getTestReport() {
        return Optional.of(testReport);
    }
}
