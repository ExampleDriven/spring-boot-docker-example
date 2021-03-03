package org.exampledriven.docker.echo.rest;

import org.exampledriven.docker.echo.domain.HostInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteHostInfoClient {

    public HostInfo getRemoteHostInfo(RestTemplate restTemplate, String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HostInfo>() {
                })
                .getBody();
    }

}