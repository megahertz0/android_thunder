package com.xunlei.tdlive.a;

import android.content.Context;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: GiftAdapter.java
final class d implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ Context b;

    d(c cVar, Context context) {
        this.a = cVar;
        this.b = context;
    }

    public final void run() {
        JsonWrapper d = this.a.d();
        if (d != null) {
            for (int i = 0; i < d.getLength(); i++) {
                JsonWrapper array = d.getArray(i, "[]");
                for (int i2 = 0; i2 < array.getLength(); i2++) {
                    a.a(this.b).d(array.getObject(i2, "{}").getString(WeiXinShareContent.TYPE_IMAGE, BuildConfig.VERSION_NAME));
                }
            }
        }
    }
}
