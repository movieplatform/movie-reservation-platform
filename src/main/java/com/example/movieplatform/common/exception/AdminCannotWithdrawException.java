package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class AdminCannotWithdrawException extends ApiException {
    private static final String Message = "관리자는 탈퇴 불가능!!";

    public AdminCannotWithdrawException() {
        super(HttpStatus.FORBIDDEN.value(), Message);
    }
}
