package org.karaoke.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
@Component
public class DataFetcher implements graphql.schema.DataFetcher {

    @Autowired
    private KaraokeService service;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public Object get(DataFetchingEnvironment env) {
        return   CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String,String> map = env.getArgument("karaoke");
            Argument arg = new Argument()
                    .setCompany(Company.valueOf(map.get("company")))
                    .setCategory(Category.valueOf(map.get("category")))
                    .setWord(map.get("keyword"));
            return service.parseKaraoke(arg, env.getArgument("page"));
        },executor);
    }
}
