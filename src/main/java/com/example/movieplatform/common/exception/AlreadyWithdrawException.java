package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class AlreadyWithdrawException extends ApiException{
    private static final String Message = "이미 탈퇴한 사용자입니다!!";

    public AlreadyWithdrawException() {
        super(HttpStatus.CONFLICT.value(), Message);
    }
}
