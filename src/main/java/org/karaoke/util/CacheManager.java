package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesWrapper;
import org.karaoke.service.KaraokeService;
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

    private KaraokeService service;

    public CacheManager(KaraokeService service) {
        this.service = service;
    }

    @PostConstruct
    public void setUp() {
        map = new LinkedHashMap<>();
    }

    public List<Karaoke> loadCache(final Argument argument) {
        return loadKaraokesByWord(argument)
                .getKaraokes();
    }

    public Map<String, KaraokesWrapper> selectMap() {
        return map;
    }

    private KaraokesWrapper loadKaraokesByWord(final Argument argument) {
        return Optional.ofNullable(map.get(makeKey(argument)))
                .orElse(getKaraokeList(argument));
    }

    private KaraokesWrapper getKaraokeList(final Argument argument) {
        KaraokesWrapper karaokesWrapper = service.extractKarake(argument);
        map.put(makeKey(argument), karaokesWrapper);
        return karaokesWrapper;
    }

    private String makeKey(final Argument argument) {
        return argument.toString();
    }

}
