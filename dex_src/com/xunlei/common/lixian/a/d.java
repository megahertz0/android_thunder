package com.xunlei.common.lixian.a;

import android.content.Context;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLixianUtil;

public final class d {
    private static final String a = "http://service.lixian.vip.xunlei.com:80";
    private static d b;
    private Context c;

    static {
        b = new d();
    }

    private d() {
        this.c = null;
    }

    public static d a() {
        return b;
    }

    private static void b() {
    }

    public final void a(Context context) {
        this.c = context;
    }

    public final void a(byte[] bArr, BaseHttpClientListener baseHttpClientListener) {
        XLLixianUtil.getInstance().getHttpClient().post(this.c, a, null, bArr, baseHttpClientListener);
    }
}
