package com.github.eikefs.core.sql.query;

import com.github.eikefs.core.sql.Query;

public class SelectQuery extends Query {


    public SelectQuery() {
        getStringBuilder().append("select ");
    }

    @Override
    public SelectQuery append(String to) {
        return (SelectQuery) super.append(to);
    }

    public SelectQuery all() {
        return append("*");
    }

    private SelectQuery select(String fieldName, boolean last) {
        append("`").append(fieldName).append("`");

        return last ? this : append(",");
    }

    public SelectQuery select(String... fields) {
        for (int index = 0; index < fields.length; index++) {
            boolean last = (index + 1) == (fields.length);

            select(fields[index], last);
        }

        return this;
    }

    public SelectQuery from(String table) {
        return append(" from ").append(table);
    }

}
