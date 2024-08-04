package com.devansh.book_recommender.service.impl;

import com.devansh.book_recommender.entity.Book;
import com.devansh.book_recommender.entity.Review;
import com.devansh.book_recommender.exception.ReviewIdInvalidException;
import com.devansh.book_recommender.exception.ReviewNotFoundException;
import com.devansh.book_recommender.model.ReviewRequest;
import com.devansh.book_recommender.repository.ReviewRepository;
import com.devansh.book_recommender.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewsByBookId(String bookId, int page, int size, String sortParameter, boolean asc) {
        Sort sort = Sort.by(sortParameter);
        sort = asc ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reviewRepository.findAllByBookId(bookId, pageable);
    }

    @Override
    public Review addReviewForBook(Book book, ReviewRequest reviewRq) {
        Review review = new Review();
        review.setBookId(book.getId());
        review.setBookTitle(book.getTitle());

        //TODO: remove the userId and profileName from request json and add the user who sent the request
        review.setUserId(reviewRq.getUserId());
        review.setProfileName(reviewRq.getProfileName());
        //

        review.setReviewHelpfulness(reviewRq.getReviewHelpfulness());
        review.setReviewScore(reviewRq.getReviewScore());
        review.setReviewTime(new Date());
        review.setReviewText(reviewRq.getReviewText());
        review.setReviewSummary(reviewRq.getReviewSummary());

        return reviewRepository.save(review);

    }

    @Override
    public void editReviewWithReviewId(String reviewId, ReviewRequest reviewRq) throws ReviewNotFoundException, ReviewIdInvalidException {
        Review review = getReviewFromDbIfAvailable(reviewId);

        //TODO: Check the loggedIn user sending the request to edit the review

        review.setReviewHelpfulness(reviewRq.getReviewHelpfulness());
        review.setReviewScore(reviewRq.getReviewScore());
        review.setReviewTime(new Date());
        review.setReviewText(reviewRq.getReviewText());
        review.setReviewSummary(reviewRq.getReviewSummary());

        reviewRepository.save(review);
    }

    private Review getReviewFromDbIfAvailable(String reviewId) throws ReviewIdInvalidException, ReviewNotFoundException {
        if (reviewId == null || reviewId.isEmpty())
            throw new ReviewIdInvalidException("ReviewId is either null or empty");
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty())
            throw new ReviewNotFoundException("No Review found with reviewId : " + reviewId);
        return optionalReview.get();
    }

    @Override
    public void deleteReview(String reviewId) throws ReviewNotFoundException, ReviewIdInvalidException {
        Review review = getReviewFromDbIfAvailable(reviewId);
        reviewRepository.deleteById(reviewId);
    }
}
