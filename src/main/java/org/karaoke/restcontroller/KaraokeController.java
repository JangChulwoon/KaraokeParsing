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

    KaraokeService parser;

    GraphQL graphQL;

    @Autowired
    PersistentQueryMap persistentQueryMap;

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

    // 이거 안돌아감 ...
    // 지금 graphIQL에서 돌아갈만한거 만드는중이야 ..
    // 다음에 만들떄 이거부터 하면됨

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
                .query(persistentQueryMap.getQuery(graphQLQuery.getQuery()))
                .variables(graphQLQuery.getVariables())
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
