package com.xunlei.downloadprovider.model.protocol.e;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.f;
import java.util.List;

// compiled from: ShortTimeVideoManager.java
final class e implements a<f> {
    final /* synthetic */ a$c a;
    final /* synthetic */ int b;
    final /* synthetic */ List c;
    final /* synthetic */ a$a d;
    final /* synthetic */ a e;

    e(a aVar, a$c com_xunlei_downloadprovider_model_protocol_e_a_c, int i, List list, a$a com_xunlei_downloadprovider_model_protocol_e_a_a) {
        this.e = aVar;
        this.a = com_xunlei_downloadprovider_model_protocol_e_a_c;
        this.b = i;
        this.c = list;
        this.d = com_xunlei_downloadprovider_model_protocol_e_a_a;
    }

    public final /* synthetic */ void a(Object obj) {
        f fVar = (f) obj;
        this.a.r = fVar;
        this.a.m = fVar.c;
        if (this.b == this.c.size() - 1) {
            this.d.a();
        }
    }

    public final void a(b bVar) {
        if (this.b == this.c.size() - 1) {
            this.d.a();
        }
    }
}
