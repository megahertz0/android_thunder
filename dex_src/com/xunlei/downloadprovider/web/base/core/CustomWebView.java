package com.xunlei.downloadprovider.web.base.core;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.umeng.a;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class CustomWebView extends FrameLayout {
    ObservableWebView a;
    private ErrorView b;
    private String c;
    private a d;
    private DefaultJsInterface e;
    private String f;
    private b g;
    private b h;
    private Handler i;
    private UnifiedLoadingView j;
    private b k;
    private f l;

    public static interface b {
        void a(boolean z);
    }

    public CustomWebView(Context context) {
        super(context);
        this.c = a.d;
        this.i = new c(this);
        this.k = new b();
        this.l = new e(this);
        a(context);
    }

    public CustomWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = a.d;
        this.i = new c(this);
        this.k = new b();
        this.l = new e(this);
        a(context);
    }

    public CustomWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = a.d;
        this.i = new c(this);
        this.k = new b();
        this.l = new e(this);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130968710, this, true);
        this.j = (UnifiedLoadingView) inflate.findViewById(2131755563);
        this.b = (ErrorView) inflate.findViewById(2131755695);
        this.b.setActionButtonListener(new d(this));
        this.j.setType(1);
        this.a = (ObservableWebView) inflate.findViewById(2131755694);
        WebView webView = this.a;
        if (VERSION.SDK_INT >= 19 && (getContext().getApplicationInfo().flags & 2) != 0) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (!isInEditMode()) {
            if (VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
            }
            CookieManager.getInstance().setAcceptCookie(true);
            WebSettings settings = webView.getSettings();
            StringBuilder stringBuilder = new StringBuilder(settings.getUserAgentString());
            stringBuilder.append(" iThunder");
            settings.setUserAgentString(stringBuilder.toString());
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(getContext().getApplicationContext().getDir("database", 0).getPath());
            settings.setLoadWithOverviewMode(true);
            settings.setRenderPriority(RenderPriority.HIGH);
            settings.setBlockNetworkImage(false);
            settings.setAppCacheEnabled(true);
            settings.setAppCachePath(getContext().getApplicationContext().getCacheDir().getPath());
            settings.setDefaultTextEncodingName("GBK");
            settings.setSupportZoom(false);
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
            settings.setTextSize(TextSize.NORMAL);
            if (VERSION.SDK_INT >= 17) {
                settings.setMediaPlaybackRequiresUserGesture(false);
            }
            webView.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebChromeClient(this.k);
            webView.setWebViewClient(this.l);
            this.e = new DefaultJsInterface(getContext(), this);
            webView.addJavascriptInterface(this.e, BaseJsInterface.NAME);
        }
    }

    public void setProgressType(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.j.setType(1);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.j.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                this.j.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.j.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            default:
                break;
        }
    }

    public final void a() {
        if (this.i.hasMessages(1)) {
            this.i.removeMessages(1);
        }
        if (this.j != null && this.j.getVisibility() != 0) {
            this.j.a();
            if (this.h != null) {
                this.h.a(true);
            }
        }
    }

    public final void b() {
        if (this.i.hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.i.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        if (!com.xunlei.xllib.a.b.a(getContext())) {
            this.b.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        if (this.b.getVisibility() != 0) {
            this.b.setVisibility(0);
            if (this.g != null) {
                this.g.a(true);
            }
        }
    }

    public final void c() {
        if (this.i.hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.i.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        this.i.sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, 500);
    }

    public final void d() {
        if (this.i.hasMessages(1)) {
            this.i.removeMessages(1);
        }
        this.i.sendEmptyMessageDelayed(1, 500);
    }

    public final void e() {
        if (this.e != null) {
            this.e.clearInterceptors();
        }
        if (this.a != null) {
            this.a.stopLoading();
            this.j.b();
            ViewParent parent = this.a.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.a);
            }
            this.a.destroy();
            this.a = null;
        }
    }

    public String getUrl() {
        return this.a != null ? this.a.getUrl() : a.d;
    }

    public WebSettings getSettings() {
        return this.a != null ? this.a.getSettings() : null;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str) && this.a != null) {
            if (!str.startsWith(BaseJsInterface.JS_PREFIX)) {
                c();
                this.a.loadUrl(str);
                a();
                this.f = str;
            } else if (VERSION.SDK_INT >= 19) {
                Object substring = str.substring(XZBDevice.Success);
                if (!TextUtils.isEmpty(substring)) {
                    this.a.evaluateJavascript(substring, null);
                }
            } else {
                this.a.loadUrl(str);
            }
        }
    }

    public void setErrorViewVisibilityListener(b bVar) {
        this.g = bVar;
    }

    public void setProgressVisibilityListener(b bVar) {
        this.h = bVar;
    }

    public String getFrom() {
        return this.c;
    }

    public void setFrom(String str) {
        this.c = str;
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.k.a = webChromeClient;
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.l.b = webViewClient;
    }

    public void setDownloadListener(DownloadListener downloadListener) {
        if (this.a != null) {
            this.a.setDownloadListener(downloadListener);
        }
    }

    public void setOnScrollChangedListener(ObservableWebView.a aVar) {
        this.a.setOnScrollChangedListener(aVar);
    }

    public final boolean f() {
        return this.a != null ? this.a.canGoBack() : false;
    }

    public final void g() {
        if (this.a != null) {
            this.a.goBack();
        }
    }

    public void setRefreshListener(a aVar) {
        this.d = aVar;
    }

    public final void h() {
        if (this.a != null) {
            this.a.loadUrl("javascript:document.body.innerHTML=\"\";");
        }
    }

    static /* synthetic */ void a(CustomWebView customWebView) {
        if (customWebView.j != null && customWebView.j.getVisibility() == 0) {
            customWebView.j.b();
            if (customWebView.h != null) {
                customWebView.h.a(false);
            }
        }
    }

    static /* synthetic */ void b(CustomWebView customWebView) {
        if (customWebView.b.getVisibility() == 0) {
            customWebView.b.setVisibility(XZBDevice.Wait);
            if (customWebView.g != null) {
                customWebView.g.a(false);
            }
        }
    }
}
