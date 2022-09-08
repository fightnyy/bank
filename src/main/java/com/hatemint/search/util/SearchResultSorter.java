package com.hatemint.search.util;

import java.util.List;

public interface SearchResultSorter<T> {

    List<T> sort(List<List<T>> args);
}
