package com.example.bookapi.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter @Setter
public class Author {

    @Field(type = FieldType.Text, analyzer = "nori")
    private String koreanName;

    @Field(type = FieldType.Keyword)
    private String englishName;

}
