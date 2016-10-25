package com.xunlei.downloadprovider.b.b;

import com.xunlei.downloadprovider.b.b.d.a;

// compiled from: BpClientDataLoader.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, byte[] bArr) {
        Object obj = null;
        if (i == 200) {
            String str = new String(bArr);
            if (this.a.b != null) {
                obj = this.a.b.parse(bArr);
                this.a.c = this.a.b.getError();
            }
        } else {
            this.a.c = i;
        }
        if (this.a.a != null) {
            this.a.a.a(this.a.c, obj);
        }
    }
}
