package com.hatemint.search.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchResultResponse {

    private List<String> kakaos;
    private List<String> navers;
    private List<String> sorted;

    public SearchResultResponse(List<String> kakaos, List<String> navers, List<String> sorted) {
        this.kakaos = kakaos;
        this.navers = navers;
        this.sorted = sorted;
    }
}
