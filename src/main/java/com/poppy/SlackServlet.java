package com.poppy;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/slack/events")
public class SlackServlet extends SlackAppServlet {
    public SlackServlet(App app) {
        super(app);
    }
}
