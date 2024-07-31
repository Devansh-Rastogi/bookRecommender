package com.devansh.book_recommender.service.impl;

import com.devansh.book_recommender.exception.BookIdInvalidException;
import com.devansh.book_recommender.exception.BookNotFoundException;
import com.devansh.book_recommender.model.Book;
import com.devansh.book_recommender.model.BookDetailResponse;
import com.devansh.book_recommender.model.Review;
import com.devansh.book_recommender.repository.BookRepository;
import com.devansh.book_recommender.repository.ReviewRepository;
import com.devansh.book_recommender.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Book> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookDetailResponse> getBookById(String bookId) throws BookNotFoundException, BookIdInvalidException {
        Book book = getIfBookExistWithBookId(bookId);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewTime").descending());
        Page<Review> reviewList = reviewRepository.findAllByBookId(bookId, pageable);
        return Optional.of(new BookDetailResponse(book, reviewList));
    }

    @Override
    public Book getIfBookExistWithBookId(String bookId) throws BookNotFoundException, BookIdInvalidException {
        if (bookId == null || bookId.isEmpty())
            throw new BookIdInvalidException("BookId is Either null or empty");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty())
            throw new BookNotFoundException("No book found with ID: " + bookId);
        return book.get();
    }
}
