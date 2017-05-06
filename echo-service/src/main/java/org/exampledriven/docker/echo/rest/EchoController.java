package org.exampledriven.docker.echo.rest;

import org.exampledriven.docker.echo.domain.HostInfo;
import org.exampledriven.docker.echo.domain.HostFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo", method = RequestMethod.GET, produces = "application/json")
    public HostInfo getHost(HttpServletRequest request) {

        return HostFactory.create(request);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public Map getFullInfo(HttpServletRequest request) {

        HashMap result = new HashMap<>();

        result.put("host", HostFactory.create(request));

        result.put("env", System.getenv());

        return result;

    }


}