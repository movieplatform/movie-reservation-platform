package com.example.movieplatform.movie.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class KMDBService {
    private final RestTemplate restTemplate;
    private final String SERVICE_KEY = URLEncoder.encode("H24V0060H05G930K228G", StandardCharsets.UTF_8);

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        String genre = "공포";
        String url = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp" +
                "?collection=kmdb_new2" +
                "&releaseDts=20100101"+
                "&releaseDte=20241231"+
                "&genre=" + genre +
                "&nation=대한민국" +
                "&listCount=2" +
                "&ServiceKey=" + SERVICE_KEY;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }
}
