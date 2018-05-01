package org.karaoke.graphql;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.parser.Parser;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DataFetcher implements graphql.schema.DataFetcher {

    private KaraokeService karaokeService;

    @Autowired
    public DataFetcher(KaraokeService karaokeService) {
        this.karaokeService = karaokeService;
    }

    @Override
    public Object get(DataFetchingEnvironment env) {
        Argument argument = buildArgument(env.getArgument("karaoke"));
        return karaokeService.getKaraoke(argument);
    }

    private Argument buildArgument(Map<String, Object> map) {
        return new Argument()
                .setCompany(Company.valueOf(map.get("company").toString()))
                .setCategory(Category.valueOf(map.get("category").toString()))
                .setWord(map.get("keyword").toString())
                .setPage(Integer.parseInt(map.get("page").toString()));
    }
}
