package org.karaoke.service;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.KaraokesWrapper;
import org.karaoke.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class KaraokeService {


    private ApplicationContext context;

    @Autowired
    public KaraokeService(ApplicationContext context) {
        this.context = context;
    }

    public KaraokesWrapper extractKarake(Argument argument){
        Parser karaokeParser = (Parser) context.getBean(argument.getCompany());
        try {
            return karaokeParser.extract(argument);
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}",e.getCause(),e.getMessage());
        }
        return null;
    }

}
