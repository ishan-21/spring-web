package com.ishan.rest_template_demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }

}
