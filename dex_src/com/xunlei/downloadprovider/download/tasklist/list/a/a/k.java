package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.accelerator.utils.ErrorCodeUtils;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: GDTLoader.java
final class k implements NativeAdListener {
    final /* synthetic */ a a;
    final /* synthetic */ j b;

    k(j jVar, a aVar) {
        this.b = jVar;
        this.a = aVar;
    }

    public final void onADLoaded(List<NativeADDataRef> list) {
        if (this.a != null) {
            if (list == null || list.size() == 0) {
                this.a.a((int) ErrorCodeUtils.XLA_PAY_SUCCESS);
                return;
            }
            List arrayList = new ArrayList();
            for (NativeADDataRef nativeADDataRef : list) {
                b bVar = new b();
                bVar.a = nativeADDataRef;
                bVar.d = SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
                bVar.e = nativeADDataRef.getTitle();
                bVar.q = 0;
                bVar.f = nativeADDataRef.getDesc();
                bVar.j = nativeADDataRef.getImgUrl();
                arrayList.add(bVar);
            }
            synchronized (this.a) {
                this.a.a(arrayList);
            }
        }
    }

    public final void onNoAD(int i) {
        a.a("adv_downloadplay_finish_tx_fail", this.b.b, i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public final void onADStatusChanged(NativeADDataRef nativeADDataRef) {
    }

    public final void onADError(NativeADDataRef nativeADDataRef, int i) {
    }
}
