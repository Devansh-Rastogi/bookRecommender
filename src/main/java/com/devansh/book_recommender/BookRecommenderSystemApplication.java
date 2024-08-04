package com.devansh.book_recommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BookRecommenderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommenderSystemApplication.class, args);
    }
}
