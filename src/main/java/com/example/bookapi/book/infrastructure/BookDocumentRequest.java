package com.example.bookapi.book.infrastructure;

import com.example.bookapi.book.domain.Author;
import com.example.bookapi.book.domain.Book;
import com.example.bookapi.book.domain.Category;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

public class BookDocumentRequest {

    @Getter
    public static class Save {

        private String title;
        private List<Author> authors;
        private List<Category> categories;
        private String isbn;
        private LocalDateTime publishDate;

        public Book toDocument() {
            return new Book(null, this.title, authors, categories, isbn, publishDate);
        }
    }

}
