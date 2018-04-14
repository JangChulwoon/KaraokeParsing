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

    @Autowired
    private ApplicationContext context;

    // 차라리 Map에 객체를 들고 있는게 좋으려나 ?
    // 이게 좋은 코드 일까 ?
    public KaraokesWrapper parseKaraoke(Argument argument, int page){
        Parser karaokeParser = (Parser) context.getBean(argument.getCompany().toString());
        try {
            return karaokeParser.parse(argument,page);
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}",e.getCause(),e.getMessage());
        }
        return null;
    }

}
