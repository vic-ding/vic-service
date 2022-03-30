package com.vic.es.utils;

import lombok.Data;

@Data
public class PageResult<T> {
    private T result;
    private long total;
}
