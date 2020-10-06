package com.github.eikefs.core.api.economy.transaction.listener;

import com.github.eikefs.core.Message;
import com.github.eikefs.core.api.economy.EconomyPlayer;
import com.github.eikefs.core.api.economy.transaction.Transaction;
import com.github.eikefs.core.api.economy.transaction.event.TransactionEvent.TransactionState;
import com.github.eikefs.core.api.economy.transaction.event.TransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TransactionListener implements Listener {

    @EventHandler
    private void handle(TransactionEvent event) {
        Transaction transaction = event.getTransaction();
        TransactionState state = event.getState();

        EconomyPlayer ecoAuthor = transaction.getAuthor();
        EconomyPlayer ecoTarget = transaction.getTarget();

        if (ecoTarget != null) {
            if (ecoAuthor != null) {
                // player to player

                Player author = Bukkit.getPlayer(ecoAuthor.getId());
                Player target = Bukkit.getPlayer(ecoTarget.getId());

                if (author.getUniqueId() == target.getUniqueId()) {
                    author.sendMessage(Message.sendMoneyToHimself);

                    return;
                }


                return;
            }

            // admin to player


        } else {
            // player to admin
        }

    }

}
