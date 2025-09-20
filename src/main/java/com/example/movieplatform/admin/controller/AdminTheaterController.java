package com.example.movieplatform.admin.controller;

import com.example.movieplatform.common.exception.TheaterAlreadyExistsException;
import com.example.movieplatform.theater.entity.Theater;
import com.example.movieplatform.theater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/theaters")
public class AdminTheaterController {
    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<String> addTheater(@RequestParam String theaterName){
        try{
            theaterService.createTheater(theaterName);
            return ResponseEntity.ok("극장 생성 완료!!");
        }catch(TheaterAlreadyExistsException e){
            return ResponseEntity.badRequest().body(theaterName + " 은 이미 존재하는 극장 이름입니다.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters(){
        List<Theater> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }
}
