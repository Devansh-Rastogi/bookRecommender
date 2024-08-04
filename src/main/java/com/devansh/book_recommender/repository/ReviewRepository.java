package com.devansh.book_recommender.repository;

import com.devansh.book_recommender.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Page<Review> findAllByBookId(String bookId, Pageable pageable);
}
