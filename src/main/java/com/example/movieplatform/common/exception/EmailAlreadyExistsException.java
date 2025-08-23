package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends ApiException {
    private static final String Message = "이미 존재하는 이메일 입니다!!";

    public EmailAlreadyExistsException(String email) {
        super(HttpStatus.CONFLICT.value(), Message+ email);
    }
}
