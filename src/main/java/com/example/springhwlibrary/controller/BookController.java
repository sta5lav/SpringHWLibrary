package com.example.springhwlibrary.controller;


import com.example.springhwlibrary.dto.BookCreateDto;
import com.example.springhwlibrary.dto.BookDto;
import com.example.springhwlibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public ResponseEntity<Page<BookDto>> getAllBooksWithPageable(Pageable pageable) {
        Page<BookDto> books = bookService.getAllBooksWithPageable(pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        Optional<BookDto> bookDto = bookService.getBook(id);
        return bookDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody BookCreateDto bookCreateDto) {
        boolean added = bookService.addBook(bookCreateDto);
        return added ? ResponseEntity.ok().build() :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
    }

    @PutMapping
    public ResponseEntity<BookDto> editBook(@RequestBody BookDto bookDto) {
        Optional<BookDto> editedBook = bookService.editBook(bookDto);
        return editedBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
