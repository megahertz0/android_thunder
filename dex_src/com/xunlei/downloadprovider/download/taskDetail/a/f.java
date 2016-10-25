package com.xunlei.downloadprovider.download.taskDetail.a;

import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.ad.recommend.a.b.a;
import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import java.util.Arrays;
import java.util.List;

// compiled from: TaskDetailGDTAdModel.java
final class f implements NativeAdListener {
    final /* synthetic */ int a;
    final /* synthetic */ b b;
    final /* synthetic */ e c;

    f(e eVar, int i, b bVar) {
        this.c = eVar;
        this.a = i;
        this.b = bVar;
    }

    public final void onADLoaded(List<NativeADDataRef> list) {
        e.a;
        new StringBuilder("onAdLoaded.gdtNativeSize: ").append(list == null ? 0 : list.size());
        List a = e.a((List) list, this.a);
        e.a;
        new StringBuilder("onADLoaded.ads: ").append(Arrays.toString(a.toArray()));
        if (a.isEmpty()) {
            if (this.b != null) {
                this.b.a(a.a);
            }
            ThunderReporter.a.c("-1");
        } else if (this.b != null) {
            this.b.a(a);
        }
    }

    public final void onNoAD(int i) {
        e.a;
        if (this.b != null) {
            this.b.a(new a(i, "\u5e7f\u70b9\u901a\u670d\u52a1\u5668\u9519\u8bef"));
        }
        ThunderReporter.a.c(String.valueOf(i));
    }

    public final void onADStatusChanged(NativeADDataRef nativeADDataRef) {
    }

    public final void onADError(NativeADDataRef nativeADDataRef, int i) {
    }
}
