package com.poppy;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import org.gitlab4j.api.models.PipelineSchedule;

import java.util.List;
import java.util.stream.Collectors;

public class GitlabScheduleSlashResponse extends SlashCommandResponse {

    public GitlabScheduleSlashResponse(SlashCommandContext ctx, List<GitlabScheduleResponse> schedules) {
        if (schedules == null || schedules.isEmpty()) {
            this.setText("No gitlab schedule to be checked configured for context "+ctx.getChannelId());
        } else {
            this.setText(schedules.stream()
                    .map(response -> response.getScheduleId().getAlias() + " - " + getStatus(response.getSchedule()) + response.getTestReport().map(testReport -> " - " + testReport.summary()).orElse(""))
                    .collect(Collectors.joining("\n")));
        }
        this.setResponseType("in_channel");
    }

    private String getStatus(PipelineSchedule schedule) {
        return schedule.getLastPipeline().getStatus().toString();
    }

}
