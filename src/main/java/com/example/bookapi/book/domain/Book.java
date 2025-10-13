package com.example.bookapi.book.domain;


import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "books")
@Setting(settingPath = "/elasticsearch/book-settings.json")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Book {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "book_title_analyzer"),
        otherFields = {
            @InnerField(suffix = "auto_complete", type = FieldType.Search_As_You_Type, analyzer = "nori")
        }
    )
    private String title;

    @Field(type = FieldType.Text, analyzer = "book_description_analyzer")
    private String description;

    @Field(type = FieldType.Nested)
    private List<Author> authors;

    @Field(type = FieldType.Nested)
    private List<Category> categories;

    @Field(type =  FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Keyword)
    private String isbn;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime publishDate;

}
