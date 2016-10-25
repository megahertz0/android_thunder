package com.google.zxing.a.c;

import com.google.zxing.common.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Deque;
import java.util.LinkedList;

// compiled from: State.java
final class g {
    static final g a;
    final int b;
    final int c;
    final int d;
    private final h e;

    static {
        a = new g(h.a, 0, 0, 0);
    }

    private g(h hVar, int i, int i2, int i3) {
        this.e = hVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    final g a(int i, int i2) {
        int i3;
        h a;
        int i4 = this.d;
        h hVar = this.e;
        if (i != this.b) {
            i3 = d.b[this.b][i];
            i3 = i4 + (i3 >> 16);
            a = hVar.a(65535 & i3, i3 >> 16);
        } else {
            i3 = i4;
            a = hVar;
        }
        int i5 = i == 2 ? XZBDevice.DOWNLOAD_LIST_ALL : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        return new g(a.a(i2, i5), i, 0, i5 + i3);
    }

    final g b(int i, int i2) {
        h hVar = this.e;
        int i3 = this.b == 2 ? XZBDevice.DOWNLOAD_LIST_ALL : 5;
        return new g(hVar.a(d.c[this.b][i], i3).a(i2, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED), this.b, 0, (i3 + this.d) + 5);
    }

    final g a(int i) {
        h a;
        h hVar = this.e;
        int i2 = this.b;
        int i3 = this.d;
        if (this.b == 4 || this.b == 2) {
            int i4 = d.b[i2][0];
            i3 += i4 >> 16;
            a = hVar.a(65535 & i4, i4 >> 16);
            i2 = 0;
        } else {
            a = hVar;
        }
        int i5 = (this.c == 0 || this.c == 31) ? R.styleable.Toolbar_collapseIcon : this.c == 62 ? XZBDevice.Pause : XZBDevice.Wait;
        g gVar = new g(a, i2, this.c + 1, i3 + i5);
        return gVar.c == 2078 ? gVar.b(i + 1) : gVar;
    }

    final g b(int i) {
        return this.c == 0 ? this : new g(new b(this.e, i - this.c, this.c), this.b, 0, this.d);
    }

    final boolean a(g gVar) {
        int i = this.d + (d.b[this.b][gVar.b] >> 16);
        if (gVar.c > 0) {
            if (this.c == 0 || this.c > gVar.c) {
                i += 10;
            }
        }
        return i <= gVar.d;
    }

    final a a(byte[] bArr) {
        Deque<h> linkedList = new LinkedList();
        for (h hVar = b(bArr.length).e; hVar != null; hVar = hVar.b) {
            linkedList.addFirst(hVar);
        }
        a aVar = new a();
        for (h hVar2 : linkedList) {
            hVar2.a(aVar, bArr);
        }
        return aVar;
    }

    public final String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{d.a[this.b], Integer.valueOf(this.d), Integer.valueOf(this.c)});
    }
}
