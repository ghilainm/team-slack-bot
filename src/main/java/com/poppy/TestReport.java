package com.poppy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestReport {

    private int time;
    private int count;
    private int failed;
    private int skipped;
    private int success;

    public String summary() {
        return getEmoteSummary() + " - " +  success + ":white_check_mark: " + (failed != 0 ? failed +" :large_red_square:" : "");
    }

    public String getEmoteSummary() {
        return success == count ? ":unicorn_face:" : ":dominique-lehmann:";
    }
}
