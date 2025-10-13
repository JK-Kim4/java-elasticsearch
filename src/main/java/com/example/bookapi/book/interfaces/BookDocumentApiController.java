package com.example.bookapi.book.interfaces;


import com.example.bookapi.book.domain.BookService;
import com.example.bookapi.book.infrastructure.BookDocumentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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



}
