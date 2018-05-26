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

    private KaraokeExtractor karaokeExtractor;

    @Autowired
    public KaraokeService(ApplicationContext context, RedisTemplate redisTemplate, KaraokeExtractor karaokeExtractor) {
        this.context = context;
        this.redisTemplate = redisTemplate;
        this.karaokeExtractor = karaokeExtractor;
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
        return tryExtractKaraokes((Argument) argument, (Parser) karaokeParser);
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
    private List<Karaoke> tryExtractKaraokes(Argument argument, Parser karaokeParser) {
        List karaokes = null;
        try {
            karaokes = extractKaraokes(argument, karaokeParser);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        insertFromCache(argument.toString(), karaokes);

        // when result is empty, timeout exception occur.
        // most exception is Timeout Exception. so I save the `null` into cache.
        // .. bad ...
        return karaokes;
    }

    private List extractKaraokes(Argument argument, Parser parser) throws IOException {
        return karaokeExtractor.tryToExtract(parser, argument);
    }

    private void insertFromCache(String key, List<Karaoke> values) {
        redisTemplate.opsForList().rightPush(key, values);
    }

    private List<Karaoke> getKaraokesFromCache(Argument argument) {
        return (List<Karaoke>) redisTemplate.opsForList()
                .rightPopAndLeftPush(argument.toString(), argument.toString());
    }


}
