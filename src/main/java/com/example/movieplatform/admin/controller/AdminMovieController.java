package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.MovieLoadRequest;
import com.example.movieplatform.admin.service.AdminMovieService;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/movies")
public class AdminMovieController {

    private final AdminMovieService adminMovieService;
    private final MovieService movieService;

    // 영화 목록
    @GetMapping
    public ResponseEntity<List<Movie>> adminMovies() {

        List<Movie> movies = movieService.getMovies();
        return ResponseEntity.ok(movies);
    }

    // 영화 불러오기
    // 영화 최종적으로 몇개 불러왔는지 메세지 추가
    @PostMapping
    public ResponseEntity<String> loadMovies(@RequestBody MovieLoadRequest movieLoadRequest) {
        try {
            adminMovieService.loadMovies(movieLoadRequest);
            return ResponseEntity.ok("영화 불러오기 완료!!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body("서버 오류 발생");
        }
    }

//    @PostMapping("/{docId}")
//    public ResponseEntity<String> deleteMovie(@PathVariable String docId){
//        movieService.deleteMovieById(docId);
//        return ResponseEntity.ok("영화삭제 완료");
//    }
}
