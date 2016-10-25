package com.xunlei.downloadprovider.ad.common.e;

import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: GDTNavLoader.java
final class c implements NativeAdListener {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ b c;

    c(b bVar, a aVar, String str) {
        this.c = bVar;
        this.a = aVar;
        this.b = str;
    }

    public final void onADLoaded(List<NativeADDataRef> list) {
        if (this.a != null) {
            List arrayList = new ArrayList();
            if (!(list == null || list.size() == 0)) {
                for (NativeADDataRef nativeADDataRef : list) {
                    arrayList.add(new d(this.b, nativeADDataRef));
                }
            }
            this.a.a(arrayList);
        }
    }

    public final void onNoAD(int i) {
        if (this.a != null) {
            this.a.a(i, "GDT LOAD FAIL");
        }
    }

    public final void onADStatusChanged(NativeADDataRef nativeADDataRef) {
        if (this.a instanceof a) {
            ((a) this.a).a(new d(this.b, nativeADDataRef));
        }
    }

    public final void onADError(NativeADDataRef nativeADDataRef, int i) {
        if (this.a instanceof a) {
            d dVar = new d(this.b, nativeADDataRef);
        }
    }
}
