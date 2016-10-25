package com.google.zxing.qrcode.decoder;

public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    private static final ErrorCorrectionLevel[] a;
    private final int b;

    static {
        L = new ErrorCorrectionLevel("L", 0, 1);
        M = new ErrorCorrectionLevel("M", 1, 0);
        Q = new ErrorCorrectionLevel("Q", 2, 3);
        H = new ErrorCorrectionLevel("H", 3, 2);
        c = new ErrorCorrectionLevel[]{L, M, Q, H};
        a = new ErrorCorrectionLevel[]{M, L, H, Q};
    }

    private ErrorCorrectionLevel(int i) {
        this.b = i;
    }

    public final int getBits() {
        return this.b;
    }

    public static ErrorCorrectionLevel forBits(int i) {
        if (i >= 0 && i < a.length) {
            return a[i];
        }
        throw new IllegalArgumentException();
    }
}
