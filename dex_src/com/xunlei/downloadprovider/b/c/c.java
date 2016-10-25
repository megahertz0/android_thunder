package com.xunlei.downloadprovider.b.c;

import com.xunlei.downloadprovider.b.c.g.b;
import java.util.List;
import java.util.Map;

// compiled from: BpDataLoader.java
final class c implements b {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Map<String, List<String>> map, byte[] bArr) {
        Object obj = null;
        if (i != 200) {
            this.a.a = i;
        } else if (this.a.mParser != null) {
            obj = this.a.mParser.parse(bArr);
            this.a.a = this.a.mParser.getError();
        }
        if (a.a(this.a) != null) {
            a.a(this.a).a(this.a.a, obj, map);
        }
    }
}
