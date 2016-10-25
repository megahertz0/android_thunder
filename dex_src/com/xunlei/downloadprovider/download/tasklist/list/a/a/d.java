package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: BaiduLoader.java
final class d implements BaiduNativeNetworkListener {
    final /* synthetic */ a a;
    final /* synthetic */ c b;

    d(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public final void onNativeLoad(List<NativeResponse> list) {
        if (this.a != null && list != null && list.size() != 0) {
            NativeResponse nativeResponse = (NativeResponse) list.get(0);
            b bVar = new b();
            bVar.b = nativeResponse;
            bVar.d = SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
            bVar.e = nativeResponse.getTitle();
            bVar.q = 3;
            bVar.f = nativeResponse.getDesc();
            bVar.j = nativeResponse.getImageUrl();
            List arrayList = new ArrayList();
            arrayList.add(bVar);
            this.a.a(arrayList);
        }
    }

    public final void onNativeFail(NativeErrorCode nativeErrorCode) {
        if (!(nativeErrorCode == null || this.a == null)) {
            this.a.a(nativeErrorCode.ordinal());
        }
        if (nativeErrorCode != null) {
            a.a("adv_downloadtab_baidu_fail", this.b.b, nativeErrorCode.ordinal());
        } else {
            a.a("adv_downloadtab_baidu_fail", this.b.b, -1);
        }
    }
}
