package com.example.movieplatform.common.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {
    private static final String Message = "엔티티가 존재하지 않습니다!!";

    public EntityNotFoundException(String Id) {
        super(HttpStatus.NOT_FOUND.value(), Message+ Id);
    }
}
