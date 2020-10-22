package org.exampledriven.docker.echo.rest;

import lombok.RequiredArgsConstructor;
import org.exampledriven.docker.echo.config.Config;
import org.exampledriven.docker.echo.domain.HostFactory;
import org.exampledriven.docker.echo.domain.HostInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/echo")
@RequiredArgsConstructor
public class RemoteEchoController {

    @Qualifier(Config.LOADBALANCED)
    private final RestTemplate loadBalancedRestTemplate;

    @Qualifier(Config.STANDARD)
    private final RestTemplate restTemplate;

    private final RemoteHostInfoClient remoteHostInfoClient;

    @GetMapping(value = "/remote-echo", produces = "application/json")
    public Map<String, HostInfo> getHost(HttpServletRequest request) {

        Map<String, HostInfo> result = new HashMap<>();
        result.put("local", HostFactory.create(request));

        getRemoteHostInfo(result, "http://example_echo-service:9098/echo (Docker swarm service)", restTemplate, "http://example_echo-service:9098/echo");
        getRemoteHostInfo(result, "http://echo-service:9098/echo (Docker compose DNS entry)", restTemplate, "http://echo-service:9098/echo");
        getRemoteHostInfo(result, "http://echo-service/echo (Ribbon+Eureka)", loadBalancedRestTemplate, "http://echo-service/echo");

        return result;
    }

    private void getRemoteHostInfo(Map<String, HostInfo> result, String key, RestTemplate loadBalancedRestTemplate, String url) {
        HostInfo remoteHostInfo = null;
        try {
            remoteHostInfo = remoteHostInfoClient.getRemoteHostInfo(loadBalancedRestTemplate, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put(key, remoteHostInfo);
    }

}