package com.devansh.book_recommender.service;

import com.devansh.book_recommender.entity.Book;
import com.devansh.book_recommender.entity.Review;
import com.devansh.book_recommender.exception.ReviewIdInvalidException;
import com.devansh.book_recommender.exception.ReviewNotFoundException;
import com.devansh.book_recommender.model.ReviewRequest;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<Review> getReviewsByBookId(String bookId, int page, int size, String sortParameter, boolean asc);

    Review addReviewForBook(Book book, ReviewRequest reviewRq);

    void editReviewWithReviewId(String reviewId, ReviewRequest reviewRq) throws ReviewNotFoundException, ReviewIdInvalidException;

    void deleteReview(String reviewId) throws ReviewNotFoundException, ReviewIdInvalidException;
}
