package com.google.zxing.qrcode.b;

import com.google.zxing.qrcode.decoder.Mode;

// compiled from: Encoder.java
final /* synthetic */ class d {
    static final /* synthetic */ int[] a;

    static {
        a = new int[Mode.values().length];
        try {
            a[Mode.NUMERIC.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Mode.ALPHANUMERIC.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Mode.BYTE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[Mode.KANJI.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
