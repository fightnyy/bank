package com.hatemint.openapi.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "kakao.openapi")
public class KakaoOpenApiProperties {

    private static final String SECRET_KEY_PREFIX = "KakaoAK";

    private final String secretKey;

    public KakaoOpenApiProperties(String secretKey) {
        this.secretKey = String.format("%s %s", SECRET_KEY_PREFIX, secretKey);
    }

    public String secretKey() {
        return secretKey;
    }
}
