package org.karaoke.restcontroller;


import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.parser.KaraokeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeParser parser;

    // 여기서 Company 부분 구현하고 Cache 도입하자
    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        log.info("argument : {} ",argument.toString());
        return  parser.parseTJ(argument, page);
    }

}
