package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.GraphQLInput;
import org.karaoke.domain.GraphQLQuery;
import org.karaoke.graphql.PersistentQueryMap;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@Slf4j
public class KaraokeController {

    private KaraokeService parser;

    private GraphQL graphQL;

    private PersistentQueryMap persistentQueryMap;

    @Autowired
    public KaraokeController(KaraokeService parser, GraphQL graphQL, PersistentQueryMap persistentQueryMap) {
        this.parser = parser;
        this.graphQL = graphQL;
        this.persistentQueryMap = persistentQueryMap;
    }

    //Rest Controller
    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) {
        return parser.parseKaraoke(argument).getKaraokes();
    }

    //GraphQL Controller
    @PostMapping("/karaokeGraphiQL")
    public CompletableFuture<ExecutionResult> selectByGraphiQL(@RequestBody GraphQLInput input) {
        return graphQL.executeAsync(buildExecutionInput(input));
    }

    // argument resolver 를 써서 적용해볼까
    // 객체 자체를 담아서 사용하자
    // caching 은 어떻게 하지?   ~
    // Test 코드도 해야되는데 ..
    @PostMapping("/karaokeGraphQL")
    public CompletableFuture<ExecutionResult> selectByGraphQL(@RequestBody GraphQLInput input) {
        return graphQL.executeAsync(buildExecutionInput(input));
    }

    private ExecutionInput buildExecutionInput(GraphQLInput input) {
        return ExecutionInput.newExecutionInput()
                .query(input.getQuery())
                .variables(input.getVariables())
                .build();
    }


}
