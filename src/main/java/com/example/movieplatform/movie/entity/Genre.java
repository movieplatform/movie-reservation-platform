package com.example.movieplatform.movie.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "genres")
@Getter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public Genre() {}
    public Genre(String name) {
        this.name = name;
    }

    public static Genre valueOf(String name) {
        return new Genre(name);
    }
}
