package com.example.movieplatform.reservation.service;

import com.example.movieplatform.admin.dto.AdminScreenInfoDto;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import com.example.movieplatform.reservation.dto.ShowTime;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningInfoService {
    private final ScreeningInfoRepository screeningInfoRepository;
    private final MovieService movieService;
    private final ScreenService screenService;


    // 관리자가 상영일정 등록
    public void saveScreeningInfo(AdminScreenInfoDto adminScreenInfoDto) {

        LocalDate startDate = adminScreenInfoDto.getStartDate();
        LocalDate endDate = adminScreenInfoDto.getEndDate();

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("상영 시작일과 종료일을 모두 입력해야 합니다.");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("상영 시작일은 종료일보다 늦을 수 없습니다.");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("상영 시작일은 오늘 이후여야 합니다.");
        }

        Movie movie = movieService.getMovie(adminScreenInfoDto.getDocId());
        Screen screen = screenService.getScreenById(adminScreenInfoDto.getScreenId());

        List<ScreeningInfo> screeningInfos = new ArrayList<>();

        for(LocalDate date = adminScreenInfoDto.getStartDate(); !date.isAfter(adminScreenInfoDto.getEndDate()); date = date.plusDays(1)) {
            for (ShowTime showtime : ShowTime.values()){
                LocalTime start = showtime.getTime();
                LocalTime end = start.plusHours(2);

                boolean exists = screeningInfoRepository.existsByScreenAndScreeningDateAndStartTime(screen, date, start);
                if (exists) {
                    throw new IllegalArgumentException("이미 편성된 상영이 있습니다");
                }

                screeningInfos.add(new ScreeningInfo(
                        date,
                        start,
                        end,
                        showtime.getPrice(),
                        ScreeningInfo.ScreeningStatus.SCHEDULED,
                        screen,
                        movie
                ));
            }
        }
        screeningInfoRepository.saveAll(screeningInfos);
    }

    // 상영일정 전부조회
    public List<ScreeningInfo> getScreeningInfos() {
        return screeningInfoRepository.findAll();
    }

    // 상영일정 삭제(단건)
    public void deleteScreeningInfo(Long id) {
        screeningInfoRepository.deleteById(id);
    }

    // 해당날짜 상영일정 조회
    public List<ScreeningInfo> getScreeningInfosByScreeningDate(LocalDate screeningDate) {
        return screeningInfoRepository.findByScreeningDate(screeningDate);
    }

}
