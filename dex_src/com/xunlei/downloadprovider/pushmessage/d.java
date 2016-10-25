package com.xunlei.downloadprovider.pushmessage;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.downloadprovider.pushmessage.d.g;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PushResultDispatcher.java
public final class d {
    public static void a(a aVar) {
        if (aVar != null) {
            switch (aVar.s) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    String str = aVar.r;
                    String str2 = aVar.l;
                    c a = com.xunlei.downloadprovidercommon.a.a.a("android_push", "push_pop");
                    a.a(JsInterface.FUNPLAY_AD_TRPE, str);
                    a.a("messageid", str2);
                    g.a(a);
                default:
                    break;
            }
        }
    }

    public static void a(Context context, a aVar) {
        com.nostra13.universalimageloader.core.d a = com.nostra13.universalimageloader.core.d.a();
        String str = aVar.m;
        if (TextUtils.isEmpty(str)) {
            str = aVar.n;
        }
        a.b(str, null, null, new e(context, aVar));
    }
}
