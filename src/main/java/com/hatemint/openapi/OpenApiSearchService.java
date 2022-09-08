package com.hatemint.openapi;

import java.util.concurrent.CompletableFuture;

public interface OpenApiSearchService {

    CompletableFuture<SearchResponse> searchPlaceByKeyword(String keyword);
}
