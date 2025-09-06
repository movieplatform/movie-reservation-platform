package com.example.movieplatform.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExistsException(EmailAlreadyExistsException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "register";
    }

    @ExceptionHandler({EntityNotFoundException.class, ScreenAlreadyExistsException.class, AlreadyWithdrawException.class, AdminCannotWithdrawException.class})
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
