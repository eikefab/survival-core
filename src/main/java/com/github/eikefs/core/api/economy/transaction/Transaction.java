package com.github.eikefs.core.api.economy.transaction;

import com.github.eikefs.core.api.economy.EconomyPlayer;

public class Transaction {

    private final EconomyPlayer author;
    private final EconomyPlayer target;
    private final double amount;

    public Transaction(EconomyPlayer author, double amount) {
        this.author = author;
        this.target = null;
        this.amount = amount;
    }

    public Transaction(EconomyPlayer author, EconomyPlayer target, double amount) {
        this.author = author;
        this.target = target;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public EconomyPlayer getAuthor() {
        return author;
    }

    public EconomyPlayer getTarget() {
        return target;
    }

}
