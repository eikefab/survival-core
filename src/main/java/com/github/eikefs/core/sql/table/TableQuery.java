package com.github.eikefs.core.sql.table;

import com.github.eikefs.core.sql.Query;

public class TableQuery extends Query {

    @Override
    public TableQuery append(String string) {
        return (TableQuery) super.append(string);
    }

    public TableQuery create(String name) {
        return append("create table " + name + " ");
    }

    public TableQuery createIfNotExists(String name) {
        return append("create table if not exists " + name + " ");
    }

    public TableQuery add(TableField... fields) {
        for (int index = 0; index < fields.length; index++) {
            boolean last = (index + 1) == fields.length;
            TableField field = fields[index];

            if (!last) append(field.build());
            else append(field.build() + ")");
        }

        return this;
    }

}
