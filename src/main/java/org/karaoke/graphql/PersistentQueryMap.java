package org.karaoke.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersistentQueryMap {

    ConcurrentHashMap<String,String> persistentQuery;

    private final String query = "query selectKaraoke($karaoke : karaoke){\n" +
            "  Karaoke(karaoke:$karaoke){\n" +
            "    number\n" +
            "    title\n" +
            "    singer\n" +
            "  }\n" +
            "}";

    @PostConstruct
    public void setUp(){
        persistentQuery = new ConcurrentHashMap<>();
        persistentQuery.put("1",query);
    }

    @Bean
    public Map<String,String> getQueryMap(){
        return persistentQuery;
    }

}
