package com.hatemint.openapi.naver;

import com.hatemint.openapi.SearchResponse;
import com.hatemint.search.Place;
import com.hatemint.search.SearchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NaverSearchResponse implements SearchResponse<Place> {

    private List<Item> items = new ArrayList<>();

    @Override
    public List<Place> convertToEntity() {
        return items.stream()
                .map(item -> parseTitle(item.title))
                .map(title -> new Place(title, SearchEngine.NAVER))
                .collect(Collectors.toList());
    }

    private static String parseTitle(String title) {
        return Jsoup.parse(title).text();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class Item {
        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private String mapx;
        private String mapy;
    }
}
