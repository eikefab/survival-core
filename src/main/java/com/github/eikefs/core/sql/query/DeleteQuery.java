package com.github.eikefs.core.sql.query;

import com.github.eikefs.core.sql.Query;

public class DeleteQuery extends Query {

    @Override
    public DeleteQuery append(String to) {
        return (DeleteQuery) super.append(to);
    }

    public DeleteQuery from(String table) {
        return append("delete from " + table + " ");
    }

}
