package com.devansh.book_recommender.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Getter
@Setter
public class Book {
    @Id
    private String id;
    private String title;
    private String description;
    private String authors;
    private String img;
    private String previewLink;
    private String publisher;
    private String publishedDate;
    private String infoLink;
    private String categories;
    private Integer ratingsCount;
}
