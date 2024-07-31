package com.devansh.book_recommender.exception;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String msg) {
        super(msg);
    }
}
