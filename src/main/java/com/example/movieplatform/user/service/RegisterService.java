package com.example.movieplatform.user.service;

import com.example.movieplatform.common.exception.EmailAlreadyExistsException;
import com.example.movieplatform.point.service.PointService;
import com.example.movieplatform.user.Repository.UserRepository;
import com.example.movieplatform.user.dto.RegisterDto;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// 암호화 필요
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PointService pointService;

    public void register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyExistsException(registerDto.getEmail());
        }

        // 비밀번호 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(registerDto.getPassword());

        User user = User.ofUser(registerDto, encodedPassword);
        userRepository.save(user);

        // 회원가입 시 5000포인트 적립 - 적립 실패시 회원 가입실패(?) 하게 할지 고민중
        pointService.addRegisterPoint(user);

    }
}
