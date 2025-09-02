package com.example.movieplatform.auth.dto;

import com.example.movieplatform.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class  CustomOAuth2User implements OAuth2User, UserPrincipal {

    private final User user;

    public CustomOAuth2User(User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "email", user.getEmail(),
                "name", user.getName()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
    }

    @Override
    public String getName() {
        return user.getEmail();
    }

    @Override
    public User getUser() {
        return user;
    }
}
