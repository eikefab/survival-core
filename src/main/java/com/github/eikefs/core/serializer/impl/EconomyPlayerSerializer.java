package com.github.eikefs.core.serializer.impl;

import com.github.eikefs.core.api.economy.EconomyPlayer;
import com.github.eikefs.core.serializer.Serializer;

import java.util.UUID;

public class EconomyPlayerSerializer implements Serializer<EconomyPlayer> {

    @Override
    public EconomyPlayer deserialize(String serialized) {
        String[] values = serialized.split(",");

        UUID id = UUID.fromString(values[0].split("=")[1]);
        double amount = Double.parseDouble(values[1].split("=")[1]);

        return new EconomyPlayer(id, amount);
    }

    @Override
    public String serialize(EconomyPlayer object) {
        return object.toString();
    }

}
