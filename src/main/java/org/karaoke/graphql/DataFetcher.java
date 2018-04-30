package org.karaoke.graphql;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DataFetcher implements graphql.schema.DataFetcher {


    // env.getArgument 할때, GraphQLInt 면 String 으로 parsing이 안되네
    @Override
    public Object get(DataFetchingEnvironment env) {
        Map<String, Object> map = env.getArgument("karaoke");
        Argument arg = new Argument()
                .setCompany(Company.valueOf(map.get("company").toString()))
                .setCategory(Category.valueOf(map.get("category").toString()))
                .setWord(map.get("keyword").toString())
                .setPage(Integer.parseInt(map.get("page").toString()));
        return null; // 이 부분은 내일 ..
    }
}
