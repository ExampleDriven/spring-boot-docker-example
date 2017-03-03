package org.exampledriven.zuul.eureka.customer.shared.server.server.rest;

import org.exampledriven.zuul.eureka.customer.shared.server.server.domain.HostInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteHostInfoClient {

    public HostInfo getRemoteHostInfo(RestTemplate restTemplate, String url) {

        HostInfo hostInfo = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HostInfo>() {})
            .getBody();

        return hostInfo;

    }

}