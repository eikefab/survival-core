package com.github.eikefs.core.sql.query;

import com.github.eikefs.core.sql.Query;
import com.github.eikefs.core.sql.database.Document;

import java.util.Arrays;
import java.util.LinkedList;

public class InsertQuery extends Query {

    private final LinkedList<Object> values = new LinkedList<>();

    @Override
    public InsertQuery append(String to) {
        return (InsertQuery) super.append(to);
    }

    public InsertQuery into(String table) {
        return append("insert into " + table + " values (");
    }

    public InsertQuery insert(Object... values) {
        this.values.addAll(Arrays.asList(values));

        return this;
    }

    public InsertQuery insert(Document document) {
        return insert(document.toMap().values().toArray());
    }

    @Override
    public String toString() {
        for (int index = 0; index < values.size(); index++) {
            if ((index + 1) == values.size()) {
                append(values.get(index) + ");");
            } else {
                append(values.get(index) + ",");
            }
        }

        return super.getStringBuilder().toString();
    }
}
