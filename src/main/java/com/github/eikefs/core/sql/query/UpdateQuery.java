package com.github.eikefs.core.sql.query;

import com.github.eikefs.core.sql.Query;

import java.util.HashMap;
import java.util.Map;

public class UpdateQuery extends Query {

    @Override
    public UpdateQuery append(String to) {
        return (UpdateQuery) super.append(to);
    }

    public UpdateQuery into(String table) {
        return append("update " + table + " set ");
    }

    public UpdateQuery sets(Map<String, Object> sets) {
        for (int index = 0; index < sets.size(); index++) {
            String key = sets.keySet().toArray()[index].toString();
            Object value = sets.get(key);

            if ((index + 1) == sets.size()) append("`" + key + "` = " + value);
            else append("`" + key + "` = " + value + ", ");
        }

        return this;
    }

    public UpdateQuery set(String set, Object value) {
        Map<String, Object> sets = new HashMap<>();

        sets.put(set, value);

        return sets(sets);
    }


}
