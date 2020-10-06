package com.github.eikefs.core;

import com.github.eikefs.core.api.economy.Economy;
import com.github.eikefs.core.sql.database.DB;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalCorePlugin extends JavaPlugin {

    private static DB database;

    static {
        database = null; // TODO: Connection
    }

    @Override
    public void onEnable() {
        Economy economy = new Economy(database);



    }
}
