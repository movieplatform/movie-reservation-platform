package com.example.movieplatform.reservation.service;

import com.example.movieplatform.admin.dto.ScreenInfoResponse;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import com.example.movieplatform.reservation.dto.ScreeningInfoDto;
import com.example.movieplatform.reservation.dto.ShowTime;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Theater;
import com.example.movieplatform.theater.service.ScreenService;
import com.example.movieplatform.theater.service.TheaterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningInfoService {
    private final ScreeningInfoRepository screeningInfoRepository;
    private final TheaterService theaterService;
    private final MovieService movieService;
    private final ScreenService screenService;

    private static final int TARGET_DAYS = 14;  // 2주일치 저장

    // 관리자가 상영일정 등록하거나 5일간격으로 스케쥴러 실행
    @Transactional
    public void saveScreeningInfo(Long screenId) {


        List<Movie> movies = movieService.getRecentTop10Movies();
        Screen screen = screenService.getScreenById(screenId);

        LocalDate today = LocalDate.now();
        LocalDate desiredEndDate = today.plusDays(TARGET_DAYS-1);

        LocalDate lastestScheduledDate = screeningInfoRepository.findMaxScreeningDateByScreen(screen);

        LocalDate startDate;
        if (lastestScheduledDate == null || lastestScheduledDate.isBefore(today)) {
            startDate = today;
        } else if (lastestScheduledDate.isAfter(desiredEndDate)) {
            throw new RuntimeException("이미 상영일정이 충분히 등록되어 있습니다.");
        } else {
            startDate = lastestScheduledDate.plusDays(1);
        }

        // 몇일 만들어야할지
        long daysToCreate = ChronoUnit.DAYS.between(startDate, desiredEndDate)+1;
        if (daysToCreate <=0 ) {
            throw new RuntimeException("등록할 상영일정이 없습니다.");
        }

        // 만들어야할 상영 일정 개수 ex) 하루에 4편씩 편성되니까 5일이면 20개
        int totalSlots = (int)(daysToCreate * ShowTime.values().length);

        List<Movie> assignment = createUniformAssignment(movies, totalSlots);
        List<ScreeningInfo> screeningInfos = new ArrayList<>(totalSlots);
        int idx = 0;
        for (int d = 0; d < daysToCreate; d++) {
            LocalDate date = startDate.plusDays(d);
            for(ShowTime showTime : ShowTime.values()) {
                if(screeningInfoRepository.existsByScreenAndScreeningDateAndStartTime(screen,date,showTime.getStartTime())) continue;
                Movie movie = assignment.get(idx++);

                ScreeningInfo screeningInfo = new ScreeningInfo(
                        date,
                        showTime.getStartTime(),
                        showTime.getEndTime(),
                        showTime.getPrice(),
                        ScreeningInfo.ScreeningStatus.SCHEDULED,
                        screen,
                        movie
                );
                screeningInfos.add(screeningInfo);
            }
        }

        if (screeningInfos.isEmpty()) {
            throw new RuntimeException("등록할 상영일정이 없습니다.");
        }

        screeningInfoRepository.saveAll(screeningInfos);

    }

    // 영화 균등분배
    private List<Movie> createUniformAssignment(List<Movie> movies, int totalSlots) {
        List<Movie> pool = new ArrayList<>(movies);
        Collections.shuffle(pool);
        List<Movie> result = new ArrayList<>(totalSlots);
        int m = pool.size();
        for (int i=0; i<totalSlots; i++) {
            result.add(pool.get(i%m));
        }
        return result;
    }

    // 해당날짜 상영일정 조회
    public List<ScreeningInfo> getScreeningInfosByScreeningDate(LocalDate screeningDate) {
        return screeningInfoRepository.findByScreeningDate(screeningDate);
    }

     // 극장별로 상영일자 조회
    public List<ScreeningInfo> getScreeningInfosByTheaterId(Long theaterId) {
        return screeningInfoRepository.findByTheaterId(theaterId);
    }

    // 프론트에서 2주일치 상영일정 조회(극장별로)
    public ScreenInfoResponse getScreenInfoByTheaterId(Long theaterId) {
        Theater theater = theaterService.getTheaterById(theaterId);
        List<ScreeningInfoDto> screeningInfoDtoList = screeningInfoRepository.findScreeningDtoByTheaterId(theaterId);

        Map<LocalDate, Map<String, List<ScreenInfoResponse.MovieDto>>> grouped =
                screeningInfoDtoList.stream()
                        .collect(Collectors.groupingBy(
                                ScreeningInfoDto::getScreeningDate,
                                TreeMap::new,
                                Collectors.groupingBy(
                                        ScreeningInfoDto::getScreenName,
                                        Collectors.mapping(
                                                dto -> new ScreenInfoResponse.MovieDto(
                                                        dto.getMovieTitle(),
                                                        dto.getStartTime(),
                                                        dto.getEndTime(),
                                                        dto.getStatus()
                                                ),
                                                Collectors.toList()
                                        )
                                )
                        ));

        List<ScreenInfoResponse.ScreeningInfo> dateList = grouped.entrySet().stream()
                .map(dateEntry -> {
                    List<ScreenInfoResponse.ScreenDto> screens = dateEntry.getValue().entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .map(screenEntry -> new ScreenInfoResponse.ScreenDto(
                                    screenEntry.getKey(),
                                    screenEntry.getValue()
                            ))
                            .toList();
                    return new ScreenInfoResponse.ScreeningInfo(dateEntry.getKey(), screens);
                })
                .toList();

        return new ScreenInfoResponse(theater.getTheaterName(), dateList);

    }
}
