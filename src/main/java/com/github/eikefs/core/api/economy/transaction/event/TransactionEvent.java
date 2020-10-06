package com.github.eikefs.core.api.economy.transaction.event;

import com.github.eikefs.core.api.economy.transaction.Transaction;

public class TransactionEvent {

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
