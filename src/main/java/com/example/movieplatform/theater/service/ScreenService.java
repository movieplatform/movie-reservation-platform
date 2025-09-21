package com.example.movieplatform.theater.service;

import com.example.movieplatform.admin.dto.ScreenResponse;
import com.example.movieplatform.admin.dto.TheaterResponse;
import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.common.exception.TheaterAlreadyExistsException;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Theater;
import com.example.movieplatform.theater.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;


    public void createFiveScreen(Theater theater){
        for(int i=1; i<=5; i++){
            Screen screen = new Screen(theater,i+"관");
            screenRepository.save(screen);
        }
    }

    public List<ScreenResponse> getScreensByTheater(Long theaterId){
        List<Screen> screen = screenRepository.findByTheaterId(theaterId);
        return screen.stream()
                .map(s-> new ScreenResponse(
                        s.getId(),
                        s.getScreenName(),
                        new TheaterResponse(
                                s.getTheater().getId(),
                                s.getTheater().getTheaterName()
                        )
                )).toList();
    }

    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public List<Long> getAllScreenIds(){
        return screenRepository.findAllIds();
    }

    public List<Screen> getScreensByTheaterId(Long theaterId){
        return screenRepository.findByTheaterId(theaterId);
    }


}
