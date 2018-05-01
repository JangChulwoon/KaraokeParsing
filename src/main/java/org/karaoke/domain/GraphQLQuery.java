package org.karaoke.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

@RequiredArgsConstructor
public class GraphQLQuery {

    private String query;
    private Map<String, Object> variables;
    private String operationName;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("query", query)
                .append("variables", variables)
                .append("operationName", operationName)
                .toString();
    }
}
