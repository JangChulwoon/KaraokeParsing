package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.GraphQLInput;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@Slf4j
public class KaraokeController {

    private KaraokeService parser;

    private GraphQL graphQL;

    @Autowired
    public KaraokeController(KaraokeService parser, GraphQL graphQL) {
        this.parser = parser;
        this.graphQL = graphQL;
    }

    //Rest Controlle
    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument) {
        return parser.extractKarake(argument).getKaraokes();
    }

    //GraphQL Controller
    @PostMapping("/karaokeGraphiQL")
    public CompletableFuture<ExecutionResult> selectByGraphiQL(@RequestBody GraphQLInput input) {
        return graphQL.executeAsync(buildExecutionInput(input));
    }

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
