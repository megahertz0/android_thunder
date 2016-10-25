package com.alipay.apmobilesecuritysdk.b;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public final class a {
    private static a b;
    private int a;

    static {
        b = new a();
    }

    public a() {
        this.a = 0;
    }

    public static a a() {
        return b;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final int b() {
        return this.a;
    }

    public final String c() {
        if (com.alipay.b.a.a.a.a.b(null)) {
            return null;
        }
        switch (this.a) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "https://mobilegw.alipay.com/mgw.htm";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
