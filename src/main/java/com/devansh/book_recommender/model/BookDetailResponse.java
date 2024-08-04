package com.devansh.book_recommender.model;

import com.devansh.book_recommender.entity.Book;
import com.devansh.book_recommender.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class BookDetailResponse {

    private String id;
    private String title;
    private String description;
    private String authors;
    private String img;
    private String previewLink;
    private String publisher;
    private String publishedDate;
    private String infoLink;
    private String categories;
    private Integer ratingsCount;
    private List<Review> reviewList;

    public BookDetailResponse(Book book, Page<Review> reviewList) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.authors = book.getAuthors();
        this.img = book.getImg();
        this.previewLink = book.getPreviewLink();
        this.publisher = book.getPublisher();
        this.publishedDate = book.getPublishedDate();
        this.infoLink = book.getInfoLink();
        this.categories = book.getCategories();
        this.ratingsCount = book.getRatingsCount();
        if (reviewList.hasContent())
            this.reviewList = reviewList.getContent();
    }
}
