package com.example.springhwlibrary.service;

import com.example.springhwlibrary.dto.BookCreateDto;
import com.example.springhwlibrary.dto.BookDto;
import com.example.springhwlibrary.model.Author;
import com.example.springhwlibrary.model.Book;
import com.example.springhwlibrary.repository.AuthorRepository;
import com.example.springhwlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Page<BookDto> getAllBooksWithPageable(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::getFromBook);
    }

    @Override
    public Optional<BookDto> getBook(Long id) {
        return bookRepository.findById(id)
                .map(this::getFromBook);
    }

    @Override
    public boolean addBook(BookCreateDto bookCreateDto) {
        Book book = new Book();
        book.setTitle(bookCreateDto.getTitle());
        book.setAuthor(findAuthor(bookCreateDto.getAuthor()));
        bookRepository.save(book);
        return true;
    }

    @Override
    public Optional<BookDto> editBook(BookDto bookDto) {
        return bookRepository.findById(bookDto.getId())
                .map(book -> {
                    book.setTitle(bookDto.getTitle());
                    book.setAuthor(findAuthor(bookDto.getAuthor()));
                    bookRepository.save(book);
                    return getFromBook(book);
                });
    }

    @Override
    public boolean deleteBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return true;
                })
                .orElse(false);
    }


    private Author findAuthor(String name) {
        List<Author> authors;
        Author author;
        if (this.authorRepository != null) {
            authors = authorRepository.findAll();
            author = authors.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(new Author(name));
            authorRepository.save(author);
        } else {
            author = new Author(name);
            authorRepository.save(author);
        }
        return author;
    }


    private BookDto getFromBook(Book book) {
        return Optional.ofNullable(book)
                .map(b -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setId(b.getId());
                    bookDto.setTitle(b.getTitle());
                    bookDto.setAuthor(Optional.ofNullable(b.getAuthor()).map(Author::getName).orElse(null));
                    return bookDto;
                })
                .orElse(null);
    }
}
