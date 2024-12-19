package com.javaacademy.library.controller;

import com.javaacademy.library.dto.BookDto;
import com.javaacademy.library.dto.PageDto;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/book")
public class BookControllerV2 {
    private static final int PAGE_SIZE = 10;
    private final Map<String, BookDto> data = new HashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 19; i++) {
            data.put("Война и мир " + i, new BookDto(i, "Война и мир " + i));
        }
    }

    private PageDto getBookDtoPage(int pageNumber) {
        List<BookDto> bookDtos = data.values().stream()
                .sorted(Comparator.comparing(BookDto::getNumber))
                .skip(PAGE_SIZE * pageNumber)
                .limit(PAGE_SIZE)
                .toList();
        int totalPages = data.values().size() / PAGE_SIZE;
        return new PageDto(bookDtos, totalPages, pageNumber, PAGE_SIZE, bookDtos.size());
    }


    @GetMapping
    @SneakyThrows
    public ResponseEntity<?> getBookByName(
            @RequestParam(required = false) String bookName,
            @RequestParam Integer pageNumber) {
        //Thread.sleep(5000);

        if (bookName == null) {
            return ResponseEntity.ok(getBookDtoPage(pageNumber));
        }

        if (data.containsKey(bookName)) {
            BookDto bookDto = data.get(bookName);
            bookDto.setDeleteLink("DELETE http://localhost:8089/book?bookName=Война и мир");
            return ResponseEntity.ok(List.of(bookDto));
        }
        return ResponseEntity.ok(List.of());
    }

    @DeleteMapping
    @CacheEvict(value = "bookName")
    public void deleteByName(@RequestParam String bookName) {
        data.remove(bookName);
    }
}
