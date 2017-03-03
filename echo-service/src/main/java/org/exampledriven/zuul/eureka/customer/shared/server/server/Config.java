package org.exampledriven.zuul.eureka.customer.shared.server.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Peter_Szanto on 6/2/2016.
 */
@Configuration
public class Config {

    public static final String LOADBALANCED = "loadbalanced";
    public static final String STANDARD = "standard";

    @LoadBalanced
    @Bean
    @Qualifier(LOADBALANCED)
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier(STANDARD)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
