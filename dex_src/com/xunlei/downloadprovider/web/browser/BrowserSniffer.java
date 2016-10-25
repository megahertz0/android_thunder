package com.xunlei.downloadprovider.web.browser;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.android.volley.Request;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.browser.BrowserSniffer.a;
import com.xunlei.downloadprovider.web.browser.BrowserSniffer.b;
import com.xunlei.downloadprovider.web.browser.BrowserSniffer.c;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferListener;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer.JsInterfaceThunderSniffer;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public final class BrowserSniffer {
    public final e a;
    boolean b;
    boolean c;
    String d;
    Context e;
    Handler f;
    boolean g;
    int h;
    ThunderSniffer i;
    final SnifferListener j;
    protected final f k;
    protected final d l;
    JsInterfaceSniffer m;
    protected TimerTask n;
    protected TimerTask o;
    boolean p;
    boolean q;
    private boolean r;
    private HashSet<a> s;
    private com.xunlei.downloadprovider.a.h.a t;

    public static interface a {
        void a(BrowserSniffer browserSniffer, float f, Object obj);

        void a(BrowserSniffer browserSniffer, b bVar, Object obj);

        void a(BrowserSniffer browserSniffer, c cVar, Object obj);

        void a(BrowserSniffer browserSniffer, Object obj);

        void b(BrowserSniffer browserSniffer, Object obj);

        void c(BrowserSniffer browserSniffer, Object obj);

        void d(BrowserSniffer browserSniffer, Object obj);
    }

    public class JsInterfaceSniffer extends JsInterfaceThunderSniffer {
        @JavascriptInterface
        public void startSniffing(String str) {
            if (BrowserSniffer.this.i != null && !BrowserSniffer.this.b) {
                BrowserSniffer.this.a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                BrowserSniffer.this.i.startSniffingContent(str);
            }
        }

        @JavascriptInterface
        public void startSniffingURLs(String str) {
            if (BrowserSniffer.this.i != null && !BrowserSniffer.this.b) {
                BrowserSniffer.this.a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                BrowserSniffer.this.i.startSniffingContent(str, true);
            }
        }

        @JavascriptInterface
        public void startSniffingBaiduData(String str) {
            if (BrowserSniffer.this.i != null && !BrowserSniffer.this.b) {
                BrowserSniffer.this.a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                BrowserSniffer.this.i.startSniffingContent(str, true);
            }
        }
    }

    public class b {
        public SniffingResourceGroup a;
        public String b;

        public b(SniffingResourceGroup sniffingResourceGroup, String str) {
            this.a = sniffingResourceGroup;
            this.b = str;
        }
    }

    public class c {
        public String a;
        public SniffingPageResource b;
        public String c;

        public c(String str, SniffingPageResource sniffingPageResource) {
            this.c = str;
            this.b = sniffingPageResource;
            if (sniffingPageResource != null) {
                this.a = sniffingPageResource.mPageUrl;
            }
        }
    }

    class d implements a {
        d() {
        }

        public final void a(BrowserSniffer browserSniffer, c cVar, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.a(browserSniffer, cVar, obj);
                } else {
                    return;
                }
            }
        }

        public final void a(BrowserSniffer browserSniffer, float f, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.a(browserSniffer, f, obj);
                } else {
                    return;
                }
            }
        }

        public final void a(BrowserSniffer browserSniffer, b bVar, Object obj) {
            SniffingResourceGroup sniffingResourceGroup = BrowserSniffer.this;
            new StringBuilder("2 -- add group data --> resourceGroup.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", resourceGroup.resources.size() --> ").append(sniffingResourceGroup.resources.size());
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.a(browserSniffer, bVar, obj);
                } else {
                    return;
                }
            }
        }

        public final void a(BrowserSniffer browserSniffer, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.a(browserSniffer, obj);
                } else {
                    return;
                }
            }
        }

        public final void b(BrowserSniffer browserSniffer, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.b(browserSniffer, obj);
                } else {
                    return;
                }
            }
        }

        public final void c(BrowserSniffer browserSniffer, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.c(browserSniffer, obj);
                } else {
                    return;
                }
            }
        }

        public final void d(BrowserSniffer browserSniffer, Object obj) {
            Iterator it = new HashSet(BrowserSniffer.this.b()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (BrowserSniffer.this.c) {
                    aVar.d(browserSniffer, obj);
                } else {
                    return;
                }
            }
        }
    }

    public static class e {
        public boolean a;
        public SniffStartFrom b;
        protected long c;
        protected long d;
        protected long e;
        protected String f;
        protected String g;

        public e() {
            this.a = false;
            this.g = null;
        }

        public final String a() {
            return this.f;
        }

        public final long b() {
            return this.d == 0 ? c() : (this.d - this.e) / 1000000;
        }

        public final long c() {
            return (this.c - this.e) / 1000000;
        }

        protected final void d() {
            this.c = System.nanoTime();
            if (this.d == 0) {
                this.d = this.c;
            }
        }

        protected final void e() {
            if (this.d == 0) {
                this.d = System.nanoTime();
            }
        }

        protected final void f() {
            this.e = System.nanoTime();
            this.c = this.e;
            this.d = 0;
            if (this.g == null) {
                this.g = com.xunlei.downloadprovider.a.b.f();
            }
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            this.f = this.g + "_" + currentTimeMillis;
        }
    }

    public static interface f {
        void a(WebView webView, String str);

        boolean a(ConsoleMessage consoleMessage);

        void b(WebView webView, String str);
    }

    public BrowserSniffer() {
        this.a = new e();
        this.b = false;
        this.r = false;
        this.c = false;
        this.s = null;
        this.t = new o(this);
        this.f = new com.xunlei.downloadprovider.a.h.b(this.t);
        this.g = false;
        this.h = 0;
        this.j = new p(this);
        this.k = new q(this);
        this.l = new d();
        this.m = new JsInterfaceSniffer();
        this.p = false;
        this.q = false;
    }

    static /* synthetic */ void a(BrowserSniffer browserSniffer, int i) {
        if (browserSniffer.h != 4 && browserSniffer.h != 0) {
            new StringBuilder("stopSniffingForFinish: ").append(browserSniffer.d);
            browserSniffer.d();
            browserSniffer.a((int) XZBDevice.DOWNLOAD_LIST_ALL);
            String originalUrl = browserSniffer.i.getOriginalUrl();
            SniffingPageResource sniffingPageResource = new SniffingPageResource();
            sniffingPageResource.mPageUrl = originalUrl;
            sniffingPageResource.isGrouped = ThunderSnifferUtil.isSupportedSearchPageUrl(originalUrl);
            sniffingPageResource.mSearchKeyword = ThunderSnifferUtil.getSearchKeywordFromUrl(originalUrl);
            sniffingPageResource.setErrorCode(i);
            browserSniffer.a(sniffingPageResource);
        }
    }

    public final f a() {
        return this.k;
    }

    public final synchronized void a(a aVar) {
        if (aVar != null) {
            b().add(aVar);
        }
    }

    private synchronized void d() {
        this.b = true;
        this.p = false;
        if (this.i != null && this.i.isSniffing()) {
            this.i.cancelSniffing();
        }
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
        if (this.o != null) {
            this.o.cancel();
            this.o = null;
        }
    }

    public final HashSet<a> b() {
        if (this.s == null) {
            this.s = new HashSet();
        }
        return this.s;
    }

    public final synchronized void a(WebView webView, String str, SniffStartFrom sniffStartFrom, boolean z) {
        if (webView == null) {
            new StringBuilder("startSniffingWithWebView error: webView = ").append(null);
        } else {
            this.b = false;
            a(1);
            this.a.f();
            this.a.b = sniffStartFrom;
            if (this.n != null) {
                this.n.cancel();
                this.n = null;
            }
            TimerTask tVar = new t(this);
            new Timer().schedule(tVar, StatisticConfig.MIN_UPLOAD_INTERVAL);
            this.n = tVar;
            if (!TextUtils.isEmpty(str) && ThunderSnifferUtil.isSupportedSearchPageUrl(str) && z) {
                this.g = true;
                this.i.setOriginalUrl(str);
                this.i.setSniffingFilter(com.xunlei.downloadprovider.util.sniff.f.a(this.e));
                this.i.startPreSniffingForTitle(ThunderSnifferUtil.getSearchKeywordFromUrl(str));
                if (this.l != null) {
                    this.l.a(this, null);
                }
            }
        }
    }

    public final synchronized void a(WebView webView, String str, SniffStartFrom sniffStartFrom) {
        if (webView == null) {
            new StringBuilder("startSniffingWithWebView error: webView = ").append(null);
        } else {
            this.a.b = sniffStartFrom;
            a(webView, str);
        }
    }

    protected final synchronized void a(WebView webView, String str) {
        boolean z = false;
        synchronized (this) {
            if (webView == null) {
                new StringBuilder("startSniffingWithWebView error: webView = ").append(null);
            } else {
                this.b = false;
                this.g = false;
                a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                this.a.f();
                if (TextUtils.isEmpty(str)) {
                    str = webView.getUrl();
                }
                this.d = str;
                if (this.e != null && !com.xunlei.xllib.a.b.a(this.e)) {
                    this.f.post(new v(this));
                } else if (e()) {
                    a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                    Sniff.a(this.a.a(), this.a.b, this.a.a, ThunderSnifferUtil.judgeSniffingPageTypeForUrl(str), ThunderSnifferUtil.getSearchWordFromUrl(str));
                    this.i.setOriginalUrl(str);
                    this.i.startSniffingContent(com.umeng.a.d);
                } else {
                    String str2;
                    new StringBuilder("doStartSniffingWithWebView: ").append(str).append(" from: ").append(this.a.b).append(" way:").append(SniffConfigure.d());
                    Sniff.a(this.a.a(), this.a.b, this.a.a, ThunderSnifferUtil.judgeSniffingPageTypeForUrl(str), ThunderSnifferUtil.getSearchWordFromUrl(str));
                    SniffConfigure a = SniffConfigure.a();
                    int judgeSniffingPageTypeForUrl = ThunderSnifferUtil.judgeSniffingPageTypeForUrl(str);
                    CharSequence charSequence = com.umeng.a.d;
                    switch (judgeSniffingPageTypeForUrl) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            charSequence = a.a("site_baidu_com", com.umeng.a.d);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            charSequence = a.a("site_so_com", com.umeng.a.d);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            charSequence = a.a("site_sm_cn", com.umeng.a.d);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            charSequence = a.a("site_sogou_com", com.umeng.a.d);
                            break;
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        str2 = "window.share.startSniffing(document.documentElement.innerHTML);";
                    } else {
                        CharSequence charSequence2 = charSequence;
                    }
                    this.i.setOriginalUrl(str);
                    this.i.getSettings().setResourceNameDedupeEnabled(false);
                    this.i.setSniffingFilter(com.xunlei.downloadprovider.util.sniff.f.a(this.e));
                    if (SniffWay.SNIFF_WAY_LOCAL != SniffConfigure.d()) {
                        z = true;
                    }
                    this.i.getSettings().setSniffingServerEnabled(z);
                    TimerTask wVar = new w(this);
                    new Timer().schedule(wVar, 10000);
                    this.o = wVar;
                    if (VERSION.SDK_INT >= 19) {
                        webView.evaluateJavascript(str2, null);
                    } else {
                        webView.loadUrl(new StringBuilder(BaseJsInterface.JS_PREFIX).append(str2).toString());
                    }
                }
            }
        }
    }

    private static boolean e() {
        return !SniffConfigure.c() || SniffWay.SNIFF_WAY_FORBIDDEN == SniffConfigure.d();
    }

    public final synchronized void c() {
        if (!(this.h == 4 || this.h == 0)) {
            new StringBuilder("stopSniffing: ").append(this.d);
            d();
            a((int) XZBDevice.DOWNLOAD_LIST_ALL);
            this.l.d(this, null);
        }
    }

    private void a(SniffingPageResource sniffingPageResource) {
        String a;
        a((int) XZBDevice.DOWNLOAD_LIST_ALL);
        this.a.d();
        int judgeSniffingPageTypeForUrl = ThunderSnifferUtil.judgeSniffingPageTypeForUrl(this.i.getOriginalUrl());
        int counts = sniffingPageResource.counts();
        int i = 0;
        if (sniffingPageResource.groups != null) {
            i = sniffingPageResource.groups.size();
        }
        new StringBuilder("SniffingResult - group: ").append(i).append(" resource: ").append(counts).append(" url: ").append(this.i.getOriginalUrl());
        String searchKeyword;
        if (counts != 0) {
            String str;
            a = this.a.a();
            long c = this.a.c();
            searchKeyword = sniffingPageResource.getSearchKeyword();
            if (judgeSniffingPageTypeForUrl == 1) {
                str = "baidu";
            } else if (judgeSniffingPageTypeForUrl == 2) {
                str = "360";
            } else if (judgeSniffingPageTypeForUrl == 3) {
                str = "shenma";
            } else if (judgeSniffingPageTypeForUrl == 4) {
                str = "sougou";
            } else {
                str = "other";
            }
            String str2 = "sniff_2_get_result";
            g a2 = g.a("android_sniff", str2, str2);
            str2 = "sniff_processid";
            if (a == null) {
                a = com.umeng.a.d;
            }
            g c2 = a2.a(str2, a, 1).a("start_page", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("result_num", (long) counts).a("time", String.valueOf(((float) c) / 1000.0f)).a("sniff_word", searchKeyword == null ? com.umeng.a.d : searchKeyword).c("time", Sniff.a(c / 1000));
            String str3 = "result_num";
            long j = (long) counts;
            if (j < 0) {
                j = 0;
            }
            if (j > 1000) {
                j = 1000;
            }
            Sniff.a(c2.c(str3, j));
        } else {
            boolean z;
            if (sniffingPageResource.errorCode == 3) {
                z = true;
            } else {
                z = e();
            }
            if (e()) {
                sniffingPageResource.setErrorCode(XZBDevice.DOWNLOAD_LIST_FAILED);
            } else if (!com.xunlei.xllib.a.b.a(BrothersApplication.a()) && sniffingPageResource.getErrorCode() == 0) {
                sniffingPageResource.setErrorCode(1);
            }
            searchKeyword = this.a.a();
            sniffingPageResource.getErrorCode();
            if (judgeSniffingPageTypeForUrl == 1) {
                a = "baidu";
            } else if (judgeSniffingPageTypeForUrl == 2) {
                a = "360";
            } else if (judgeSniffingPageTypeForUrl == 3) {
                a = "shenma";
            } else if (judgeSniffingPageTypeForUrl == 4) {
                a = "sougou";
            } else {
                a = "other";
            }
            String str4 = "sniff_2_no_result";
            g a3 = g.a("android_sniff", str4, str4);
            str4 = "sniff_processid";
            if (searchKeyword == null) {
                searchKeyword = com.umeng.a.d;
            }
            Sniff.a(a3.a(str4, searchKeyword, 1).a("start_page", a, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("cloud", z ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("errorcode", MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }
        this.l.a(this, new c(this.a.a(), sniffingPageResource), null);
        if (!com.xunlei.downloadprovider.util.sniff.f.c()) {
            SniffConfigure a4 = SniffConfigure.a();
            a = new StringBuilder("http://static.m.sjzhushou.com/snf_config?rd=").append(System.currentTimeMillis()).toString();
            Request aVar = new com.xunlei.downloadprovider.util.sniff.a(a, new com.xunlei.downloadprovider.util.sniff.b(a4), new com.xunlei.downloadprovider.util.sniff.c(a4, a));
            aVar.setRetryPolicy(new com.android.volley.f(5000, 1, 1.0f));
            com.xunlei.downloadprovider.j.a.b().a(aVar);
        }
    }

    protected final void a(int i) {
        this.h = i;
        if (!(1 == i || this.n == null)) {
            this.n.cancel();
            this.n = null;
        }
        if (!(2 == i || this.o == null)) {
            this.o.cancel();
            this.o = null;
        }
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.p = false;
                this.r = false;
                this.g = false;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.r = false;
                this.g = true;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.p = false;
                this.r = true;
                this.g = false;
            default:
                break;
        }
    }
}
