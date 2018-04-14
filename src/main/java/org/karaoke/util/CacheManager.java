package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesWrapper;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CacheManager {

    private Map<String, KaraokesWrapper> map;

    @Autowired
    private KaraokeService service;

    @PostConstruct
    public void setUp() {
        map = new LinkedHashMap<>();
    }

    public List<Karaoke> loadCache(Argument argument, int page) {
        return loadKaraokesByWord(argument, page)
                .orElse(getKaraokeList(argument, page))
                .getKaraokes();
    }

    public Map<String, KaraokesWrapper> selectMap() {
        return map;
    }

    private Optional<KaraokesWrapper> loadKaraokesByWord(Argument word, int page) {
        return Optional.of(map.get(word.toString() + page));
    }

    private KaraokesWrapper getKaraokeList(Argument argument, int page) {
        KaraokesWrapper karaokesWrapper = service.parseKaraoke(argument, page);
        map.put(argument.toString() + page, karaokesWrapper);
        return karaokesWrapper;
    }

}
