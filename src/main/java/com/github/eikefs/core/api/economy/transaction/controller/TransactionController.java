package com.github.eikefs.core.api.economy.transaction.controller;

import com.github.eikefs.core.api.economy.EconomyPlayer;
import com.github.eikefs.core.api.economy.transaction.Transaction;
import com.github.eikefs.core.sql.database.DB;
import com.github.eikefs.core.sql.database.Document;
import com.github.eikefs.core.sql.query.InsertQuery;

public abstract class TransactionController {

    private static final String TABLE_NAME = "transaction_logs";

    private final DB database;

    public TransactionController(DB database) {
        this.database = database;
    }

    public void log(Transaction transaction) {
        EconomyPlayer target = transaction.getTarget();

        database.update(
                new InsertQuery()
                .into(TABLE_NAME)
                .insert(new Document()
                        .insert("author", transaction.getAuthor())
                        .insert("target", target == null ? "admin" : target)
                        .insert("amount", transaction.getAmount())
                ));
    }

    public abstract void apply(Transaction transaction);

}
