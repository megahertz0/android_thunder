package com.xunlei.downloadprovider.search;

import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.search.a.a;
import java.util.List;

// compiled from: SearchAdHelper.java
final class b implements NativeAdListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onNoAD(int i) {
        synchronized (a.class) {
            if (this.a.c.isEmpty()) {
                return;
            }
            a aVar = (a) this.a.c.firstElement();
            this.a.c.remove(0);
            aVar.a();
        }
    }

    public final void onADStatusChanged(NativeADDataRef nativeADDataRef) {
    }

    public final void onADError(NativeADDataRef nativeADDataRef, int i) {
    }

    public final void onADLoaded(List<NativeADDataRef> list) {
        synchronized (a.class) {
            if (this.a.c.isEmpty() || list == null || list.isEmpty()) {
                return;
            }
            a aVar = (a) this.a.c.pop();
            aVar.a((NativeADDataRef) list.get(0));
        }
    }
}
