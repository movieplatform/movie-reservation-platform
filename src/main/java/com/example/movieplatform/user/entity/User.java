package com.example.movieplatform.user.entity;

import com.example.movieplatform.auth.dto.OAuth2Response;
import com.example.movieplatform.user.dto.RegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime currentLoginAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private AuthType authType;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}
    public User(String email, String password, String name, String phoneNumber, LocalDate birthDate, LocalDateTime currentLoginAt, Status status, AuthType authType, Role role ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.currentLoginAt = currentLoginAt;
        this.status = status;
        this.authType = authType;
        this.role = role;
    }

    // 일반 유저 회원 가입
    public static User ofUser(RegisterDto registerDto, String encodedPassword) {
        String cleanedPhone = registerDto.getPhoneNumber().replace("-","");
        return new User(registerDto.getEmail(), encodedPassword, registerDto.getName(), cleanedPhone,
                registerDto.getBirthDate(), null, Status.ACTIVE, AuthType.LOCAL, Role.USER);
    }

    // 구글 로그인 회원가입
    public static User ofOAuth2(OAuth2Response oAuth2Response) {
        return new User(oAuth2Response.getEmail(), null, oAuth2Response.getName(), null,
                null, LocalDateTime.now(), Status.ACTIVE, AuthType.GOOGLE, Role.USER );
    }

    public enum Status{
        ACTIVE,
        DELETED
    }

    public enum AuthType{
        LOCAL,
        GOOGLE
    }

    public enum Role{
        USER,
        ADMIN
    }

    // 최근 로그인 시간 업데이트
    public void updateLoginTime(){
        this.currentLoginAt = LocalDateTime.now();
    }
}
