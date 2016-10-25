package com.xunlei.downloadprovider.ad.splash.b;

import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.ad.splash.a.a;
import com.xunlei.downloadprovider.ad.splash.a.c;
import java.util.List;

// compiled from: SplashGDTAd.java
final class q implements NativeAdListener {
    final /* synthetic */ p a;

    q(p pVar) {
        this.a = pVar;
    }

    public final void onADLoaded(List<NativeADDataRef> list) {
        if (!this.a.g.e) {
            a aVar;
            if (list == null || list.isEmpty()) {
                aVar = null;
            } else {
                NativeADDataRef nativeADDataRef = (NativeADDataRef) list.get(0);
                if (nativeADDataRef != null) {
                    c cVar = new c(nativeADDataRef);
                    cVar.a(Integer.parseInt("1115"));
                    aVar = cVar;
                } else {
                    aVar = null;
                }
            }
            new StringBuilder("splashAdInfo : ").append(aVar == null ? "null" : aVar.toString());
            if (aVar != null) {
                this.a.e();
                this.a.a(aVar, new r(this, aVar));
                return;
            }
            this.a.a(null);
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_tx_fail", "-1");
        }
    }

    public final void onNoAD(int i) {
        if (!this.a.g.e) {
            this.a.a(null);
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_tx_fail", String.valueOf(i));
        }
    }

    public final void onADStatusChanged(NativeADDataRef nativeADDataRef) {
    }

    public final void onADError(NativeADDataRef nativeADDataRef, int i) {
    }
}
