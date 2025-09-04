package com.example.movieplatform.movie.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "movie_genres")
@Getter
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_movieId")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public MovieGenre() {}
    public MovieGenre(Movie movie, Genre genre) {
        this.movie = movie;
        this.genre = genre;
    }

    public static MovieGenre valueOf(Movie movie, Genre genre) {
        return new MovieGenre(movie, genre);
    }
}
