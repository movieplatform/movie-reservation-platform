package com.example.movieplatform.screen.service;

import com.example.movieplatform.common.exception.ScreenAlreadyExistsException;
import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;

    // 관리자 페이지에서 상영관 이름 입력후 저장( ex: 1관, 2관, 3관)
    public void createScreen(String screenName) {
        if(screenRepository.existsByScreenName(screenName)) throw new ScreenAlreadyExistsException(screenName);
        Screen screen = new Screen(screenName);
        screenRepository.save(screen);
    }
}
