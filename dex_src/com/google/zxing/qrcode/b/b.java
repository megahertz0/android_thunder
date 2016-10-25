package com.google.zxing.qrcode.b;

import java.lang.reflect.Array;
import org.android.spdy.SpdyAgent;

// compiled from: ByteMatrix.java
public final class b {
    final byte[][] a;
    public final int b;
    public final int c;

    public b(int i, int i2) {
        this.a = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i2, i});
        this.b = i;
        this.c = i2;
    }

    public final byte a(int i, int i2) {
        return this.a[i2][i];
    }

    public final void a(int i, int i2, int i3) {
        this.a[i2][i] = (byte) i3;
    }

    public final void a(int i, int i2, boolean z) {
        this.a[i2][i] = (byte) (z ? 1 : 0);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(((this.b * 2) * this.c) + 2);
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                switch (this.a[i][i2]) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        stringBuilder.append(" 0");
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        stringBuilder.append(" 1");
                        break;
                    default:
                        stringBuilder.append("  ");
                        break;
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
