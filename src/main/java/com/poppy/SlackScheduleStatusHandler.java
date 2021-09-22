package com.poppy;

import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SlackScheduleStatusHandler {

    private final GitlabService gitlabService;

    @SneakyThrows
    @Async
    public void answerToScheduleStatus(SlashCommandRequest req, SlashCommandContext ctx) {
        ctx.respond(new GitlabScheduleSlashResponse(ctx, gitlabService.getSchedulesForContext(ctx.getChannelId())));
    }
}
