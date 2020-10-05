package com.github.eikefs.core.sql.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableField {

    private String name;
    private String type;
    private int size;
    private boolean primary;
    private List<TableFieldConstraint> constraints = new ArrayList<>();
    private String foreignReference;

    public static TableField builder() {
        return new TableField();
    }

    public TableField name(String name) {
        this.name = name;

        return this;
    }

    public TableField type(String type) {
        this.type = type;

        return this;
    }

    public TableField size(int size) {
        this.size = size;

        return this;
    }

    public TableField constraint(TableFieldConstraint constraint) {
        this.constraints.add(constraint);

        return this;
    }

    public TableField foreign(String table, String field) {
        this.foreignReference = "foreign key `" + name + "` references " + table + " (`" + field + "`)";

        return this;
    }

    public TableField primary() {
        return this;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();

        nonNullable(name, type);

        stringBuilder.append("`")
                     .append(name)
                     .append("` ")
                     .append(type);

        if (size >= 1) {
            stringBuilder.append("(").append(size).append(")");
        }

        if (foreignReference != null) {
            stringBuilder.append(", ").append(foreignReference);
        }

        if (primary) {
            stringBuilder.append(", primary key (`").append(name).append("`)");
        }

        return stringBuilder.toString();
    }

    private <T> T[] nonNullable(T... fields) {
        for (T field : fields) {
            Objects.requireNonNull(field);
        }

        return fields;
    }

}
