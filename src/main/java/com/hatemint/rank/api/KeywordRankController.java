package com.hatemint.rank.api;

import com.hatemint.rank.Keyword;
import com.hatemint.rank.KeywordRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class KeywordRankController {

    private final KeywordRankingService service;

    @GetMapping("/ranks")
    public ResponseEntity<List<KeywordRankResponse>> rank() {
        List<Keyword> keywords = service.getTopTenKeywords();
        List<KeywordRankResponse> response = IntStream.range(0, keywords.size())
                .mapToObj(rank -> KeywordRankResponse.of(rank + 1, keywords.get(rank)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
