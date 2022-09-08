package com.hatemint.rank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KeywordSearchEvent {

    private final String keyword;
}
