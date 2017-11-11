package org.karaoke.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.parser.KaraokeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeParser parser;

    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@PathVariable String company, @PathVariable String category, @PathVariable String word) throws IOException {
        return parser.parseKY(word);
    }

}
