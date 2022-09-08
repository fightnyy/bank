package com.hatemint.search.util;

import com.hatemint.search.Place;
import com.hatemint.search.SearchEngine;

import java.util.*;
import java.util.stream.Collectors;

public class PlaceSearchResultSorter implements SearchResultSorter<Place> {

    @Override
    public List<Place> sort(List<List<Place>> args) {
        Map<Integer, PlaceSortBlock> blocks = new HashMap<>();
        Comparator<PlaceSortBlock> comparator = new PlaceSortBlockComparator();

        for (List<Place> places : args) {
            merge(blocks, places);
        }

        return blocks.values().stream()
                .sorted(comparator::compare)
                .map(PlaceSortBlock::place)
                .collect(Collectors.toList());
    }

    private void merge(Map<Integer, PlaceSortBlock> blocks, List<Place> places) {
        for (int index = 0; index < places.size(); index++) {
            PlaceSortBlock block = PlaceSortBlock.of(places.get(index), index);
            int key = block.key();

            if (blocks.containsKey(key)) {
                blocks.get(key).increaseCountAndReadjustPriority(block);
            } else {
                blocks.put(key, block);
            }
        }
    }

    private static class PlaceSortBlockComparator implements Comparator<PlaceSortBlock> {

        @Override
        public int compare(PlaceSortBlock o1, PlaceSortBlock o2) {
            // If the count is the same, the search engine has the highest priority.
            if (o1.counts == o2.counts) {
                if (o1.priority == o2.priority) {
                    // Within the same search engine, the search order has higher priority.
                    return o1.index - o2.index;
                }
                return o1.priority - o2.priority;
            }

            // The higher the count, the higher the priority.
            return o2.counts - o1.counts;
        }
    }

    /**
     * Data structure for sorting search results.
     *
     * place: The place to be sorted.
     * index: The index of the place in the original list.
     * coutns: Frequency of appearance within multiple search results.
     * priority: The priority of the search engine.
     */
    public static class PlaceSortBlock {

        private Place place;
        private int index;
        private int counts;
        private int priority;

        private PlaceSortBlock(Place place, int index) {
            this.place = place;
            this.index = index;
            this.priority = calculatePriority(place.getSearchEngine());
        }

        public int key() {
            return Objects.hash(place);
        }

        private int calculatePriority(SearchEngine engine) {
            switch (engine) {
                case KAKAO:
                    return 0;
                case NAVER:
                    return 1;
                default:
                    return Integer.MAX_VALUE;
            }
        }

        public static PlaceSortBlock of(Place place, int index) {
            return new PlaceSortBlock(place, index);
        }

        public void increaseCountAndReadjustPriority(PlaceSortBlock block) {
            if (this.priority > block.priority) {
                this.priority = block.priority;
                this.place = block.place;
                this.index = block.index;
            }
            this.counts++;
        }

        public Place place() {
            return this.place;
        }
    }
}
