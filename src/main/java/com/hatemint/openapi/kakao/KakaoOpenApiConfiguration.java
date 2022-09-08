package com.hatemint.openapi.kakao;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class KakaoOpenApiConfiguration {

    private final KakaoOpenApiProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return request -> {
            request.header("Authorization", properties.secretKey());
        };
    }
}
