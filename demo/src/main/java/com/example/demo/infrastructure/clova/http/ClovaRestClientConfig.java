package com.example.demo.infrastructure.clova.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ClovaRestClientConfig {
    @Bean
    RestTemplate restTemplate(
            @Value("clova.secret-key")
            String secretKey,
            @Value("clova.gateway-key")
            String gatewayKey,
            @Value("clova.api-url")
            String apiUrl
    ) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .uriTemplateHandler(new DefaultUriBuilderFactory(apiUrl))
                .additionalInterceptors(new ClovaAuthInterceptor(secretKey, gatewayKey))
                .defaultMessageConverters()
                .build();
        return restTemplate;
    }
}
