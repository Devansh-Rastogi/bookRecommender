package com.devansh.book_recommender.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewRequest {
    //TODO : to remove this, once jwt auth is implemented
    @NotNull(message = "userId can't be null")
    @Size(min = 1)
    private String userId;
    @NotNull(message = "profileName can't be null")
    @Size(min = 1)
    private String profileName;
    private String reviewHelpfulness;
    @NotNull(message = "reviewScore can't be null")
    @DecimalMin(value = "1.0", inclusive = true, message = "reviewScore must be at least 1.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "reviewScore must be no more than 5.0")
    private Double reviewScore;
    @NotNull(message = "reviewSummary can't be null")
    @Size(min = 1, message = "reviewSummary must be at least 1 character")
    private String reviewSummary;
    @NotNull(message = "reviewText can't be null")
    @Size(min = 1, message = "reviewText must be at least 1 character")
    private String reviewText;
}
