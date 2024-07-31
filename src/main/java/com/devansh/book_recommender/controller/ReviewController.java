package com.devansh.book_recommender.controller;

import com.devansh.book_recommender.exception.BookIdInvalidException;
import com.devansh.book_recommender.exception.BookNotFoundException;
import com.devansh.book_recommender.exception.ReviewIdInvalidException;
import com.devansh.book_recommender.exception.ReviewNotFoundException;
import com.devansh.book_recommender.model.Book;
import com.devansh.book_recommender.model.Review;
import com.devansh.book_recommender.model.ReviewRequest;
import com.devansh.book_recommender.service.BookService;
import com.devansh.book_recommender.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @GetMapping("books/{bookId}/reviews")
    public ResponseEntity<?> getReviewsByBookId(@PathVariable("bookId") String bookId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "reviewTime") String sortBy,
                                                @RequestParam(defaultValue = "true") boolean asc) throws BookNotFoundException, BookIdInvalidException {
        try {
            bookService.getIfBookExistWithBookId(bookId);
            Page<Review> reviewPage = reviewService.getReviewsByBookId(bookId, page, size, sortBy, asc);
            if (reviewPage.hasContent())
                return ResponseEntity.ok(reviewPage);
            return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching the book reviews", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("books/{bookId}/reviews")
    public ResponseEntity<?> addReviewForBook(@PathVariable("bookId") String bookId,
                                              @RequestBody ReviewRequest reviewRq) throws BookNotFoundException, BookIdInvalidException {
        try {
            Book book = bookService.getIfBookExistWithBookId(bookId);
            Review review = reviewService.addReviewForBook(book, reviewRq);
            return new ResponseEntity<>("Review created for book : " + bookId + " with reviewId : " + review.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while adding the review for the bookId : " + bookId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("reviews/{reviewId}")
    public ResponseEntity<?> editReviewForBook(@PathVariable("reviewId") String reviewId,
                                               @RequestBody ReviewRequest reviewRq) throws ReviewIdInvalidException, ReviewNotFoundException {
        try {
            reviewService.editReviewWithReviewId(reviewId, reviewRq);
            return ResponseEntity.ok("Successfully edit the review : " + reviewId);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while editing the review : " + reviewId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") String reviewId) throws ReviewNotFoundException, ReviewIdInvalidException {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return new ResponseEntity<>("Error while deleting the review : " + reviewId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
