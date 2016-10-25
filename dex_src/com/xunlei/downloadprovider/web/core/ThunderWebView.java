package com.xunlei.downloadprovider.web.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.WebpageProgressBar;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState;
import com.xunlei.downloadprovider.web.core.a.b;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.MalformedURLException;
import java.net.URL;
import org.android.spdy.SpdyAgent;

public class ThunderWebView extends LinearLayout {
    private static String p;
    public XLWebView a;
    public Handler b;
    public boolean c;
    boolean d;
    private final String e;
    private p f;
    private CurrentShowState g;
    private JsInterface h;
    private boolean i;
    private boolean j;
    private boolean k;
    private ErrorView l;
    private WebpageProgressBar m;
    private c n;
    private Context o;
    private String q;
    private String r;
    private boolean s;
    private String t;
    private boolean u;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[CurrentShowState.values().length];
            try {
                a[CurrentShowState.show_error.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[CurrentShowState.show_loading.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[CurrentShowState.show_webview.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum CurrentShowState {
        show_webview,
        show_error,
        show_loading;

        static {
            show_webview = new com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState("show_webview", 0);
            show_error = new com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState("show_error", 1);
            show_loading = new com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState("show_loading", 2);
            a = new com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState[]{show_webview, show_error, show_loading};
        }
    }

    static /* synthetic */ boolean b(String str) {
        Object trim = str.trim();
        if (trim.endsWith("/")) {
            trim = trim.substring(0, trim.length() - 1);
        }
        return "http://m.sjzhushou.com/v2/site/site_index_3.4.html".equals(trim);
    }

    static /* synthetic */ void h() {
    }

    static /* synthetic */ void i() {
    }

    static {
        p = null;
    }

    public ThunderWebView(Context context) {
        super(context);
        this.e = getClass().getSimpleName();
        this.f = null;
        this.g = CurrentShowState.show_loading;
        this.h = null;
        this.i = false;
        this.j = true;
        this.k = true;
        this.a = null;
        this.l = null;
        this.m = null;
        this.o = null;
        this.q = null;
        this.r = null;
        this.s = true;
        this.d = false;
        this.o = context;
        j();
        a();
        b.a().b();
    }

    public ThunderWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = getClass().getSimpleName();
        this.f = null;
        this.g = CurrentShowState.show_loading;
        this.h = null;
        this.i = false;
        this.j = true;
        this.k = true;
        this.a = null;
        this.l = null;
        this.m = null;
        this.o = null;
        this.q = null;
        this.r = null;
        this.s = true;
        this.d = false;
        this.o = context;
        j();
        a();
        b.a().b();
    }

    public void setIsReportPage(boolean z) {
        this.d = z;
    }

    public final void a() {
        int x = com.xunlei.downloadprovider.a.b.x();
        if (BrothersApplication.a().getSharedPreferences("dp_webview", 0).getInt("clearcache", 0) != x) {
            boolean z = true;
        } else {
            int i = 0;
        }
        if (z) {
            this.a.clearCache(true);
            Editor edit = BrothersApplication.a().getSharedPreferences("dp_webview", 0).edit();
            edit.putInt("clearcache", x);
            edit.commit();
        }
    }

    private void setTextHintColor(int i) {
    }

    private void setDetailPageWebCoverGone(boolean z) {
    }

    private void j() {
        this.j = true;
        this.n = new c(this, this.o);
        this.b = new h.b(this.n);
        this.h = new JsKNAccelInterface(this.b, com.xunlei.downloadprovider.a.b.d(), this.o.getString(R.string.version), com.xunlei.downloadprovider.a.b.f(), com.xunlei.downloadprovider.a.b.h(), com.xunlei.downloadprovider.a.b.g());
        View inflate = LayoutInflater.from(getContext()).inflate(2130969003, this);
        this.a = (XLWebView) inflate.findViewById(2131757066);
        this.l = (ErrorView) inflate.findViewById(2131756034);
        this.l.setActionButtonListener(new o(this));
        this.m = (WebpageProgressBar) inflate.findViewById(2131757223);
        if (com.xunlei.downloadprovider.a.b.i() > 10) {
            this.a.setLayerType(0, null);
        }
        this.a.setWebViewClient(new i(this));
        this.a.setWebChromeClient(new j(this));
        this.a.getSettings().setUserAgentString(new StringBuilder("Android.Thunder.").append(this.a.getSettings().getUserAgentString()).toString());
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        if (com.xunlei.downloadprovider.a.b.i() >= 8) {
            settings.setPluginState(PluginState.ON);
        }
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.o.getApplicationContext().getDir("database", 0).getPath());
        settings.setCacheMode(-1);
        settings.setLoadWithOverviewMode(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setAppCacheEnabled(true);
        settings.setDefaultTextEncodingName("GBK");
        this.a.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
        this.a.setDownloadListener(new k(this));
        this.a.setOnTouchListener(new l(this));
        this.a.setOnLongClickListener(new m(this));
        this.a.setHorizontalScrollBarEnabled(false);
        BrowserUtil.a(this.a);
    }

    public void setThunderWebViewClient(p pVar) {
        this.f = pVar;
        this.n.b = pVar;
    }

    public void setJsCallbackMessageListener(a aVar) {
        this.n.c = aVar;
    }

    private void setSupportZoom(boolean z) {
        if (this.a instanceof WebView) {
            WebSettings settings = this.a.getSettings();
            settings.setSupportZoom(z);
            settings.setBuiltInZoomControls(z);
        }
    }

    private void setBlockNetworkImage(boolean z) {
        if (this.a != null && (this.a instanceof WebView)) {
            WebSettings settings = this.a.getSettings();
            if (settings != null && (settings instanceof WebSettings)) {
                settings.setBlockNetworkImage(z);
            }
        }
    }

    public final void b() {
        if (com.xunlei.xllib.a.b.a(BrothersApplication.a().getApplicationContext())) {
            if (this.n != null) {
                if (this.a.getUrl() != null) {
                    this.n.a(this.a.getUrl());
                } else {
                    this.n.a(p);
                }
            }
            setCurShowView(CurrentShowState.show_loading);
            this.a.clearView();
            if (this.a.getUrl() != null) {
                this.a.reload();
                return;
            } else {
                a(p);
                return;
            }
        }
        setCurShowView(CurrentShowState.show_error);
    }

    @SuppressLint({"NewApi"})
    public final void c() {
        if (this.a != null && (this.a instanceof WebView)) {
            this.m.b();
            ViewParent parent = this.a.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.a);
            }
            this.a.removeJavascriptInterface(ThunderSniffer.JSINTERFACE_NAMESPACE);
            this.a.setWebViewClient(null);
            this.a.setWebChromeClient(null);
            this.a.clearView();
            this.a.clearHistory();
            this.a.clearCache(false);
            this.a.destroy();
            this.a = null;
        }
    }

    public final boolean a(String str) {
        if (TextUtils.isEmpty(str) || this.a == null) {
            return false;
        }
        this.q = d(str);
        if (!str.contains("about:blank")) {
            if (!(str.startsWith(BaseJsInterface.JS_PREFIX) || c(str))) {
                BrowserUtil.a();
                str = BrowserUtil.a(str);
            }
            e(str);
            if (!(this.n == null || str.startsWith(BaseJsInterface.JS_PREFIX))) {
                this.n.a(str);
            }
            if (VERSION.SDK_INT < 19 || str == null || !str.startsWith(BaseJsInterface.JS_PREFIX)) {
                this.a.loadUrl(str);
            } else {
                this.a.evaluateJavascript(str.substring(XZBDevice.Success), null);
            }
            p = str;
            return true;
        } else if (p != null && p.equals(str)) {
            return false;
        } else {
            this.a.loadUrl(str);
            p = str;
            return true;
        }
    }

    private static boolean c(String str) {
        return (str == null || str.equals(com.umeng.a.d) || !str.startsWith("file:///android_asset")) ? false : true;
    }

    public void setBackgroundColor(int i) {
        if (this.a instanceof WebView) {
            this.a.setBackgroundColor(i);
        }
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        if (this.a instanceof WebView) {
            this.a.setLayoutParams(layoutParams);
        }
    }

    public final boolean d() {
        return this.a instanceof WebView ? this.a.canGoBack() : false;
    }

    public final void e() {
        if (this.a instanceof WebView) {
            this.a.goBack();
        }
    }

    public String getUrl() {
        return this.a instanceof WebView ? this.a.getUrl() : null;
    }

    public void setCurShowView(CurrentShowState currentShowState) {
        new StringBuilder("func setCurShowView : showState = ").append(currentShowState).append(" , mWebView = ").append(this.a);
        if (currentShowState != null && this.a != null) {
            switch (AnonymousClass_1.a[currentShowState.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.g = CurrentShowState.show_error;
                    if (com.xunlei.xllib.a.b.a(BrothersApplication.a().getApplicationContext())) {
                        this.l.setErrorType(1);
                    } else {
                        this.l.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    }
                    this.a.setVisibility(XZBDevice.Wait);
                    this.l.setVisibility(0);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.g = CurrentShowState.show_loading;
                    this.a.getUrl();
                    this.l.setVisibility(XZBDevice.Wait);
                    this.a.setVisibility(0);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.g = CurrentShowState.show_webview;
                    this.l.setVisibility(XZBDevice.Wait);
                    this.a.setVisibility(0);
                default:
                    break;
            }
        }
    }

    public CurrentShowState getCurrentShowState() {
        return this.g;
    }

    public static String getCurrentUrl() {
        return p;
    }

    public void setCurrentUrl(String str) {
        p = str;
    }

    public String getOrigalHost() {
        return this.q;
    }

    public String getRefUrl() {
        return this.r;
    }

    public void setRefUrl(String str) {
        this.r = str;
    }

    public String getTitle() {
        if (!(this.a instanceof WebView)) {
            return null;
        }
        String title = this.a.getTitle();
        if (title == null) {
            title = this.t;
        }
        return title == null ? this.a.getUrl() : title;
    }

    private static String d(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLayerState(boolean z) {
        this.c = z;
    }

    public final void f() {
        if (this.c && (this.a instanceof WebView)) {
            this.a.loadUrl("javascript:physicalReturn()");
        }
    }

    private void e(String str) {
        setSupportZoom(true);
        if (!(BrowserUtil.b(str) || c(str))) {
            boolean z;
            if (str == null || str.equals(com.umeng.a.d) || !(str.startsWith("http://www.baidu.com") || str.startsWith("http://m.baidu.com"))) {
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                if (this.j) {
                    this.a.removeJavascriptInterface(ThunderSniffer.JSINTERFACE_NAMESPACE);
                    this.i = false;
                }
                this.a.addJavascriptInterface(this.h, ThunderSniffer.JSINTERFACE_NAMESPACE);
            }
        }
        if ((!this.i && this.h != null) || (this.h != null && this.u)) {
            this.i = true;
            this.a.addJavascriptInterface(this.h, ThunderSniffer.JSINTERFACE_NAMESPACE);
        }
    }

    public void setIsSniffExcuting(boolean z) {
        this.u = z;
    }

    public void setNeedRmoveJSInterface(boolean z) {
        this.j = z;
    }

    public void setCanLoadingShow(boolean z) {
        if (!this.s && this.g != CurrentShowState.show_error) {
            setCurShowView(CurrentShowState.show_webview);
        }
    }

    public void setEntry(int i) {
        this.n.d = i;
    }

    public void setRefreshButtonListener(OnClickListener onClickListener) {
        if (this.l != null) {
            this.l.setActionButtonListener(onClickListener);
        }
    }

    static /* synthetic */ void a(ThunderWebView thunderWebView, DownData downData) {
        if (thunderWebView.f == null) {
            return;
        }
        if (DownloadService.a() == null) {
            DownloadService.a(new n(thunderWebView, downData));
        } else {
            thunderWebView.f.a(downData);
        }
    }
}
