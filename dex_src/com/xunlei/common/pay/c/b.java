package com.xunlei.common.pay.c;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.XLPayType;
import com.xunlei.common.pay.a.c;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLPriceParam;
import org.apache.http.Header;

// compiled from: XLPriceTask.java
public class b extends e {
    private static final String c;
    private XLPriceParam d;
    private com.xunlei.common.pay.a.b e;

    static {
        c = b.class.getSimpleName();
    }

    public b() {
        this.d = null;
        this.e = null;
    }

    public final void c() {
        String b = this.e.b();
        XLLog.v(c, new StringBuilder("generatePriceUrl url = ").append(b).toString());
        f.a().e().get(f.a().d(), b, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(c, new StringBuilder("generatePriceUrl buffer = ").append(str).toString());
                f.a().a(Integer.valueOf(XLPayType.XL_GET_PRICE), Integer.valueOf(0), XLPayErrorCode.getErrorDesc(0), b.this.f(), Integer.valueOf(b.this.b()), XLUtilTools.parseJSONPString(str));
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(c, new StringBuilder("generatePriceUrl error = ").append(th.getMessage()).toString());
                f.a().a(Integer.valueOf(XLPayType.XL_GET_PRICE), Integer.valueOf(XLPayErrorCode.XLP_GET_PRICE_ERROR), XLPayErrorCode.getErrorDesc(0), b.this.f(), Integer.valueOf(b.this.b()), null);
            }
        });
    }

    public final void a(XLPayParam xLPayParam) {
        this.d = (XLPriceParam) xLPayParam;
        this.e = c.a(this.d);
    }

    public final XLPayParam d() {
        return this.d;
    }

    private void a(int i, String str) {
        f.a().a(Integer.valueOf(XLPayType.XL_GET_PRICE), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), str);
    }
}
