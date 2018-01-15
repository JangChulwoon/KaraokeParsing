package org.karaoke.domain;


import java.util.Map;

public class GraphQLQuery {

    private Integer key;
    private Map<String, Object> variables;
    private String operationName;

    public GraphQLQuery(Integer key, Map<String, Object> variables, String operationName) {
        this.key = key;
        this.variables = variables;
        this.operationName = operationName;
    }

    public Integer getKey() {
        return key;
    }

    public GraphQLQuery setKey(Integer key) {
        this.key = key;
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
