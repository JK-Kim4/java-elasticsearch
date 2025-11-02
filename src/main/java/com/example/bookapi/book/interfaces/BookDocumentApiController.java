package com.example.bookapi.book.interfaces;


import com.example.bookapi.book.domain.Book;
import com.example.bookapi.book.domain.BookService;
import com.example.bookapi.book.infrastructure.BookDocumentRequest;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookDocumentApiController {

    private final BookService bookService;

    @PostMapping
    public void save(@RequestBody BookDocumentRequest.Save request) {
        bookService.save(request.toDocument());
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
        List<String> suggestions = bookService.getSuggestions(query);

        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam BookSearchCriteria criteria) {
        List<Book> books = bookService.searchBooks(criteria);

        return ResponseEntity.ok(books);
    }

}
