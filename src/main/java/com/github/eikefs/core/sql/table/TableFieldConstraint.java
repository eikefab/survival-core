package com.github.eikefs.core.sql.table;

public enum TableFieldConstraint {

    NOT_NULL("not null"),
    AUTO_INCREMENT("auto_increment");

    private final String sqlSyntax;

    TableFieldConstraint(String sqlSyntax) {
        this.sqlSyntax = sqlSyntax;
    }

    @Override
    public String toString() {
        return sqlSyntax;
    }

}
