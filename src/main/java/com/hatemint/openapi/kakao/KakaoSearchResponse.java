package com.hatemint.openapi.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hatemint.openapi.SearchResponse;
import com.hatemint.search.Place;
import com.hatemint.search.SearchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoSearchResponse implements SearchResponse<Place> {

    private Meta meta;
    private List<Document> documents = new ArrayList<>();

    @Override
    public List<Place> convertToEntity() {
        return documents.stream()
                .map(document -> new Place(document.placeName, SearchEngine.KAKAO))
                .collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class Meta {
        private int totalCount;
        private int pageableCount;
        private boolean isEnd;
        private RegionInfo sameName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class RegionInfo {
        private List<String> region;
        private String keyword;
        private String selectedRegion;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class Document {
        private String id;
        private String placeName;
        private String categoryName;
        private String categoryGroupCode;
        private String categoryGroupName;
        private String phone;
        private String addressName;
        private String roadAddressName;
        private String x;
        private String y;
        private String placeUrl;
        private String distance;
    }
}
