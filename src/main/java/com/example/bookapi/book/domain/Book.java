package com.example.bookapi.book.domain;


import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "books")
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "nori") // 한글 형태소 분석 등
    private String title;

    @Field(type = FieldType.Nested)
    private List<Author> authors;

    @Field(type = FieldType.Nested)
    private List<Category> categories;

    @Field(type = FieldType.Keyword)
    private String isbn;

    @Field(type =  FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime publishDate;

}
