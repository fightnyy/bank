package com.hatemint.openapi.kakao.fallback;

import com.hatemint.openapi.kakao.KakaoOpenApiClient;
import com.hatemint.openapi.kakao.KakaoSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KakaoOpenApiFallbackFactory implements FallbackFactory<KakaoOpenApiClient> {

    @Override
    public KakaoOpenApiClient create(Throwable cause) {
        log.error("KakaoOpenApiClient error {}", cause.getMessage());
        return new KakaoOpenApiClient() {
            @Override
            public KakaoSearchResponse findPlaceByKeyword(String keyword) {
                return new KakaoSearchResponse();
            }
        };
    }
}
