package com.poppy;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.gitlab4j.api.models.PipelineSchedule;
import org.jetbrains.annotations.NotNull;

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

    public String summary() {
       return schedule.getDescription() + " - " + getStatus(schedule) + getTestReportSummary() + "("+scheduleId.getTestResultUrl()+")";
    }

    private String getTestReportSummary() {
        return testReport != null ?  " - " + testReport.summary()  : "";
    }

    private String getStatus(PipelineSchedule schedule) {
        return schedule.getLastPipeline().getStatus().toString();
    }

}
