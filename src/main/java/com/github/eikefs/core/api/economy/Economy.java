package com.github.eikefs.core.api.economy;

import com.github.eikefs.core.api.economy.transaction.Transaction;
import com.github.eikefs.core.api.economy.transaction.event.TransactionEvent;
import com.github.eikefs.core.api.economy.transaction.event.TransactionEvent.TransactionState;
import com.github.eikefs.core.api.economy.transaction.controller.TransactionController;
import com.github.eikefs.core.sql.database.DB;
import com.github.eikefs.core.sql.database.Document;
import com.github.eikefs.core.sql.query.DeleteQuery;
import com.github.eikefs.core.sql.query.InsertQuery;
import com.github.eikefs.core.sql.query.SelectQuery;
import com.github.eikefs.core.sql.query.UpdateQuery;
import com.github.eikefs.core.sql.table.TableField;
import com.github.eikefs.core.sql.table.TableFieldConstraint;
import com.github.eikefs.core.sql.table.TableRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Economy extends TransactionController implements TableRepository<EconomyPlayer> {

    private final DB database;
    private final Cache<UUID, EconomyPlayer> cache;

    public Economy(DB database) {
        super(database);
        initialize(database);

        this.database = database;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(25, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public void apply(Transaction transaction) {
        EconomyPlayer author = transaction.getAuthor();
        EconomyPlayer target = transaction.getTarget();
        double amount = transaction.getAmount();
        
        TransactionState state = TransactionState.ACCEPT;

        if (amount > author.getAmount()) state = TransactionState.DENY;
        else {
            if (target != null) {
                target.setAmount(target.getAmount() + amount);
            }

            author.setAmount(author.getAmount() - amount);

            log(transaction);
        }

        TransactionEvent event = new TransactionEvent(transaction, state);

        Bukkit.getPluginManager().callEvent(event);
    }

    public DB getDatabase() {
        return database;
    }

    @Override
    public String tableName() {
        return "economy";
    }

    @Override
    public TableField[] fields() {
        return new TableField[] {
                TableField.builder()
                        .name("id")
                        .type("char")
                        .size(36)
                        .primary()
                        .constraint(TableFieldConstraint.NOT_NULL),
                TableField.builder()
                        .name("amount")
                        .type("integer")
        };
    }

    @Override
    public void insert(Document document) {
        InsertQuery query = new InsertQuery()
                .into(tableName())
                .insert(document);

        database.update(query);
    }

    @Override
    public void update(Document key, Document document) {
        database.update(
                new UpdateQuery()
                .into(tableName())
                .sets(document.toMap())
                .where("id", "=", key.getObject("id"))
        );
    }

    @Override
    public void delete(Document key) {
        database.update(
                new DeleteQuery()
                .from(tableName())
                .where("id", "=", key.getObject("id"))
        );
    }

    @Override
    public EconomyPlayer get(Document key) {
        UUID id = UUID.fromString(key.getString("id"));
        EconomyPlayer player;

        if (cache.asMap().containsKey(id)) {
            player = cache.getIfPresent(id);
        } else {
            SelectQuery query = (SelectQuery) new SelectQuery()
                    .select("amount")
                    .from(tableName())
                    .where("id", "=", id);

            double amount = database.queryOne(query).getDouble("amount");

            player = new EconomyPlayer(id, amount);

            cache.put(id, player);
        }

        return player;
    }

}
