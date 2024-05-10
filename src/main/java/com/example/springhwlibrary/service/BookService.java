package com.example.springhwlibrary.service;

import com.example.springhwlibrary.dto.BookCreateDto;
import com.example.springhwlibrary.dto.BookDto;
import com.example.springhwlibrary.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Page<BookDto> getAllBooksWithPageable(Pageable pageable);

    Optional<BookDto> getBook(Long id);

    boolean addBook(BookCreateDto bookCreateDto);

    Optional<BookDto> editBook(BookDto bookDto);

    boolean deleteBook(Long id);




}
