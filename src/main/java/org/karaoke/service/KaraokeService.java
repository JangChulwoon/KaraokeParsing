package org.karaoke.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesTime;
import org.karaoke.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karaoke.domain.Category.SINGER;
import static org.karaoke.domain.Category.SONG;

@Component
@Slf4j
public class KaraokeService {

    @Autowired
    private ApplicationContext context;

    public KaraokesTime parseKaraoke(Argument argument, int page){
        Parser karaokeParser = (Parser) context.getBean(argument.getCompany().toString());
        try {
            return karaokeParser.parse(argument,page);
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}",e.getCause(),e.getMessage());
        }
        return null;
    }

}
