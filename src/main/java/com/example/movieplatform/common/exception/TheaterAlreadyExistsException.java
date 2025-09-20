package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class TheaterAlreadyExistsException extends ApiException {
    private static final String Message = "존재하는 극장 입니다!!";

    public TheaterAlreadyExistsException(String theaterName) {
        super(HttpStatus.CONFLICT.value(), Message+ theaterName);
    }
}
