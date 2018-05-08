package org.karaoke.service;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
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
        List<Karaoke> karaokes = getKaraokesFromCache(argument);

        if (karaokes != null) {
            return karaokes;
        }
        // Todo
        // extract is search , saveCache is instructions
        // search and instructions is not used the "same function".
        return extractAndSaveCache((Argument) argument, (Parser) karaokeParser);
    }

    /**
     * TODO 2018.05.01
     * I should consider the Exception process.
     * Where should the exceptions be handled?
     * 1. controller.
     * 2. Exception
     * 3. Spring container (?)
     *
     * @param argument
     * @param karaokeParser
     * @return
     */
    private List<Karaoke> extractAndSaveCache(Argument argument, Parser karaokeParser) {
        List karaokes = extractKaraokes(argument, karaokeParser);
        insertFromCache(argument.toString(), karaokes);
        // most exception is not momentary. so I save the `null` into cache.
        return karaokes;
    }

    private List extractKaraokes(Argument argument, Parser karaokeParser) {
        try {
            return karaokeParser.extract(argument);
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}", e.getCause(), e.getMessage());
            return null;
        }
    }

    private void insertFromCache(String key, List<Karaoke> values) {
        redisTemplate.opsForList().rightPush(key, values);
    }

    private List<Karaoke> getKaraokesFromCache(Argument argument) {
        return (List<Karaoke>) redisTemplate.opsForList()
                .rightPopAndLeftPush(argument.toString(), argument.toString());
    }

}
