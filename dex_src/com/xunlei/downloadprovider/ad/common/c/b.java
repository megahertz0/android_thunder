package com.xunlei.downloadprovider.ad.common.c;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: InmobiNavLoader.java
final class b implements NativeAdListener {
    final /* synthetic */ String a;
    final /* synthetic */ a b;
    final /* synthetic */ a c;

    b(a aVar, String str, a aVar2) {
        this.c = aVar;
        this.a = str;
        this.b = aVar2;
    }

    public final void onAdLoadSucceeded(InMobiNative inMobiNative) {
        if (inMobiNative != null) {
            c cVar = new c(this.a, inMobiNative);
            if (this.b != null) {
                List arrayList = new ArrayList();
                arrayList.add(cVar);
                this.b.a(arrayList);
            }
        }
    }

    public final void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (this.b != null) {
            this.b.a(inMobiAdRequestStatus.getStatusCode().ordinal(), inMobiAdRequestStatus.getStatusCode().name());
        }
    }

    public final void onAdDismissed(InMobiNative inMobiNative) {
    }

    public final void onAdDisplayed(InMobiNative inMobiNative) {
    }

    public final void onUserLeftApplication(InMobiNative inMobiNative) {
    }
}
