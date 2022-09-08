package com.hatemint.search;

import com.hatemint.openapi.OpenApiSearchService;
import com.hatemint.openapi.SearchResponse;
import com.hatemint.rank.KeywordSearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceSearchService {

    private final List<OpenApiSearchService> services;
    private final ApplicationEventPublisher publisher;

    public List<List<Place>> search(String keyword) {
        publisher.publishEvent(new KeywordSearchEvent(keyword));

        return services.stream()
                .map(service -> service.searchPlaceByKeyword(keyword))
                .map(CompletableFuture::join)
                .map(SearchResponse<Place>::convertToEntity)
                .collect(Collectors.toList());
    }
}
