package com.example.bookapi.book.infrastructure;

import com.example.bookapi.book.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {

}
