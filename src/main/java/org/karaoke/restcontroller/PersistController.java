package org.karaoke.restcontroller;

import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.GraphQLInput;
import org.karaoke.graphql.PersistentQueryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PersistController {


    @Autowired
    PersistentQueryMap persistQuery;

    @Autowired
    GraphQL graphQL;

    @GetMapping("/queries")
    public String selectAll(){ // key : value 인 json 으로 추후 바꿀 것
        return persistQuery.getQueryMap().toString();
    }

    @PostMapping("/queries")
    public ResponseEntity<String> selectAll(@RequestBody GraphQLInput query){
        if(verifyQuery(query.getKey(),query.getQuery())){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(400).body("wrong input");
    }

    private boolean verifyQuery(String key, String query){
        List list = graphQL.execute(query).getErrors();
        if(list.isEmpty() || persistQuery.hasKey(key)){
            return false;
        }
        persistQuery.insertQuery(key,query);
        return true;

    }

}
