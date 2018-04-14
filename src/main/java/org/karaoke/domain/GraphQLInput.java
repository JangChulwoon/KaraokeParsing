package org.karaoke.domain;

import java.util.Collections;
import java.util.Map;

public class GraphQLInput {
    private String key;
    private String query;
    private Map<String, Object> variable;

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

    public void setVariable(Map input) {
        this.variable = Collections.synchronizedMap(input);
    }
}
