package com.xunlei.downloadprovider.web.core;

import android.os.Message;
import com.xunlei.downloadprovider.util.sniff.f.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: DefaultJsCallbackListener.java
final class f implements a {
    final /* synthetic */ String a;
    final /* synthetic */ c$a b;

    f(c$a com_xunlei_downloadprovider_web_core_c_a, String str) {
        this.b = com_xunlei_downloadprovider_web_core_c_a;
        this.a = str;
    }

    public final void a(String str) {
        String a = c.a(this.a, str, c.b(this.b.e).a, this.b.c, null);
        if (a != null && !a.equals(BuildConfig.VERSION_NAME) && this.b.d != null) {
            Message obtainMessage = this.b.d.obtainMessage(100220);
            obtainMessage.obj = a;
            this.b.d.sendMessage(obtainMessage);
        }
    }
}
