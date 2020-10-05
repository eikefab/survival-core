package com.github.eikefs.core.sql.table;

import com.github.eikefs.core.sql.database.DB;
import com.github.eikefs.core.sql.database.Document;

public interface TableRepository<T> {

    default void initialize(DB database) {

    }

    String tableName();
    TableField[] fields();

    void insert(Document document);
    void update(Document key, Document document);
    void delete(Document key);
    T get(Document key);

}
