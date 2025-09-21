package com.example.movieplatform.scheduler;

import com.example.movieplatform.reservation.service.ScreeningInfoService;
import com.example.movieplatform.theater.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScreeningInfoScheduler {

    private final ScreeningInfoService screeningInfoService;
    private final ScreenService screenService;

    @Scheduled(cron = "0 0 0 */5 * *")
    public void scheduleScreeningInfo(){
        List<Long> screenIds = screenService.getAllScreenIds();
        for (Long screenId : screenIds) {
            screeningInfoService.saveScreeningInfo(screenId);
        }
    }
}
