package org.karaoke.domain;

import java.util.Collections;
import java.util.Map;

public class GraphQLInput {

    private String query;
    private Map variables;

    public String getQuery() {
        return query;
    }

    public GraphQLInput setQuery(String query) {
        this.query = query;
        return this;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = Collections.synchronizedMap(variables);
    }
}
