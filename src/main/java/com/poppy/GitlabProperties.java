package com.poppy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("gitlab")
public class GitlabProperties {

    private Map<String, List<GitlabScheduleIdentifier>> schedules;

}
