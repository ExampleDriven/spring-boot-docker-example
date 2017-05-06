package org.exampledriven.docker.echo;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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

    @Bean
    @Profile("AWS")
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        config.setDataCenterInfo(info);
        config.setHostname(info.get(AmazonInfo.MetaDataKey.localHostname));
        config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
        config.setNonSecurePort(9098);
        return config;
    }

}
