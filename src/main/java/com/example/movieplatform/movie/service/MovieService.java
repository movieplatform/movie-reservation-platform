package com.example.movieplatform.movie.service;

import com.example.movieplatform.movie.dto.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2";
    private final String SERVICE_KEY = "발급받은 인증키";

}
