package com.hatemint.search.api;

import com.hatemint.search.Place;
import com.hatemint.search.SearchEngine;
import com.hatemint.search.util.PlaceSearchResultSorter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchResultResponseBuilder {
    public SearchResultResponse build(List<List<Place>> results) {
        Map<SearchEngine, List<Place>> grouped = groupBySearchEngine(results);

        List<Place> kakaos = grouped.getOrDefault(SearchEngine.KAKAO, Collections.emptyList());
        List<Place> navers = grouped.getOrDefault(SearchEngine.NAVER, Collections.emptyList());

        kakaos = kakaos.subList(0, getSubListSize(kakaos, navers));

        List<Place> sorted = new PlaceSearchResultSorter().sort(List.of(kakaos, navers));

        return new SearchResultResponse(
                parseNameFromPlace(kakaos),
                parseNameFromPlace(navers),
                parseNameFromPlace(sorted)
        );
    }

    private int getSubListSize(List<Place> kakaos, List<Place> navers) {
        if (kakaos.size() == 0) {
            return 0;
        }
        return Math.max(5, 10 - navers.size());
    }

    private Map<SearchEngine, List<Place>> groupBySearchEngine(List<List<Place>> results) {
        return results.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Place::getSearchEngine));
    }

    private List<String> parseNameFromPlace(List<Place> places) {
        return places.stream()
                .map(Place::getName)
                .collect(Collectors.toList());
    }
}
