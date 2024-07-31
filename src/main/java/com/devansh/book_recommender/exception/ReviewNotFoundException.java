package com.devansh.book_recommender.exception;

public class ReviewNotFoundException extends Throwable {
    public ReviewNotFoundException(String msg) {
        super(msg);
    }
}
