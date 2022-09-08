package com.hatemint.openapi.naver;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class NaverOpenApiConfiguration {

    private final NaverOpenApiProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return request -> {
            request.header("X-Naver-Client-Id", properties.clientId())
                    .header("X-Naver-Client-Secret", properties.clientSecret())
                    .query("display", "5");
        };
    }
}
