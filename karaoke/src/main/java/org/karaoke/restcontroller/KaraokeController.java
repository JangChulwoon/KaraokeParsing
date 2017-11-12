package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.karaoke.domain.Argument;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeService parser;

    @Autowired
    GraphQL graphQL;

    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        return  parser.parseKaraoke(argument, page);
    }

    @PostMapping("/karaokeGraphiQL")
    public ExecutionResult selectByGraphiQL(@RequestBody String query)
            throws InterruptedException {
        JSONObject json = new JSONObject(query);
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(json.get("query").toString()).build();
        return  graphQL.execute(executionInput);
    }

    @PostMapping("/karaokeGraphQL")
    public ExecutionResult selectByGraphQL(@RequestBody String query)
            throws InterruptedException {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
        return  graphQL.execute(executionInput);
    }
}
