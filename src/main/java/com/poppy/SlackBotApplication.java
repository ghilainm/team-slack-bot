package com.poppy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ServletComponentScan
@SpringBootApplication
@Slf4j
public class SlackBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlackBotApplication.class, args);
    }
}
