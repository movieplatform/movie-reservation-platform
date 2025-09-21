package com.example.movieplatform.scheduler;

import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningStatusScheduler {

    private final ScreeningInfoRepository screeningInfoRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateScreeningStatus(){
        LocalDate today = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        List<ScreeningInfo> todayScreenings = screeningInfoRepository.findByScreeningDate(today);

        for(ScreeningInfo screening : todayScreenings){
            LocalTime start = screening.getStartTime();
            LocalTime end = screening.getEndTime();

            ScreeningInfo.ScreeningStatus oldStatus = screening.getScreeningStatus();
            ScreeningInfo.ScreeningStatus newStatus;

            if (nowTime.isBefore(start)) {
                newStatus = ScreeningInfo.ScreeningStatus.SCHEDULED;
            } else if (!nowTime.isBefore(start) && nowTime.isBefore(end)) {
                newStatus = ScreeningInfo.ScreeningStatus.ONGOING;
            } else {
                newStatus = ScreeningInfo.ScreeningStatus.FINISHED;
            }

            if (oldStatus != newStatus) {
                screening.updateScreeningStatus(newStatus);
            }
        }
        screeningInfoRepository.flush();
    }
}
