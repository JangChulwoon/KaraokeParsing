package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.KaraokesTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
@Slf4j
public class Scheduler {

    private final static long ONE_DAY = 1000 * 60 * 60 * 24;
    @Autowired
    CacheManager manager;

    // 5 분 마다 실행
    @Scheduled(initialDelay = 0, fixedDelay = 300000)
    public void clearCache() { // 실행될 로직
        Map<String, KaraokesTime> map = manager.selectMap();
        Timestamp clearedStandard = new Timestamp(System.currentTimeMillis() + ONE_DAY);
        long count = map.entrySet().stream()
                .filter(entry ->entry.getValue().getSaveTime().after(clearedStandard))
                .map(entry -> map.remove(entry.getKey()))
                .count();
        log.info("Clean cache count : {}", count);
    }

}
