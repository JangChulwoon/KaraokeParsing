package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesTime;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CacheManager {

    private Map<String, KaraokesTime> map;

    @Autowired
    private KaraokeService service;

    @PostConstruct
    public void setUp() {
        map = new LinkedHashMap<>();
    }

    public List<Karaoke> loadCache(Argument argument, int page) {
        KaraokesTime karaokesTime = this.loadKaraokesByWord(argument, page);
        if (karaokesTime == null) {
            karaokesTime = service.parseKaraoke(argument, page);
            this.add(argument, page, karaokesTime);
        }
        return karaokesTime.getKaraokes();
    }

    public Map<String, KaraokesTime> selectMap() {
        return map;
    }


    // Map key를 다른걸로 만들 수 없을까 고민해볼 것.
    private KaraokesTime loadKaraokesByWord(Argument word, int page) {
        return map.get(word.toString() + page);
    }

    private void add(Argument arg, int page, KaraokesTime list) {
        map.put(arg.toString() + page, list);
    }
}
