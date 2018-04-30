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
    public KaraokeService(ApplicationContext context,RedisTemplate redisTemplate) {
        this.context = context;
        this.redisTemplate = redisTemplate;
    }

    // 여기에 redis를 넣자 ~!
    public List<Karaoke> extractKaraoke(Argument argument){
        Parser karaokeParser = (Parser) context.getBean(argument.getCompany());

        List<Karaoke> karaokes = (List<Karaoke>) redisTemplate.opsForList().rightPopAndLeftPush(argument.toString(),argument.toString());
        if(karaokes != null){
            return karaokes;
        }
        try {
            List<Karaoke> karaokes1 = karaokeParser.extract(argument); // 구조를 조금 바꿔야할듯 ?
            redisTemplate.opsForList().rightPush(argument.toString(), karaokes1);
            return karaokes1;
        } catch (IOException e) {
            log.error("Cause : {} , Message : {}",e.getCause(),e.getMessage());
        }
        return null;
    }

}
