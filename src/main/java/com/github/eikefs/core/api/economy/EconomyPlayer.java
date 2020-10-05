package com.github.eikefs.core.api.economy;

import java.util.UUID;

public class EconomyPlayer {

    private final UUID id;
    private double amount;

    public EconomyPlayer(UUID id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "id=" + id +
               ",amount=" + amount;
    }

}
