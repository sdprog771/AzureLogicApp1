package com.example.connecttologicapp1.builder;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateBuilderObj {

    @Bean(name = "appRestClient")
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder
                .connectTimeout(Duration.ofMillis(3000))
                .readTimeout(Duration.ofMillis(3000))
                .build();
    }

}
