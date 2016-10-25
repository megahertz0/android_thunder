package com.xunlei.downloadprovider.ad.common.a;

import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: BaiduNavLoader.java
final class b implements BaiduNativeNetworkListener {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    b(a aVar, a aVar2, String str) {
        this.c = aVar;
        this.a = aVar2;
        this.b = str;
    }

    public final void onNativeLoad(List<NativeResponse> list) {
        if (this.a != null) {
            List arrayList = new ArrayList();
            if (!(list == null || list.size() == 0)) {
                for (NativeResponse nativeResponse : list) {
                    arrayList.add(new c(this.b, nativeResponse));
                }
            }
            this.a.a(arrayList);
        }
    }

    public final void onNativeFail(NativeErrorCode nativeErrorCode) {
        if (this.a != null) {
            this.a.a(nativeErrorCode.ordinal(), nativeErrorCode.name());
        }
    }
}
