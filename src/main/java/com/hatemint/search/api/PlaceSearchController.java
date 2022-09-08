package com.hatemint.search.api;

import com.hatemint.search.Place;
import com.hatemint.search.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceSearchController {

    private final PlaceSearchService service;

    @GetMapping("/search/{keyword}")
    public ResponseEntity<SearchResultResponse> search(@PathVariable String keyword) {
        List<List<Place>> results = service.search(keyword);
        return ResponseEntity.ok(new SearchResultResponseBuilder().build(results));
    }
}
