package org.exampledriven.docker.echo.rest;

import org.exampledriven.docker.echo.Config;
import org.exampledriven.docker.echo.domain.HostFactory;
import org.exampledriven.docker.echo.domain.HostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/echo")
public class RemoteEchoController {

    @Autowired
    @Qualifier(Config.LOADBALANCED)
    RestTemplate loadBalancedRestTemplate;

    @Autowired
    @Qualifier(Config.STANDARD)
    RestTemplate restTemplate;

    @Autowired
    RemoteHostInfoClient remoteHostInfoClient;

    @GetMapping(value = "/remote-echo", produces = "application/json")
    public Map<String, HostInfo> getHost(HttpServletRequest request) {

        Map result = new HashMap<String, HostInfo>();
        result.put("local", HostFactory.create(request));

        getRemoteHostInfo(result, "http://example_echo-service:9098/echo (Docker swarm service)", restTemplate, "http://example_echo-service:9098/echo");
        getRemoteHostInfo(result, "http://echo-service:9098/echo (Docker compose DNS entry)", restTemplate, "http://echo-service:9098/echo");
        getRemoteHostInfo(result, "http://echo-service/echo (Ribbon+Eureka)", loadBalancedRestTemplate, "http://echo-service/echo");

        return result;

    }

    private void getRemoteHostInfo(Map result, String key, RestTemplate loadBalancedRestTemplate, String url) {
        HostInfo remoteHostInfo = null;
        try {
            remoteHostInfo = remoteHostInfoClient.getRemoteHostInfo(loadBalancedRestTemplate, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put(key, remoteHostInfo);
    }

}