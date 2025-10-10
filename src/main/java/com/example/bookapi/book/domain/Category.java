package com.example.bookapi.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Category {
    @Field(type = FieldType.Keyword)
    private String name;
}
