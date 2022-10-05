package com.yago.app.demo.client.java17.configuration;

import com.yago.app.demo.client.java17.interceptor.RestTemplateHeaderModifierInterceptor;
import com.yago.app.demo.client.java17.properties.ApplicationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    private ApplicationProperties applicationProperties;

    public RestTemplateConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        RestTemplate restTemplate = builder.build();

        List<ClientHttpRequestInterceptor> interceptors
            = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor(applicationProperties));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
