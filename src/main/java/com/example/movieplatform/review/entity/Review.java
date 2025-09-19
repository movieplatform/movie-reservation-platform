package com.example.movieplatform.review.entity;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String content;
    private LocalDateTime postedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_docId")
    private Movie movie;

    public Review() {}

    public Review(int rating, String content, LocalDateTime postedAt, User user, Movie movie) {
        this.rating = rating;
        this.content = content;
        this.postedAt = postedAt;
        this.user = user;
        this.movie = movie;
    }

    public static Review ofReview(int rating, String content, User user, Movie movie) {
        return new Review(rating, content, LocalDateTime.now(), user, movie);
    }

}
