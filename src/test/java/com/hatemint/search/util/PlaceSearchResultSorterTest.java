package com.hatemint.search.util;

import com.hatemint.search.Place;
import com.hatemint.search.SearchEngine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;



@DataJpaTest
class PlaceSearchResultSorterTest {

    TestEntityManager testEntityManager;


    @Test
    void 카카오_검색_이후_네이버_검색_후_정렬() {
        // given
        List<Place> kakaos = List.of(
                new Place("A곱창", SearchEngine.KAKAO),
                new Place("B곱창", SearchEngine.KAKAO),
                new Place("C곱창", SearchEngine.KAKAO),
                new Place("D곱창", SearchEngine.KAKAO)
        );
        List<Place> navers = List.of(
                new Place("A곱창", SearchEngine.NAVER),
                new Place("E곱창", SearchEngine.NAVER),
                new Place("D곱창", SearchEngine.NAVER),
                new Place("C곱창", SearchEngine.NAVER)

        );
        List<Place> expected = List.of(
                new Place("A곱창", SearchEngine.KAKAO),
                new Place("C곱창", SearchEngine.KAKAO),
                new Place("D곱창", SearchEngine.KAKAO),
                new Place("B곱창", SearchEngine.KAKAO),
                new Place("E곱창", SearchEngine.NAVER)
        );

        SearchResultSorter<Place> sorter = new PlaceSearchResultSorter();

        // when
        List<Place> result = sorter.sort(List.of(kakaos, navers));

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void 네이버_검색_이후_카카오_검색_후_정렬() {
        // given
        List<Place> kakaos = List.of(
                new Place("A곱창", SearchEngine.KAKAO),
                new Place("B곱창", SearchEngine.KAKAO),
                new Place("C곱창", SearchEngine.KAKAO),
                new Place("D곱창", SearchEngine.KAKAO)
        );
        List<Place> navers = List.of(
                new Place("A곱창", SearchEngine.NAVER),
                new Place("E곱창", SearchEngine.NAVER),
                new Place("D곱창", SearchEngine.NAVER),
                new Place("C곱창", SearchEngine.NAVER)

        );
        List<Place> expected = List.of(
                new Place("A곱창", SearchEngine.KAKAO),
                new Place("C곱창", SearchEngine.KAKAO),
                new Place("D곱창", SearchEngine.KAKAO),
                new Place("B곱창", SearchEngine.KAKAO),
                new Place("E곱창", SearchEngine.NAVER)
        );

        SearchResultSorter<Place> sorter = new PlaceSearchResultSorter();

        // when
        List<Place> result = sorter.sort(List.of(navers, kakaos));

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

}
