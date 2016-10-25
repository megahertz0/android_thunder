package com.xunlei.downloadprovider.ad.splash.b;

import android.view.ViewGroup;
import com.umeng.a;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: SplashAdFactory.java
public final class k {
    public static a a(int i, AD_TYPE ad_type, BaseActivity baseActivity, ViewGroup viewGroup, l lVar, d dVar) {
        switch (AnonymousClass_1.a[ad_type.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return a(i, baseActivity, viewGroup, lVar, dVar);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return new m(i, baseActivity, "2865973", viewGroup, lVar, dVar);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return new z(i, baseActivity, "Fak6FT8TMi", viewGroup, lVar, dVar);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return new s(i, baseActivity, a.d, viewGroup, lVar, dVar);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return new ab(i, baseActivity, "1115", viewGroup, lVar, dVar);
            default:
                return a(i, baseActivity, viewGroup, lVar, dVar);
        }
    }

    private static a a(int i, BaseActivity baseActivity, ViewGroup viewGroup, l lVar, d dVar) {
        return new p(i, baseActivity, "8020418483671194", viewGroup, lVar, dVar);
    }
}
