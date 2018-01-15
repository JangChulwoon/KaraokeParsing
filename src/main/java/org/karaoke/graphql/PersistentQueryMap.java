package org.karaoke.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersistentQueryMap {

    ConcurrentHashMap<Integer,String> persistentQuery;

    private final String query = "query selectquery($karaoke : karaoke){\n" +
            "  Karaoke(karaoke:$karaoke){\n" +
            "    number\n" +
            "    title\n" +
            "    singer\n" +
            "  }\n" +
            "}";

    @PostConstruct
    public void setUp(){
        persistentQuery = new ConcurrentHashMap<>();
        // 이런식으로 작업
        // 추후엔 관리자가 추가할 수 있도록 작업 !
        persistentQuery.put(1,query);
    }

    @Bean
    public Map<Integer,String> getQueryMap(){
        return persistentQuery;
    }

}
