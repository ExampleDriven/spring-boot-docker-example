package org.exampledriven.zuul.eureka.customer.shared.server.server.rest;

import org.exampledriven.zuul.eureka.customer.shared.server.server.Config;
import org.exampledriven.zuul.eureka.customer.shared.server.server.domain.HostInfo;
import org.exampledriven.zuul.eureka.customer.shared.server.server.domain.HostFactory;
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

        getRemoteHostInfo(result, "remote-dns", restTemplate, "http://echo-service:9098/echo");
        getRemoteHostInfo(result, "remote-ribbon", loadBalancedRestTemplate, "http://echo-service/echo");

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