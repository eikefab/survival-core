package com.github.eikefs.core.sql;

public abstract class Query {

    private final StringBuilder stringBuilder = new StringBuilder();

    public Query() {}

    public Query(String query) {
        stringBuilder.append(query);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public Query append(String to) {
        getStringBuilder().append(to);

        return this;
    }

    public Query where(String field, String type, Object compare) {
        return append("`" + field + "` ").append(type).append(" '" + compare + "'");
    }

    @Override
    public String toString() {
        return stringBuilder.toString().trim();
    }

}
