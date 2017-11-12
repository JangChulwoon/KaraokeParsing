package org.karaoke.restcontroller;


import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeService parser;

    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        return  parser.parseKaraoke(argument, page);
    }

}
