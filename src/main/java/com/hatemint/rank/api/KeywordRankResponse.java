package com.hatemint.rank.api;

import com.hatemint.rank.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordRankResponse {

    private int rank;

    private long searchCount;

    private String keyword;

    private KeywordRankResponse(int rank, long searchCount, String keyword) {
        this.rank = rank;
        this.searchCount = searchCount;
        this.keyword = keyword;
    }

    public static KeywordRankResponse of(int rank, Keyword keyword) {
        return new KeywordRankResponse(rank, keyword.getCount(), keyword.getValue());
    }
}
