package com.google.zxing;

import com.umeng.a;

// compiled from: BinaryBitmap.java
public final class b {
    public final a a;
    private com.google.zxing.common.b b;

    public b(a aVar) {
        this.a = aVar;
    }

    public final com.google.zxing.common.b a() throws j {
        if (this.b == null) {
            this.b = this.a.a();
        }
        return this.b;
    }

    public final String toString() {
        try {
            return a().toString();
        } catch (j e) {
            return a.d;
        }
    }
}
