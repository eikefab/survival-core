package com.github.eikefs.core.serializer;

public interface Serializer<T> {

    T deserialize(String serialized);
    String serialize(T object);

}
