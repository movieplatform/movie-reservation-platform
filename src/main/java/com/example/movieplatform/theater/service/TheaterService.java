package com.example.movieplatform.theater.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.common.exception.TheaterAlreadyExistsException;
import com.example.movieplatform.theater.entity.Theater;
import com.example.movieplatform.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final ScreenService screenService;

    public void createTheater(String theaterName){
        if(theaterRepository.existsByTheaterName(theaterName)){
            throw new TheaterAlreadyExistsException(theaterName);
        }
        Theater theater = new Theater(theaterName);
        theaterRepository.save(theater);

        screenService.createFiveScreen(theater);
    }

    public List<Theater> getAllTheaters(){
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long theaterId){
        return theaterRepository.findById(theaterId)
                .orElseThrow(() -> new EntityNotFoundException(theaterId.toString()));
    }
}
