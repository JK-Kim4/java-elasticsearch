package com.example.bookapi.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Getter @Setter
public class Category {

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "book_category_analyzer"),
        otherFields = {
            @InnerField(suffix = "raw", type = FieldType.Search_As_You_Type, analyzer = "nori"),
        }
    )
    private String name;
}
