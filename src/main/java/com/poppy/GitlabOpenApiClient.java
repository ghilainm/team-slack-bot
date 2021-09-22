package com.poppy;

import org.gitlab4j.api.GitLabApiClient;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class GitlabOpenApiClient extends GitLabApiClient {
    public GitlabOpenApiClient(String url, String token) {
        super(url, token);
    }

    @Override
    public Response get(MultivaluedMap<String, String> queryParams, Object... pathArgs) throws IOException {
        return super.get(queryParams, pathArgs);
    }


}
