package com.github.eikefs.core.api.economy.transaction.event;

import com.github.eikefs.core.api.economy.transaction.Transaction;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TransactionEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public enum TransactionState {

        DENY(0),
        ACCEPT(1);

        private final int state;

        TransactionState(int state) {
            this.state = state;
        }

        public int toInt() {
            return state;
        }

    }

    private final Transaction transaction;
    private final TransactionState state;

    public TransactionEvent(Transaction transaction, TransactionState state) {
        this.transaction = transaction;
        this.state = state;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public TransactionState getState() {
        return state;
    }

}
