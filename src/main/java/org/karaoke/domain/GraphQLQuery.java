package org.karaoke.domain;


import java.util.Map;

public class GraphQLQuery {

    private String key;
    private Map<String, Object> variables;

    public GraphQLQuery() {
    }

    public GraphQLQuery(String key, Map<String, Object> variables) {
        this.key = key;
        this.variables = variables;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

}
