package com.xunlei.downloadprovider.download.taskDetail.a;

import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// compiled from: TaskDetailGDTAdModel.java
public class e implements a {
    private static final String a;

    static {
        a = e.class.getSimpleName();
    }

    public final void a(int i, b<h> bVar) {
        new NativeAD(BrothersApplication.a(), "1104872693", "5000014347729495", new f(this, i, bVar)).loadAD(i * 3);
        String str = "adv_download_detail_tx_request";
        a.a(g.a("android_advertise", str, str));
    }

    static /* synthetic */ List a(List list, int i) {
        List arrayList = new ArrayList();
        if (!(list == null || list.isEmpty())) {
            Set hashSet = new HashSet();
            for (NativeADDataRef nativeADDataRef : list) {
                if (nativeADDataRef != null) {
                    g gVar = new g(nativeADDataRef);
                    if (hashSet.add(gVar.c())) {
                        arrayList.add(gVar);
                    }
                }
                if (arrayList.size() >= i) {
                    break;
                }
            }
        }
        return arrayList;
    }
}
