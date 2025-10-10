package com.example.bookapi.book.domain;

import com.example.bookapi.book.infrastructure.BookSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookSearchRepository bookSearchRepository;

    public void save(Book book) {
        bookSearchRepository.save(book);
    }

}
