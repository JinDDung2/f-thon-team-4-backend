package com.example.demo.infrastructure.clova.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ClovaAuthInterceptor implements ClientHttpRequestInterceptor {
    private final String secretKey;
    private final String gatewayKey;

    public ClovaAuthInterceptor(String secretKey, String gatewayKey) {
        this.secretKey = secretKey;
        this.gatewayKey = gatewayKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("X-NCP-APIGW-API-KEY", gatewayKey);
        request.getHeaders().set("X-NCP-CLOVASTUDIO-API-KEY", secretKey);
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return execution.execute(request, body);
    }
}
