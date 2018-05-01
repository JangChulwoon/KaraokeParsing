package org.karaoke.service;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesWrapper;
import org.karaoke.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class KaraokeService {


    private ApplicationContext context;

    private RedisTemplate redisTemplate;

    @Autowired
    public KaraokeService(ApplicationContext context, RedisTemplate redisTemplate) {
        this.context = context;
        this.redisTemplate = redisTemplate;
    }

    public List<Karaoke> getKaraoke(Argument argument) {
        Parser karaokeParser = (Parser) context.getBean(argument.getCompany());
        List<Karaoke> karaokes = getKaraokesForCache(argument);

        if (karaokes != null) {
            return karaokes;
        }

        return extractKaraoke(argument, karaokeParser);
    }

    /**
     * TODO 2018.05.01
     * I should consider the Exception process.
     * Where should the exceptions be handled?
     * 1. controller.
     * 2. Exception
     * 3. Spring container (?)
     * @param argument
     * @param karaokeParser
     * @return
     */
    private List<Karaoke> extractKaraoke(Argument argument, Parser karaokeParser) {
        try {
            List<Karaoke> karaokes = karaokeParser.extract(argument);
            redisTemplate.opsForList().rightPush(argument.toString(), karaokes);
            return karaokes;
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}", e.getCause(), e.getMessage());
            return null;
        }
    }

    private List<Karaoke> getKaraokesForCache(Argument argument) {
        return (List<Karaoke>) redisTemplate.opsForList()
                .rightPopAndLeftPush(argument.toString(), argument.toString());
    }

}
