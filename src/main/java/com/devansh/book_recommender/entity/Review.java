package com.devansh.book_recommender.entity;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reviews")
@Getter
@Setter
public class Review {
    @Id
    private String id;
    @NonNull
    private String bookId;
    @NonNull
    private String bookTitle;
    private Double price;
    private String userId;
    private String profileName;
    private String reviewHelpfulness;
    private Double reviewScore;
    private Date reviewTime;
    private String reviewSummary;
    private String reviewText;
}
