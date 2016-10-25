package com.xunlei.downloadprovider.util.b;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadlib.parameter.XLConstant.XLErrorCode;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CrackUtil.java
final class e extends WebViewClient {
    final /* synthetic */ a a;
    final /* synthetic */ Object b;
    final /* synthetic */ f c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;
    final /* synthetic */ String h;
    final /* synthetic */ c i;

    e(c cVar, a aVar, Object obj, f fVar, int i, String str, String str2, String str3, String str4) {
        this.i = cVar;
        this.a = aVar;
        this.b = obj;
        this.c = fVar;
        this.d = i;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = str4;
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.a.mCancel = true;
        c.b(this.i).removeMessages(XLErrorCode.APPKEY_CHECKER_ERROR, this.b);
        if (this.c != null) {
            switch (this.d) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    this.c.a(XLPayErrorCode.XLP_GATE_GEN_ERROR, null);
                case SimpleLog.LOG_LEVEL_DEBUG:
                    this.c.a(XLPayErrorCode.XLP_GATE_GEN_ERROR, this.e, this.f, this.g, this.h, null);
                default:
                    break;
            }
        }
    }
}
