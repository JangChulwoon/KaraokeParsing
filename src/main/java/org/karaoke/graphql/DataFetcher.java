package org.karaoke.graphql;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.domain.Karaoke;
import org.karaoke.service.KaraokeService;
import org.karaoke.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DataFetcher implements graphql.schema.DataFetcher {

    @Autowired
    CacheManager manager;

    @Override
    public Object get(DataFetchingEnvironment env) {
        Map<String, String> map = env.getArgument("karaoke");
        Argument arg = new Argument()
                .setCompany(Company.valueOf(map.get("company")))
                .setCategory(Category.valueOf(map.get("category")))
                .setWord(map.get("keyword"));

        return manager.manageCache(arg, env.getArgument("page"));
    }
}
