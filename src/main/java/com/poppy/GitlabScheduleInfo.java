package com.poppy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitlabScheduleInfo {

    private String alias;
    private String projectId;
    private int scheduleId;
    private String testResultUrl;

}
