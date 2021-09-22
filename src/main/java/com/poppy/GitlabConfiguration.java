package com.poppy;

import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(GitlabProperties.class)
@Configuration
public class GitlabConfiguration {

    @Value("${gitlab.api.url}")
    private String url;
    @Value("${gitlab.api.token}")
    private String token;

    @Bean
    public GitLabApi gitLabApi() {
        GitLabApi gitLabApi = new GitLabApi(url, token);
        gitLabApi.setIgnoreCertificateErrors(true);
        gitLabApi.enableRequestResponseLogging();
        return gitLabApi;
    }

    @Bean
    public GitlabOpenApiClient gitLabApiClient() {
        GitlabOpenApiClient gitLabApi = new GitlabOpenApiClient(url, token);
        gitLabApi.setIgnoreCertificateErrors(true);
        return gitLabApi;
    }
}
