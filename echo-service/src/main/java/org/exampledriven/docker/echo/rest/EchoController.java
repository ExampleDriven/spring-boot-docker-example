package org.exampledriven.docker.echo.rest;

import org.exampledriven.docker.echo.domain.HostFactory;
import org.exampledriven.docker.echo.domain.HostInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EchoController {

    @GetMapping(value = "/echo", produces = "application/json")
    public HostInfo getHost(HttpServletRequest request) {
        return HostFactory.create(request);
    }

    @GetMapping(value = "/", produces = "application/json")
    public Map getFullInfo(HttpServletRequest request) {

        HashMap result = new HashMap<>();
        result.put("host", HostFactory.create(request));
        result.put("env", System.getenv());

        return result;
    }

}