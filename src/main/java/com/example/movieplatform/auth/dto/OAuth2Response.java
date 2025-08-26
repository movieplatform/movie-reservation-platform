package com.example.movieplatform.auth.dto;

public interface OAuth2Response {

    String getProvider(); // 제공자 이름

    String getProviderId(); // 각각 유저의 번호값

    String getEmail();

    String getName();
}
