package com.example.movieplatform.auth.service;

import com.example.movieplatform.auth.dto.CustomUserDetails;
import com.example.movieplatform.user.Repository.UserRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email + " 존재하지 않는 사용자입니다. ");
        } // /login?error 로 이동
        user.updateLoginTime();
        userRepository.save(user);
        return new CustomUserDetails(user);
    }
}
