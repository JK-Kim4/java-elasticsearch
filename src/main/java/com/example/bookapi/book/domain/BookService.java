package com.example.bookapi.book.domain;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NumberRangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import com.example.bookapi.book.infrastructure.BookSearchRepository;
import com.example.bookapi.book.interfaces.BookSearchCriteria;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NumberRange;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookSearchRepository bookSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public void save(Book book) {
        bookSearchRepository.save(book);
    }

    public List<String> getSuggestions(String query) {

        Query multiMatchQuery = MultiMatchQuery.of(m -> m
            .query(query)
            .type(TextQueryType.BoolPrefix)
            .fields("title.auto_complete", "title.auto_complete._2gram",
                "title.auto_complete._3gram")
        )._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
            .withQuery(multiMatchQuery)
            .withPageable(PageRequest.of(0, 5))
            .build();

        SearchHits<Book> searchHits = elasticsearchOperations.search(nativeQuery, Book.class);

        return searchHits.getSearchHits().stream()
            .map(hit -> {
                    Book book = hit.getContent();
                    return book.getTitle();
                }
            ).toList();
    }

    public List<Book> searchBooks(BookSearchCriteria criteria) {
        Query query = MultiMatchQuery.of(b -> b
            .query(criteria.getQuery())
            .fields("title^3", "description^1", "categories^2")
            .fuzziness("AUTO")
        )._toQuery();

        Query categoryFilter = TermQuery.of(t -> t
            .field("categories.raw")
            .value(criteria.getCategory())
        )._toQuery();

        Query priceRangeFilter = NumberRangeQuery.of(r -> r
            .field("price")
            .gte(criteria.getMinPrice())
            .lte(criteria.getMaxPrice())
        )._toRangeQuery()._toQuery();

        Query boolQuery = BoolQuery.of(b -> b
            .must(query)
            .filter(categoryFilter, priceRangeFilter)
        )._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
            .withQuery(boolQuery)
            .withPageable(PageRequest.of(criteria.getPage() - 1, criteria.getSize()))
            .build();

        SearchHits<Book> searchHits = elasticsearchOperations.search(nativeQuery, Book.class);

        return searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .toList();
    }
}
