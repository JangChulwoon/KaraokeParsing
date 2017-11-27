package org.karaoke.util;

import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheManager {

    private Map<String, List<Karaoke>> map;

    @Autowired
    private KaraokeService service;


    @PostConstruct
    public void setUp() {
        map = new ConcurrentHashMap<>();
    }

    private List<Karaoke> loadKaraokesByWord(Argument word, int page) {
        return map.get(buildKey(word) + page);
    }

    private void add(Argument arg, int page, List<Karaoke> list) {
        map.put(buildKey(arg) + page, list);
    }

    private String buildKey(Argument arg) {
        return arg.getCompany().toString() + arg.getCategory().toString() + arg.getWord();
    }

    public List<Karaoke> manageCache(Argument argument, int page) {
        List<Karaoke> list = this.loadKaraokesByWord(argument, page);
        if (list == null) {
            list = service.parseKaraoke(argument, page);
            this.add(argument, page, list);
        }
        return list;
    }

}
