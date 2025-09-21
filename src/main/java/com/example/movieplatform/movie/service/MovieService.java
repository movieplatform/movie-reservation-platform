package com.example.movieplatform.movie.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.movie.repository.MovieRepository;
import com.example.movieplatform.movie.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAllByOrderByRepRlsDateDesc();
    }

    public Page<Movie> getMoviesByGenre(String genreName, Pageable pageable){
        if (genreName.equals("ALL")) {
            Pageable sortedPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by("title").ascending()   // 제목 기준 오름차순 정렬
            );
            return movieRepository.findAll(sortedPageable);
        }
        return movieRepository.findByGenreName(genreName, pageable);
    }

    public Movie getMovie(String docId) {
        return movieRepository.findByDocId(docId)
                .orElseThrow(() -> new EntityNotFoundException(docId));
    }

    public List<Movie> getRecentTop10Movies() {
        List<Movie> movies = movieRepository.findTop10ByOrderByRepRlsDateDesc();
        if (movies == null || movies.isEmpty()) {
            throw new IllegalArgumentException("최근 개봉한 영화가 없습니다.");
        }
        return movies;
    }

//    public void deleteMovieById(String id) {
//        Movie movie = getMovie(id);
//        movieRepository.delete(movie);
//    }
}
