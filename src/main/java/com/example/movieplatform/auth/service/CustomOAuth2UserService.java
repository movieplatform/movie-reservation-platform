package com.example.movieplatform.auth.service;

import com.example.movieplatform.auth.dto.CustomOAuth2User;
import com.example.movieplatform.auth.dto.GoogleResponse;
import com.example.movieplatform.auth.dto.OAuth2Response;
import com.example.movieplatform.point.service.PointService;
import com.example.movieplatform.user.Repository.UserRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PointService pointService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response;

        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else{
            throw new OAuth2AuthenticationException("지원하지 않는 로그인 제공자");
        }

        User user = userRepository.findByEmail(oAuth2Response.getEmail());


        if (user == null) {
            user = User.ofOAuth2(oAuth2Response);
            userRepository.save(user);
            pointService.addRegisterPoint(user);

        } else{
            user.updateLoginTime();
            userRepository.save(user);
        }

        if (user.getStatus() == User.Status.DELETED){
            throw new DisabledException("탈퇴한 회원입니다.");
        } // /login?error 로 이동

        return new CustomOAuth2User(user);
    }
}
