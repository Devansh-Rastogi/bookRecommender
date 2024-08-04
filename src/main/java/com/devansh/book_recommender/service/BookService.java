package com.devansh.book_recommender.service;

import com.devansh.book_recommender.entity.Book;
import com.devansh.book_recommender.exception.BookIdInvalidException;
import com.devansh.book_recommender.exception.BookNotFoundException;
import com.devansh.book_recommender.model.BookDetailResponse;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Page<Book> getBooks(int page, int size);

    Optional<BookDetailResponse> getBookById(String bookId) throws BookNotFoundException, BookIdInvalidException;

    Book getIfBookExistWithBookId(String bookId) throws BookNotFoundException, BookIdInvalidException;
}
