package org.karaoke.util;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
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

    private Map<String, List<Karaoke>> map;

    @Autowired
    private KaraokeService service;

    // 순서대로 하기 위해 linked 로 .. 근데 얘는 비동기 안되잖아 ?
    // 흠 ?
    @PostConstruct
    public void setUp() {
        map = new LinkedHashMap<>();
    }

    public List<Karaoke> loadCache(Argument argument, int page) {
        List<Karaoke> list = this.loadKaraokesByWord(argument, page);
        if (list == null) {
            list = service.parseKaraoke(argument, page);
            log.info("Check timeStamp {}", list.toString());
            this.add(argument, page, list);
        }
        return list;
    }

    public Map<String, List<Karaoke>> selectMap() {
        return map;
    }


    // Map key를 다른걸로 만들 수 없을까 고민해볼 것.
    private List<Karaoke> loadKaraokesByWord(Argument word, int page) {
        return map.get(word.toString() + page);
    }

    private void add(Argument arg, int page, List<Karaoke> list) {
        map.put(arg.toString() + page, list);
    }
}
