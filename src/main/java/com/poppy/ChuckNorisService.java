package com.poppy;

import io.chucknorris.client.ChuckNorrisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChuckNorisService {

    private final ChuckNorrisClient client = new ChuckNorrisClient();

    public String getRandomJoke() {
        log.info("Getting joke from chuck noris database");
        return client.getRandomJoke().getValue();
    }
}
