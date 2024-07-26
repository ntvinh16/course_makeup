package com.ecommerce.courses.domain.model.response.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PagedList<T> {
    private int page;
    private int size;
    private long total;
    private List<T> data;

    public PagedList(int page, int size, List<T> data, long total) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }
}

