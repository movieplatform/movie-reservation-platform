package com.example.movieplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoviePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviePlatformApplication.class, args);
    }

}
