package com.example.demo.infrastructure.clova.http;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ClovaRestClientConfig {
    @Bean
    RestTemplate restTemplate(
            @Value("${clova.secret-key}")
            String secretKey,
            @Value("${clova.gateway-key}")
            String gatewayKey,
            @Value("${clova.api-url}")
            String apiUrl
    ) {
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder
                .create()
                .setMaxConnPerRoute(20)
                .setMaxConnTotal(200)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(TimeValue.ofSeconds(60))
                .build();

        RestTemplate restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
                .uriTemplateHandler(new DefaultUriBuilderFactory(apiUrl))
                .additionalInterceptors(new ClovaAuthInterceptor(secretKey, gatewayKey))
                .defaultMessageConverters()
                .build();

        return restTemplate;
    }

    @Bean
    ClovaRestClient clovaRestClient(RestTemplate restTemplate) {
        return new ClovaRestClient(restTemplate);
    }
}
