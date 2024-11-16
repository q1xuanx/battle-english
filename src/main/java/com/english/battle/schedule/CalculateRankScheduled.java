package com.english.battle.schedule;


import com.english.battle.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CalculateRankScheduled {
    private final RoomService roomService;
    @Scheduled(cron = ("0 0 5 * * *"))
    public void calculateRankScheduled() {
        roomService.CalculateRankOfUser();
    }
}
