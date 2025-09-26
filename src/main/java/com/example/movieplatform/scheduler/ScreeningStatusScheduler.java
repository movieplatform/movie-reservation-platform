package com.example.movieplatform.scheduler;

import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningStatusScheduler {

    private final ScreeningInfoRepository screeningInfoRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateScreeningStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime currentTime = now.toLocalTime();

        // 오늘 및 미래 상영 정보 조회
        List<ScreeningInfo> activeScreenings = screeningInfoRepository.findActiveAndTodayScreenings(today, currentTime);

        for (ScreeningInfo screening : activeScreenings) {
            LocalDateTime startDateTime = LocalDateTime.of(screening.getScreeningDate(), screening.getStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(screening.getScreeningDate(), screening.getEndTime());

            ScreeningInfo.ScreeningStatus oldStatus = screening.getScreeningStatus();
            ScreeningInfo.ScreeningStatus newStatus;

            // 오늘/미래 영화 상태 판단
            if (now.isBefore(startDateTime)) {
                newStatus = ScreeningInfo.ScreeningStatus.SCHEDULED;
            } else if (!now.isBefore(startDateTime) && now.isBefore(endDateTime)) {
                newStatus = ScreeningInfo.ScreeningStatus.ONGOING;
            } else {
                newStatus = ScreeningInfo.ScreeningStatus.FINISHED;
            }

            if (oldStatus != newStatus) {
                screening.updateScreeningStatus(newStatus);
            }
        }

        // 오늘 이전에 SCHEDULED 상태로 남아 있는 과거 영화도 FINISHED로 수정
        List<ScreeningInfo> pastWrongStatus = screeningInfoRepository
                .findByScreeningStatusAndScreeningDateBefore(ScreeningInfo.ScreeningStatus.SCHEDULED, today);

        for (ScreeningInfo screening : pastWrongStatus) {
            screening.updateScreeningStatus(ScreeningInfo.ScreeningStatus.FINISHED);
        }

        screeningInfoRepository.flush();
    }
}
