package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class ScreenAlreadyExistsException extends ApiException {
    private static final String Message = "존재하는 상영관 입니다!!";

    public ScreenAlreadyExistsException(String screenName) {
        super(HttpStatus.CONFLICT.value(), Message+ screenName);
    }
}
