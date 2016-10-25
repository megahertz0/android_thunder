package com.google.zxing.pdf417.encoder;

// compiled from: BarcodeRow.java
final class b {
    final byte[] a;
    private int b;

    b(int i) {
        this.a = new byte[i];
        this.b = 0;
    }

    final void a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3;
            int i4 = this.b;
            this.b = i4 + 1;
            byte[] bArr = this.a;
            if (z) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            bArr[i4] = (byte) i3;
        }
    }
}
