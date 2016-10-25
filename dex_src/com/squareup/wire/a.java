package com.squareup.wire;

// compiled from: FieldEncoding.java
final /* synthetic */ class a {
    static final /* synthetic */ int[] a;

    static {
        a = new int[FieldEncoding.values().length];
        try {
            a[FieldEncoding.VARINT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[FieldEncoding.FIXED32.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[FieldEncoding.FIXED64.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[FieldEncoding.LENGTH_DELIMITED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
