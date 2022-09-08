package com.hatemint.openapi.naver.fallback;

import com.hatemint.openapi.naver.NaverOpenApiClient;
import com.hatemint.openapi.naver.NaverSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaverOpenApiClientFallbackFactory implements FallbackFactory<NaverOpenApiClient> {

    @Override
    public NaverOpenApiClient create(Throwable cause) {
        log.error("NaverOpenApiClient error {}", cause.getMessage());
        return new NaverOpenApiClient() {
            @Override
            public NaverSearchResponse findPlaceByKeyword(String keyword) {
                return new NaverSearchResponse();
            }
        };
    }
}
