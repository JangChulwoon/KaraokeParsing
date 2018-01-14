package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.GraphQLQuery;
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

    KaraokeService parser;

    GraphQL graphQL;

    @Autowired
    public void setParser(KaraokeService parser) {
        this.parser = parser;
    }

    @Autowired
    public void setGraphQL(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

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

    // Test logic
    // 이거 하기전에 react 공부해야한다... relay 쪽이 react 코드라서 ..
    @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object executeOperation(@RequestBody Map body) {
        String query = (String) body.get("query");
        Map<String, Object> variables = (Map<String, Object>) body.get("variables");
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
        ExecutionResult executionResult = graphQL.execute(query, (Object) null, variables);
        Map<String, Object> result = new LinkedHashMap<>();
        if (executionResult.getErrors().size() > 0) {
            result.put("errors", executionResult.getErrors());
            log.error("Errors: {}", executionResult.getErrors());
        }
        result.put("data", executionResult.getData());
        return result;
    }
}
