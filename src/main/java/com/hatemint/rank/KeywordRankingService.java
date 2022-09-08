package com.hatemint.rank;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordRankingService {

    private static final String KEYWORD_RANKING_KEY = "keyword";

    private final RedisTemplate<String, String> redisTemplate;
    private ZSetOperations<String, String> zSetOps;

    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    public List<Keyword> getTopTenKeywords() {
        Set<ZSetOperations.TypedTuple<String>> result = zSetOps.reverseRangeWithScores(KEYWORD_RANKING_KEY, 0, 9);

        return result.stream()
                .map(Keyword::of)
                .collect(Collectors.toList());
    }

}
