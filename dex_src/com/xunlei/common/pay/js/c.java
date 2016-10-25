package com.xunlei.common.pay.js;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.pay.js.export.IXLPayJSHandler;
import com.xunlei.common.pay.js.export.XLPayJSInterface;
import com.xunlei.common.pay.js.export.XLPayJSUserInfo;

// compiled from: XLPayJSRegister.java
public class c {
    private static String e = "XLPayJsInstance";
    private String a;
    private WebView b;
    private XLPayJSInterface c;
    private Activity d;
    private a f;
    private IXLPayJSHandler g;
    private XLPayJSUserInfo h;

    // compiled from: XLPayJSRegister.java
    final class AnonymousClass_1 implements Runnable {
        private /* synthetic */ String a;

        AnonymousClass_1(String str) {
            this.a = str;
        }

        public final void run() {
            if (!TextUtils.isEmpty(this.a)) {
                c.this.loadUrl(this.a);
            }
        }
    }

    // compiled from: XLPayJSRegister.java
    class a extends Handler {
        private /* synthetic */ c a;

        public a(c cVar, Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            int i = message.what;
        }
    }

    public c() {
        this.a = c.class.getSimpleName();
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public final boolean a(WebView webView, Activity activity, IXLPayJSHandler iXLPayJSHandler) {
        if (this.b != null || webView == null || activity == null || iXLPayJSHandler == null) {
            return false;
        }
        XLLog.v(this.a, new StringBuilder("register js interface thread = ").append(Thread.currentThread().getId()).toString());
        this.b = webView;
        this.d = activity;
        this.g = iXLPayJSHandler;
        this.h = this.g.handleXLUserInfo();
        this.f = new a(this, Looper.getMainLooper());
        this.b.getSettings().setJavaScriptEnabled(true);
        this.c = new XLPayJSInterface();
        this.c.init(this);
        this.b.addJavascriptInterface(this.c, "XLPayJsInstance");
        return true;
    }

    public final boolean a() {
        if (this.b == null) {
            return false;
        }
        XLLog.v(this.a, "unRegister js interface.");
        if (VERSION.SDK_INT >= 11) {
            this.b.removeJavascriptInterface("XLPayJsInstance");
        }
        this.b = null;
        this.d = null;
        this.f = null;
        this.c.unInit();
        this.c = null;
        return true;
    }

    private WebView d() {
        return this.b;
    }

    public final Activity b() {
        return this.d;
    }

    private Handler e() {
        return this.f;
    }

    public final XLPayJSUserInfo c() {
        return this.h;
    }

    public final void a(String str) {
        this.f.post(new AnonymousClass_1(str));
    }
}
