package com.tencent.map.b;

import com.umeng.a;

// compiled from: ProGuard
public final class n {
    public byte[] a;
    public String b;

    public n() {
        this.b = "GBK";
    }

    public final String toString() {
        try {
            return new String(this.a, this.b);
        } catch (Exception e) {
            return a.d;
        }
    }
}
