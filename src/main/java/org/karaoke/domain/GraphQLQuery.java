package org.karaoke.domain;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GraphQLQuery {

    private String query;
    private Map<String, Object> variables;
    private String operationName;

    public GraphQLQuery(String query, Map<String, Object> variables, String operationName) {
        this.query = query;
        this.variables = variables;
        this.operationName = operationName;
    }

    public String getQuery() {
        return query;
    }

    public GraphQLQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public GraphQLQuery setVariables(Map<String, Object> variables) {
        this.variables = variables;
        return this;
    }

    public String getOperationName() {
        return operationName;
    }

    public GraphQLQuery setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }
}
