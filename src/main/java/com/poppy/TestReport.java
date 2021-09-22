package com.poppy;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class TestReport {

    private int time;
    private int count;
    private int failed;
    private int skipped;
    private int success;

    public String summary() {
        return getEmoteSummary() + getString();
    }

    @NotNull
    private String getString() {
        if (count != 0) {
            return " - " + success + ":white_check_mark: " + (failed != 0 ? failed + " :large_red_square:" : "");
        } else {
            return " no test performed (pipeline probably failed) :large_red_square:";
        }
    }

    public String getEmoteSummary() {
        return (success == count && count != 0) ? ":unicorn_face:" : ":dominique-lehmann:";
    }
}
