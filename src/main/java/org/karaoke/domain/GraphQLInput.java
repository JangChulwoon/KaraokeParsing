package org.karaoke.domain;

public class GraphQLInput {
    private String key;
    private String query;

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
}
