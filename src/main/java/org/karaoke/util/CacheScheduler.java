package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.karaoke.domain.KaraokesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class CacheScheduler {

    private final static int STANDARD_DAY = 1;

    @Autowired
    CacheManager manager;

    @Scheduled(initialDelay = 0, fixedDelay = 300000)
    public void clearCache() { // 실행될 로직
        Map<String, KaraokesWrapper> map = manager.selectMap();
        DateTime currentTime = new DateTime();
        currentTime.plusDays(STANDARD_DAY);
        long count = map.entrySet().stream()
                .filter(entry ->entry.getValue().getLastCalledTime().isAfter(currentTime))
                .map(entry -> map.remove(entry.getKey()))
                .count();
        log.info("Clean cache count : {}", count);
    }

}
