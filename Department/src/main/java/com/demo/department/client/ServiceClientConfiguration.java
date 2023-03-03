package com.demo.department.client;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ServiceClientConfiguration {
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(500, 5000, 3);
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ServiceClientErrorDecoder();
    }
}

