package com.javaacademy.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer number;
    private String name;
    private List<BookPageDto> pages;
    private String deleteLink;
    private LocalDateTime createdDate = LocalDateTime.now();

    public BookDto(Integer number, String name) {
        this.number = number;
        this.name = name;
    }
}
