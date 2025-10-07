package com.example.movieplatform.movie.service;

import com.example.movieplatform.admin.dto.MovieLoadRequest;
import com.example.movieplatform.movie.dto.KMDBApiResponse;
import com.example.movieplatform.movie.repository.GenreRepository;
import com.example.movieplatform.movie.repository.MovieGenreRepository;
import com.example.movieplatform.movie.repository.MovieRepository;
import com.example.movieplatform.movie.entity.Genre;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.entity.MovieGenre;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KMDBService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kmdb.service-key}")
    private String serviceKey;

    public void loadMovies(MovieLoadRequest request) {
        try {
            String json = fetchMoviesJson(request);
            KMDBApiResponse response = parseJson(json);
            boolean hasSavedMovie = false;

            for (KMDBApiResponse.DataWrapper dataWrapper : response.data()) {
                if (dataWrapper.result() == null) continue;

                for (KMDBApiResponse.ResultWrapper result : dataWrapper.result()) {
                    boolean saved = processMovie(result);
                    if (saved) {
                        hasSavedMovie = true;
                    }
                }
            }

            if (!hasSavedMovie) {
                throw new RuntimeException("검색 조건에 맞는 영화가 없습니다.");
            }

        } catch (Exception e) {
            throw new RuntimeException("영화 데이터를 가져오는 중 오류 발생: " + e.getMessage(), e);
        }
    }

    private boolean processMovie(KMDBApiResponse.ResultWrapper result) {
        Optional<Movie> existingMovie = movieRepository.findByDocId(result.docid());
        Movie movie;

        if (existingMovie.isPresent()) {
            movie = existingMovie.get();
        } else {
            String validPoster = getFirstValidPoster(result.posters());
            if (validPoster == null) return false;

            String koreanPlot = extractKoreanPlot(result);
            movie = Movie.ofMovie(result, validPoster, koreanPlot);
            movieRepository.save(movie);
        }
        saveGenresForMovie(movie, result.genre());
        return true;
    }

    private String fetchMoviesJson(MovieLoadRequest request) {  // 에외처리?
        String url = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp" +
                "?collection=kmdb_new2" +
                "&releaseDts=" + request.getReleaseStart() +
                "&releaseDte" + request.getReleaseEnd() +
                "&genre=" + request.getGenre() +
                "&nation=" + request.getNation() +
                "&listCount=" + request.getListCount() +
                "&ServiceKey=" + serviceKey;

        return restTemplate.getForObject(url, String.class);
    }

    // KMDB API가 JSON을 반환하지만 HTTP헤더에는 Content-type: text/html로 되어있음
//    Set-Cookie: [JSESSIONID=653983DB11BFC75866D58FE281583036; Path=/openapi-data2; HttpOnly]
//    Access-Control-Allow-Origin: [*]
//    Content-Type: [text/html;charset=utf-8]
//    Transfer-Encoding: [chunked]
//    Date: [Wed, 03 Sep 2025 14:32:33 GMT]
//    Keep-Alive: [timeout=20]
//    Connection: [keep-alive]
//    Server: [Apache]

    private KMDBApiResponse parseJson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, KMDBApiResponse.class);
    }

    public void saveGenresForMovie(Movie movie, String genreString) {
        if (genreString == null || genreString.isEmpty()) return;

        String[] genreNames = genreString.split(",");
        for (String genreName : genreNames) {
            genreName = genreName.trim();
            Genre genre = genreRepository.findByName(genreName);
            if (genre == null) {
                genre = genreRepository.save(Genre.valueOf(genreName));
            }

            Optional<MovieGenre> existingMovieGenre = movieGenreRepository.findByMovieAndGenre(movie, genre);

            if (existingMovieGenre.isEmpty()) {
                MovieGenre movieGenre = MovieGenre.valueOf(movie, genre);
                movieGenreRepository.save(movieGenre);
            }

        }
    }

    private String getFirstValidPoster(String posters) {
        if (posters == null || posters.isEmpty()) return null;

        String[] urls = posters.split("\\|");
        for (String url : urls) {
            url = url.trim();
            if (isValidUrl(url)) {
                return url;  // 첫 번째 유효한 URL 반환
            }
        }
        return null;
    }

    private boolean isValidUrl(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(2000); // 2초
            conn.setReadTimeout(2000);
            int responseCode = conn.getResponseCode();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    private String extractKoreanPlot(KMDBApiResponse.ResultWrapper result) {
        if (result.plots() != null && result.plots().plot() != null) {
            return result.plots().plot().stream()
                    .filter(p -> "한국어".equals(p.plotLang()))
                    .map(KMDBApiResponse.Plot::plotText)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
