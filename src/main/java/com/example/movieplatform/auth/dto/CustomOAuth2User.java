package com.example.movieplatform.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class  CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;
    private final String role;

    public CustomOAuth2User(OAuth2Response oAuth2Response, String role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", oAuth2Response.getEmail());
        map.put("name", oAuth2Response.getName());
        map.put("provider", oAuth2Response.getProvider());
        map.put("providerId", oAuth2Response.getProviderId());
        return map;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getName() {
        return oAuth2Response.getEmail();
    }
}
