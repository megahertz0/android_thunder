package com.google.zxing.common.b;

import java.util.ArrayList;
import java.util.List;

// compiled from: ReedSolomonEncoder.java
public final class d {
    private final a a;
    private final List<b> b;

    public d(a aVar) {
        this.a = aVar;
        this.b = new ArrayList();
        this.b.add(new b(aVar, new int[]{1}));
    }

    private b a(int i) {
        if (i >= this.b.size()) {
            b bVar = (b) this.b.get(this.b.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                a aVar = this.a;
                int[] iArr = new int[2];
                iArr[0] = 1;
                int i2 = (size - 1) + this.a.m;
                iArr[1] = this.a.i[i2];
                bVar = bVar.b(new b(aVar, iArr));
                this.b.add(bVar);
            }
        }
        return (b) this.b.get(i);
    }

    public final void a(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b a = a(i);
        Object obj = new Object[length];
        System.arraycopy(iArr, 0, obj, 0, length);
        obj = new b(this.a, obj).a(i, 1).c(a)[1].a;
        int length2 = i - obj.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(obj, 0, iArr, length + length2, obj.length);
    }
}
