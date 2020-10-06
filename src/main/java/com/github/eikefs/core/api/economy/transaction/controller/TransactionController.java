package com.github.eikefs.core.api.economy.transaction.controller;

import com.github.eikefs.core.Message;
import com.github.eikefs.core.Permission;
import com.github.eikefs.core.api.economy.EconomyPlayer;
import com.github.eikefs.core.api.economy.transaction.Transaction;
import com.github.eikefs.core.sql.database.DB;
import com.github.eikefs.core.sql.database.Document;
import com.github.eikefs.core.sql.query.InsertQuery;
import org.bukkit.Bukkit;

public abstract class TransactionController {

    private static final String TABLE_NAME = "transaction_logs";

    private final DB database;

    public TransactionController(DB database) {
        this.database = database;
    }

    public void log(Transaction transaction) {
        EconomyPlayer target = transaction.getTarget();
        EconomyPlayer author = transaction.getAuthor();
        double amount = transaction.getAmount();

        database.update(
                new InsertQuery()
                .into(TABLE_NAME)
                .insert(new Document()
                        .insert("author", author == null ? "admin" : author.getId())
                        .insert("target", target == null ? "admin" : target.getId())
                        .insert("amount", amount)
                ));

        Bukkit.broadcast(String.format(Message.adminLogSpy, String.format(
                "%s received %s from %s",
                target == null ? "admin" : target.getId(),
                amount,
                author == null ? "admin" : author.getId())
        ), Permission.adminSpy);
    }

    public abstract void apply(Transaction transaction);

}
