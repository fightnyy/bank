package com.hatemint.openapi.naver;

import com.hatemint.openapi.naver.fallback.NaverOpenApiClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "naver-open-api",
        url = "${naver.openapi.url}",
        configuration = NaverOpenApiConfiguration.class,
        fallbackFactory = NaverOpenApiClientFallbackFactory.class
)
public interface NaverOpenApiClient {

    @GetMapping(value = "/v1/search/local.json")
    NaverSearchResponse findPlaceByKeyword(@RequestParam("query") String keyword);
}
