package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.GraphQLQuery;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeService parser;

    @Autowired
    GraphQL graphQL;

    //Rest Controller
    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        return parser.parseKaraoke(argument, page).getKaraokes();
    }

    //GraphQL Controller
    @PostMapping("/karaokeGraphiQL")
    public CompletableFuture<ExecutionResult> selectByGraphiQL(@RequestBody GraphQLQuery graphQLQuery) {
        return graphQL.executeAsync(buildExecutionInput(graphQLQuery));
    }

    @PostMapping("/karaokeGraphQL")
    public CompletableFuture<ExecutionResult> selectByGraphQL(@RequestBody GraphQLQuery graphQLQuery) {
        return graphQL.executeAsync(buildExecutionInput(graphQLQuery));
    }

    private ExecutionInput buildExecutionInput(GraphQLQuery graphQLQuery) {
        return ExecutionInput.newExecutionInput()
                .query(graphQLQuery.getQuery())
                .variables(graphQLQuery.getVariables())
                .operationName(graphQLQuery.getOperationName())
                .build();
    }
}
