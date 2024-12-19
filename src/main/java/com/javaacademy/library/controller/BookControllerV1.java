package com.javaacademy.library.controller;

import com.javaacademy.library.dto.BookDto;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookControllerV1 {
    private final Map<String, BookDto> data = new HashMap<>();
    private final Map<String, BookDto> cache = new HashMap<>();
    //private final Map<String, BookDto> clientFavoriteBooks = new HashMap<>();

    @PostConstruct
    public void init() {
        data.put("Война и мир", new BookDto(1, "Война и мир"));
    }

//    @GetMapping("/book")
//    public ResponseEntity<?> getBookByName(@RequestParam(required = false) String bookName,
//                                                      @RequestHeader(name = "client-name") String clientName
//    ) {
//        if (clientFavoriteBooks.containsKey(clientName)) {
//            return ResponseEntity.ok(List.of(clientFavoriteBooks.get(clientName)));
//        }
//        if (bookName == null) {
//            return ResponseEntity.badRequest().body("Я вас не знаю, а вы книгу не назвали");
//        }
//        return ResponseEntity.ok(List.of(data.get(bookName)));
//    }

    @GetMapping
    @SneakyThrows
    public ResponseEntity<?> getBookByName(@RequestParam String bookName) {
        if (cache.containsKey(bookName)) {
            return ResponseEntity.ok(List.of(cache.get(bookName)));
        }
        Thread.sleep(5000);
        if (data.containsKey(bookName)) {
            BookDto bookDto = data.get(bookName);
            cache.put(bookName, bookDto);
            return ResponseEntity.ok(List.of(bookDto));
        }
        return ResponseEntity.ok(List.of());
    }

    @DeleteMapping
    public void deleteByName(@RequestParam String bookName) {
        data.remove(bookName);
        cache.remove(bookName);
    }
}
