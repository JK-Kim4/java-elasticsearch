package com.example.bookapi.book.interfaces;

import lombok.Getter;

@Getter
public class BookSearchCriteria {
    private String query;
    private String category;
    private double minPrice;
    private double maxPrice;
    private int page;
    private int size;

}
