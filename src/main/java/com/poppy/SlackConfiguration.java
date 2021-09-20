package com.poppy;

import com.slack.api.bolt.App;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SlackConfiguration {

    @Bean
    App app(ChuckNorisService chuckNorisService) {
        var app = new App();
        app.command("/alim", (req, ctx) -> ctx.ack(chuckNorisService.getRandomJoke()));
        return app;
    }
}
