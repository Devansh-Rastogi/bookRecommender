package com.devansh.book_recommender.repository;

import com.devansh.book_recommender.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
