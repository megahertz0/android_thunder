package com.xunlei.downloadprovider.web.core;

import android.os.Message;
import com.xunlei.downloadprovider.util.sniff.f.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;

// compiled from: DefaultJsCallbackListener.java
final class e implements a {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ c$a c;

    e(c$a com_xunlei_downloadprovider_web_core_c_a, String str, String str2) {
        this.c = com_xunlei_downloadprovider_web_core_c_a;
        this.a = str;
        this.b = str2;
    }

    public final void a(String str) {
        String str2;
        try {
            str2 = new String((byte[]) this.c.a, this.a);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str2 = null;
        }
        str2 = c.a(this.b, str, c.b(this.c.e).a, this.c.c, str2);
        if (str2 != null && !str2.equals(BuildConfig.VERSION_NAME) && this.c.d != null) {
            Message obtainMessage = this.c.d.obtainMessage(100220);
            obtainMessage.obj = str2;
            this.c.d.sendMessage(obtainMessage);
        }
    }
}
