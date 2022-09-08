package com.hatemint.openapi.kakao;

import com.hatemint.openapi.kakao.fallback.KakaoOpenApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakao-open-api",
        url = "${kakao.openapi.url}",
        configuration = KakaoOpenApiConfiguration.class,
        fallbackFactory = KakaoOpenApiFallbackFactory.class
)
public interface KakaoOpenApiClient {

    @GetMapping(value = "/v2/local/search/keyword.json")
    KakaoSearchResponse findPlaceByKeyword(@RequestParam("query") String keyword);
}
