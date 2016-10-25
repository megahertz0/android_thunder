package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// compiled from: ByteArrayPool.java
public final class b {
    protected static final Comparator<byte[]> a;
    private List<byte[]> b;
    private List<byte[]> c;
    private int d;
    private final int e;

    static {
        a = new c();
    }

    public b(int i) {
        this.b = new LinkedList();
        this.c = new ArrayList(64);
        this.d = 0;
        this.e = i;
    }

    public final synchronized byte[] a(int i) {
        byte[] bArr;
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            bArr = (byte[]) this.c.get(i2);
            if (bArr.length >= i) {
                this.d -= bArr.length;
                this.c.remove(i2);
                this.b.remove(bArr);
                break;
            }
        }
        bArr = new byte[i];
        return bArr;
    }

    public final synchronized void a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.e) {
                this.b.add(bArr);
                int binarySearch = Collections.binarySearch(this.c, bArr, a);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.c.add(binarySearch, bArr);
                this.d += bArr.length;
                a();
            }
        }
    }

    private synchronized void a() {
        while (this.d > this.e) {
            byte[] bArr = (byte[]) this.b.remove(0);
            this.c.remove(bArr);
            this.d -= bArr.length;
        }
    }
}
