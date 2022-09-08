package com.hatemint.rank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
@RequiredArgsConstructor
public class Keyword {

    private final long count;
    private final String value;

    public static Keyword of(ZSetOperations.TypedTuple<String> tuple) {
        return new Keyword(Double.valueOf(tuple.getScore()).longValue(), tuple.getValue());
    }
}
