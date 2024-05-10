package com.example.springhwlibrary.repository;

import com.example.springhwlibrary.model.Author;
import com.example.springhwlibrary.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    Page<Book> findAllByAuthor(Author author, Pageable pageable);

}
