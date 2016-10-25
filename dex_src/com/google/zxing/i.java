package com.google.zxing;

// compiled from: MultiFormatWriter.java
final /* synthetic */ class i {
    static final /* synthetic */ int[] a;

    static {
        a = new int[BarcodeFormat.values().length];
        try {
            a[BarcodeFormat.EAN_8.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[BarcodeFormat.EAN_13.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[BarcodeFormat.UPC_A.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[BarcodeFormat.QR_CODE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[BarcodeFormat.CODE_39.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[BarcodeFormat.CODE_128.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[BarcodeFormat.ITF.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[BarcodeFormat.PDF_417.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[BarcodeFormat.CODABAR.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[BarcodeFormat.DATA_MATRIX.ordinal()] = 10;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[BarcodeFormat.AZTEC.ordinal()] = 11;
        } catch (NoSuchFieldError e11) {
        }
    }
}
