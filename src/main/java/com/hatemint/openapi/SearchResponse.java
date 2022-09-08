package com.hatemint.openapi;

import java.util.List;

public interface SearchResponse<T> {

    List<T> convertToEntity();
}
