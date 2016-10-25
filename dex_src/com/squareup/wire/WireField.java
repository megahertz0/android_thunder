package com.squareup.wire;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WireField {

    public enum Label {
        REQUIRED,
        OPTIONAL,
        REPEATED,
        ONE_OF,
        PACKED;

        static {
            REQUIRED = new com.squareup.wire.WireField.Label("REQUIRED", 0);
            OPTIONAL = new com.squareup.wire.WireField.Label("OPTIONAL", 1);
            REPEATED = new com.squareup.wire.WireField.Label("REPEATED", 2);
            ONE_OF = new com.squareup.wire.WireField.Label("ONE_OF", 3);
            PACKED = new com.squareup.wire.WireField.Label("PACKED", 4);
            a = new com.squareup.wire.WireField.Label[]{REQUIRED, OPTIONAL, REPEATED, ONE_OF, PACKED};
        }
    }
}
