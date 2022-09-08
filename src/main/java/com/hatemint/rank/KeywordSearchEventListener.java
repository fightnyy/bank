package com.hatemint.rank;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class KeywordSearchEventListener {

    private static final String KEYWORD_RANKING_KEY = "keyword";
    private static final int INCREMENT_SCORE = 1;

    private final RedisTemplate<String, String> redisTemplate;
    private ZSetOperations<String, String> zSetOps;

    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    @Async
    @EventListener
    public void handle(KeywordSearchEvent event) {
        zSetOps.incrementScore(KEYWORD_RANKING_KEY, event.getKeyword(), INCREMENT_SCORE);
    }
}
