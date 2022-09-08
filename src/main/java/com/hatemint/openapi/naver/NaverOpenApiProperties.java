package com.hatemint.openapi.naver;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "naver.openapi")
@RequiredArgsConstructor
public class NaverOpenApiProperties {

    private final String clientId;
    private final String clientSecret;

    public String clientId() {
        return clientId;
    }

    public String clientSecret() {
        return clientSecret;
    }
}
