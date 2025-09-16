package com.example.movieplatform.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @GetMapping
    public ResponseEntity<Boolean> checkSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean loggedIn = auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
        return ResponseEntity.ok(loggedIn);
    }
}
