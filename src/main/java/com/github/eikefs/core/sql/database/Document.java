package com.github.eikefs.core.sql.database;

import com.github.eikefs.core.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;

public class Document {

    private final Map<String, Object> values = new HashMap<>();

    public Map<String, Object> toMap() {
        return values;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public Object getObject(String key) {
        return toMap().get(key);
    }

    public String getString(String key) {
        return toMap().get(key).toString();
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public float getFloat(String key) {
        return Float.parseFloat(getString(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    public <T> T get(Serializer<T> serializer, String key) {
        return serializer.deserialize(getString(key));
    }

    public Document insert(String key, Object value) {
        toMap().put(key, value);

        return this;
    }

}
