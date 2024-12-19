package com.javaacademy.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto <T> {
    private List<T> content;
    private Integer countPages;
    private Integer currentPage;
    private Integer maxPageSize;
    private Integer size;
}
