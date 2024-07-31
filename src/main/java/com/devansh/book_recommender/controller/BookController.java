package com.devansh.book_recommender.controller;

import com.devansh.book_recommender.exception.BookIdInvalidException;
import com.devansh.book_recommender.exception.BookNotFoundException;
import com.devansh.book_recommender.model.Book;
import com.devansh.book_recommender.model.BookDetailResponse;
import com.devansh.book_recommender.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Book> books = bookService.getBooks(page, size);
            if (books.hasContent() && !books.getContent().isEmpty())
                return ResponseEntity.ok(books);
            else
                return new ResponseEntity<>("No books found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the books.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/books/{bookId}", produces = "application/json")
    public ResponseEntity<?> getBooksById(@PathVariable("bookId") String bookId) throws BookNotFoundException, BookIdInvalidException {
        try {
            Optional<BookDetailResponse> book = bookService.getBookById(bookId);
            return ResponseEntity.ok(book.get());
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while fetching the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
