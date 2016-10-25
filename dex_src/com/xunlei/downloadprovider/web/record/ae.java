package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.web.record.aa.a;
import java.util.ArrayList;

// compiled from: RecordServerUtils.java
final class ae implements a {
    final /* synthetic */ aa a;

    ae(aa aaVar) {
        this.a = aaVar;
    }

    public final void a(String str) {
        if (this.a.c == 1) {
            if (this.a.a(str)) {
                this.a.a.a(null, this.a.c, this.a.d, this.a.b);
            } else {
                this.a.a.a(this.a.c);
            }
        } else if (this.a.c == 2 || this.a.c == 4) {
            ArrayList arrayList = new ArrayList();
            if (this.a.a((Object) str, arrayList)) {
                this.a.a.a(arrayList, this.a.c, this.a.d, false);
            } else {
                this.a.a.a(this.a.c);
            }
            new Thread(new af(this.a, arrayList)).start();
        } else if (this.a.c != 3) {
        } else {
            if (this.a.b(str)) {
                this.a.a.a(null, this.a.c, this.a.d, false);
            } else {
                this.a.a.a(this.a.c);
            }
        }
    }

    public final void a() {
        this.a.a.a(this.a.c);
    }
}
