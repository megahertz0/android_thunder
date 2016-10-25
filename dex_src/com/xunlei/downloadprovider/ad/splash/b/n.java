package com.xunlei.downloadprovider.ad.splash.b;

import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.downloadprovider.ad.splash.a.a;
import com.xunlei.downloadprovider.ad.splash.a.b;
import java.util.List;

// compiled from: SplashBaiDuAd.java
final class n implements BaiduNativeNetworkListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void onNativeLoad(List<NativeResponse> list) {
        if (!this.a.g.e) {
            a aVar;
            if (list == null || list.isEmpty()) {
                aVar = null;
            } else {
                NativeResponse nativeResponse = (NativeResponse) list.get(0);
                if (nativeResponse != null) {
                    b bVar = new b(nativeResponse);
                    bVar.a(Integer.parseInt("1115"));
                    aVar = bVar;
                } else {
                    aVar = null;
                }
            }
            new StringBuilder("splashAdInfo : ").append(aVar == null ? "null" : aVar.toString());
            if (aVar != null) {
                this.a.e();
                this.a.a(aVar, new o(this, aVar));
                return;
            }
            this.a.a(null);
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_baidu_fail", "-1");
        }
    }

    public final void onNativeFail(NativeErrorCode nativeErrorCode) {
        if (!this.a.g.e) {
            this.a.a(null);
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_baidu_fail", String.valueOf(nativeErrorCode));
        }
    }
}
