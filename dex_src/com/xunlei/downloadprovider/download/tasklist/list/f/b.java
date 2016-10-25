package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.os.Handler;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.util.k;
import org.android.spdy.SpdyProtocol;

// compiled from: RedEnvelopeControl.java
final class b extends k {
    final /* synthetic */ a a;

    b(a aVar, Handler handler) {
        this.a = aVar;
        super(handler);
    }

    public final void a() {
        a aVar = this.a.b;
        aVar.u--;
        long j = this.a.b.u;
        a aVar2 = this.a;
        if (j < 60) {
            aVar2.e = 0;
            aVar2.f = (int) j;
        } else if (j % 60 == 0) {
            aVar2.e = ((int) j) / 60;
            aVar2.f = 0;
        } else {
            aVar2.e = ((int) j) / 60;
            aVar2.f = ((int) j) % 60;
        }
        if (aVar2.e >= 10 || aVar2.e < 0) {
            aVar2.g = aVar2.e;
        } else {
            aVar2.g = new StringBuilder("0").append(aVar2.e).toString();
        }
        if (aVar2.f >= 10 || aVar2.f < 0) {
            aVar2.h = aVar2.f;
        } else {
            aVar2.h = new StringBuilder("0").append(aVar2.f).toString();
        }
        this.a.c.b.setText(new StringBuilder("\u5269").append(this.a.g).append(":").append(this.a.h).toString());
        new StringBuilder("\u5269").append(this.a.g).append(":").append(this.a.h);
        if (j == -1) {
            a aVar3 = this.a;
            a.i = null;
            if (aVar3.j != null) {
                aVar3.j.b();
            }
            aVar3.j = null;
            aVar3.d = null;
            if (aVar3.c != null) {
                aVar3.c.a(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (aVar3.b != null) {
                aVar3.b.s = false;
                aVar3.b.t = true;
            }
        }
    }
}
