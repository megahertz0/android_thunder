package com.google.gson;

public enum LongSerializationPolicy {
    DEFAULT {
        public final JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING {
        public final JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
