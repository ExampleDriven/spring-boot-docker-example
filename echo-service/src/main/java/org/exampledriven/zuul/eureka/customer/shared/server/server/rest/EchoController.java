package org.exampledriven.zuul.eureka.customer.shared.server.server.rest;

import org.exampledriven.zuul.eureka.customer.shared.server.server.domain.HostInfo;
import org.exampledriven.zuul.eureka.customer.shared.server.server.domain.HostFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo", method = RequestMethod.GET, produces = "application/json")
    public HostInfo getHost(HttpServletRequest request) {

        return HostFactory.create(request);

    }



}