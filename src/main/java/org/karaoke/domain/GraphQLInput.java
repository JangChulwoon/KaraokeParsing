package org.karaoke.domain;

import java.util.Map;

public class GraphQLInput {
    private String key;
    private String query;
    private Map variable;

    public String getKey() {
        return key;
    }

    public GraphQLInput setKey(String key) {
        this.key = key;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public GraphQLInput setQuery(String query) {
        this.query = query;
        return this;
    }

    public Map getVariable() {
        return variable;
    }

    // 이코드 조금 위험함
    public void setVariable(Map variable) {
        this.variable = variable;
    }
}
