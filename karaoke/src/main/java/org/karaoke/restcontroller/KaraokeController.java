package org.karaoke.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.parser.KaraokeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeParser parser;

    // 여기서 Company 부분 구현하고 Cache 도입하자
    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@PathVariable Company company, @PathVariable Category category, @PathVariable String word, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        log.info("default : {}",page);
        return parser.parseKY(category,word,page);
    }

}
