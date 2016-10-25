package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.http.SslError;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout;
import anet.channel.util.HttpConstant;
import com.inmobi.ads.b.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.rendering.InMobiAdActivity.b;
import com.inmobi.rendering.RenderView.RenderViewState;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.MraidMediaProcessor.MediaContentType;
import com.inmobi.rendering.mraid.c;
import com.inmobi.rendering.mraid.d;
import com.inmobi.rendering.mraid.h;
import com.inmobi.rendering.mraid.i;
import com.inmobi.rendering.mraid.j;
import com.inmobi.rendering.mraid.k;
import com.inmobi.rendering.mraid.l;
import com.inmobi.rendering.mraid.m;
import com.inmobi.rendering.mraid.n;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "ViewConstructor", "ClickableViewAccessibility"})
public final class RenderView extends WebView {
    private static boolean Q;
    static final a a;
    private static final String b;
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private String F;
    private boolean G;
    private AtomicBoolean H;
    private final Object I;
    private final Object J;
    private boolean K;
    private boolean L;
    private View M;
    private CustomViewCallback N;
    private int O;
    private boolean P;
    private final b R;
    private final WebViewClient S;
    private final WebChromeClient T;
    private RenderView c;
    private List<c> d;
    private Activity e;
    private boolean f;
    private ViewGroup g;
    private a h;
    private RenderViewState i;
    private RenderingProperties j;
    private h k;
    private k l;
    private MraidMediaProcessor m;
    private n n;
    private a o;
    private e p;
    private com.inmobi.ads.b.c q;
    private List<String> r;
    private boolean s;
    private d t;
    private m u;
    private l v;
    private JSONObject w;
    private JSONObject x;
    private boolean y;
    private boolean z;

    public static interface a {
        void a(RenderView renderView);

        void a(RenderView renderView, HashMap<Object, Object> hashMap);

        void b(RenderView renderView);

        void b(RenderView renderView, HashMap<Object, Object> hashMap);

        void c(RenderView renderView);

        void d(RenderView renderView);

        void e(RenderView renderView);

        void f(RenderView renderView);

        void g(RenderView renderView);
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass_2(String str) {
            this.a = str;
        }

        public void run() {
            if (!RenderView.this.H.get()) {
                RenderView.this.loadDataWithBaseURL(com.umeng.a.d, this.a, "text/html", GameManager.DEFAULT_CHARSET, null);
            }
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass_3(String str) {
            this.a = str;
        }

        public void run() {
            if (!RenderView.this.H.get()) {
                String toString = new StringBuilder("javascript:try{").append(this.a).append("}catch(e){}").toString();
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Injecting javascript:").append(toString).toString());
                if (VERSION.SDK_INT < 19) {
                    RenderView.this.h(toString);
                } else {
                    RenderView.this.i(toString);
                }
            }
        }
    }

    public enum RenderViewState {
        LOADING,
        DEFAULT,
        RESIZED,
        EXPANDED,
        EXPANDING,
        HIDDEN,
        RESIZING
    }

    static {
        a = new a() {
            public final void a(RenderView renderView) {
            }

            public final void b(RenderView renderView) {
            }

            public final void c(RenderView renderView) {
            }

            public final void d(RenderView renderView) {
            }

            public final void e(RenderView renderView) {
            }

            public final void f(RenderView renderView) {
            }

            public final void a(RenderView renderView, HashMap<Object, Object> hashMap) {
            }

            public final void b(RenderView renderView, HashMap<Object, Object> hashMap) {
            }

            public final void g(RenderView renderView) {
            }
        };
        b = RenderView.class.getSimpleName();
        Q = false;
    }

    public RenderView(Context context, RenderingProperties renderingProperties) {
        super(context);
        this.d = new ArrayList();
        this.f = false;
        this.i = RenderViewState.DEFAULT;
        this.r = new ArrayList();
        this.y = true;
        this.z = true;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = null;
        this.G = false;
        this.H = new AtomicBoolean(false);
        this.I = new Object();
        this.J = new Object();
        this.L = true;
        this.O = -1;
        this.P = false;
        this.R = new b() {
            public void a() {
                Logger.a(InternalLogLevel.INTERNAL, b, "onAdScreenDisplayed");
                if (PlacementType.INLINE == RenderView.this.j.a()) {
                    if (RenderView.this.c != null) {
                        RenderView.this.c.setAndUpdateViewState(RenderViewState.EXPANDED);
                    } else {
                        RenderView.this.setAndUpdateViewState(RenderViewState.EXPANDED);
                    }
                    RenderView.this.K = false;
                }
                if (RenderView.this.h != null) {
                    RenderView.this.h.e(RenderView.this);
                }
            }

            public void b() {
                Logger.a(InternalLogLevel.INTERNAL, b, "onAdScreenDismissed");
                if (PlacementType.INLINE == RenderView.this.j.a()) {
                    RenderView.this.setAndUpdateViewState(RenderViewState.DEFAULT);
                    if (RenderView.this.c != null) {
                        RenderView.this.c.setAndUpdateViewState(RenderViewState.DEFAULT);
                    }
                } else if (RenderViewState.DEFAULT == RenderView.this.i) {
                    RenderView.this.setAndUpdateViewState(RenderViewState.HIDDEN);
                }
                if (RenderView.this.h != null) {
                    RenderView.this.h.f(RenderView.this);
                }
            }
        };
        this.S = new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Placement type: ").append(RenderView.this.j.a()).append(" url:").append(str).toString());
                if (!(RenderView.this.f || "about:blank".equals(str))) {
                    RenderView.this.s();
                }
                if (PlacementType.FULL_SCREEN != RenderView.this.j.a()) {
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Override URL loading (returned true): ").append(str).toString());
                    try {
                        com.inmobi.commons.a.a.a(RenderView.this.getRenderViewContext(), Intent.parseUri(str, 0));
                        RenderView.this.getListener().g(RenderView.this);
                        return true;
                    } catch (URISyntaxException e) {
                        Logger.a(InternalLogLevel.INTERNAL, b, e.getMessage());
                        return true;
                    } catch (ActivityNotFoundException e2) {
                        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("No app can handle the URI (").append(str).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                        return true;
                    }
                } else if (!RenderView.this.f || !str.startsWith(HttpConstant.HTTP) || str.contains("play.google.com") || str.contains("market.android.com") || str.contains("market%3A%2F%2F")) {
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Override URL loading (returned true): ").append(str).toString());
                    try {
                        com.inmobi.commons.a.a.a(RenderView.this.getRenderViewContext(), Intent.parseUri(str, 0));
                        RenderView.this.getListener().g(RenderView.this);
                        return true;
                    } catch (URISyntaxException e3) {
                        Logger.a(InternalLogLevel.INTERNAL, b, e3.getMessage());
                        return true;
                    } catch (ActivityNotFoundException e4) {
                        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("No app can handle the URI (").append(str).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                        return true;
                    }
                } else {
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Override URL loading (returned false): ").append(str).toString());
                    if (RenderView.this.m == null) {
                        return false;
                    }
                    RenderView.this.m.c();
                    return false;
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Page load started:").append(str).toString());
                RenderView.this.G = false;
                RenderView.this.setAndUpdateViewState(RenderViewState.LOADING);
            }

            public void onPageFinished(WebView webView, String str) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Page load finished:").append(str).toString());
                if (RenderView.this.r.contains(str) && !RenderView.this.G) {
                    RenderView.this.G = true;
                    Logger.a(InternalLogLevel.INTERNAL, b, "Injecting MRAID javascript for two piece creatives.");
                    RenderView.this.b(RenderView.this.getMraidJsString());
                }
                if (RenderViewState.LOADING == RenderView.this.i) {
                    RenderView.this.h.c(RenderView.this);
                    RenderView.this.t();
                    if (RenderView.this.c != null) {
                        RenderView.this.setAndUpdateViewState(RenderViewState.EXPANDED);
                    } else {
                        RenderView.this.setAndUpdateViewState(RenderViewState.DEFAULT);
                    }
                }
            }

            public void onLoadResource(WebView webView, String str) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Resource loading:").append(str).toString());
                if (str != null) {
                    String url = RenderView.this.getUrl();
                    if (str.contains("/mraid.js") && !url.equals("about:blank") && !url.startsWith("file:")) {
                        if (!RenderView.this.r.contains(url)) {
                            RenderView.this.r.add(url);
                        }
                        if (!RenderView.this.G) {
                            RenderView.this.G = true;
                            Logger.a(InternalLogLevel.INTERNAL, b, "Injecting MRAID javascript for two piece creatives.");
                            RenderView.this.b(RenderView.this.getMraidJsString());
                        }
                    }
                }
            }

            @TargetApi(22)
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Loading error. Error code:").append(i).append(" Error msg:").append(str).append(" Failing url:").append(str2).toString());
            }

            @TargetApi(23)
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Loading error. Error code:").append(webResourceError.getErrorCode()).append(" Error msg:").append(webResourceError.getDescription()).append(" Failing url:").append(webResourceRequest.getUrl()).toString());
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("SSL error received. Error code:").append(sslError.getPrimaryError()).toString());
                sslErrorHandler.cancel();
            }
        };
        this.T = new WebChromeClient() {

            class AnonymousClass_1 implements OnClickListener {
                final /* synthetic */ JsResult a;

                AnonymousClass_1(JsResult jsResult) {
                    this.a = jsResult;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.confirm();
                }
            }

            class AnonymousClass_2 implements OnClickListener {
                final /* synthetic */ JsResult a;

                AnonymousClass_2(JsResult jsResult) {
                    this.a = jsResult;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.cancel();
                }
            }

            class AnonymousClass_3 implements OnClickListener {
                final /* synthetic */ JsResult a;

                AnonymousClass_3(JsResult jsResult) {
                    this.a = jsResult;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.confirm();
                }
            }

            class AnonymousClass_6 implements OnClickListener {
                final /* synthetic */ Callback a;
                final /* synthetic */ String b;

                AnonymousClass_6(Callback callback, String str) {
                    this.a = callback;
                    this.b = str;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.invoke(this.b, false, false);
                }
            }

            class AnonymousClass_7 implements OnClickListener {
                final /* synthetic */ Callback a;
                final /* synthetic */ String b;

                AnonymousClass_7(Callback callback, String str) {
                    this.a = callback;
                    this.b = str;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.invoke(this.b, true, false);
                }
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("jsAlert called with: ").append(str2).append(str).toString());
                if (RenderView.this.e != null) {
                    new Builder(RenderView.this.e).setMessage(str2).setTitle(str).setPositiveButton(17039370, new AnonymousClass_1(jsResult)).setCancelable(false).create().show();
                } else {
                    jsResult.confirm();
                }
                return true;
            }

            public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                if (RenderView.this.e != null) {
                    new Builder(RenderView.this.e).setMessage(str2).setPositiveButton(17039370, new AnonymousClass_3(jsResult)).setNegativeButton(17039360, new AnonymousClass_2(jsResult)).create().show();
                } else {
                    jsResult.confirm();
                }
                return true;
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                if (RenderView.this.e != null) {
                    RenderView.this.M = view;
                    RenderView.this.N = customViewCallback;
                    RenderView.this.M.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                    FrameLayout frameLayout = (FrameLayout) RenderView.this.e.findViewById(16908290);
                    RenderView.this.M.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    frameLayout.addView(RenderView.this.M, new LayoutParams(-1, -1, 0, 0));
                    RenderView.this.M.requestFocus();
                    a(RenderView.this.M, new OnKeyListener() {
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (4 != keyEvent.getKeyCode() || keyEvent.getAction() != 0) {
                                return false;
                            }
                            Logger.a(InternalLogLevel.INTERNAL, b, "Back pressed when HTML5 video is playing.");
                            AnonymousClass_7.this.a();
                            return true;
                        }
                    });
                }
            }

            private void a(View view, OnKeyListener onKeyListener) {
                view.setOnKeyListener(onKeyListener);
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
            }

            public void onHideCustomView() {
                a();
                super.onHideCustomView();
            }

            private void a() {
                if (RenderView.this.M != null) {
                    if (RenderView.this.N != null) {
                        RenderView.this.N.onCustomViewHidden();
                        RenderView.this.N = null;
                    }
                    if (RenderView.this.M != null && RenderView.this.M.getParent() != null) {
                        ((ViewGroup) RenderView.this.M.getParent()).removeView(RenderView.this.M);
                        RenderView.this.M = null;
                    }
                }
            }

            public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
                if (RenderView.this.e != null) {
                    new Builder(RenderView.this.e).setTitle("Location Permission").setMessage("Allow location access").setPositiveButton(17039370, new AnonymousClass_7(callback, str)).setNegativeButton(17039360, new AnonymousClass_6(callback, str)).create().show();
                }
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Console message:").append(consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId()).toString());
                return true;
            }
        };
        this.c = null;
        this.j = renderingProperties;
        this.K = false;
        if (!Q) {
            a(getContext());
            Q = true;
        }
    }

    public final void setOriginalRenderView(RenderView renderView) {
        this.c = renderView;
    }

    public final RenderView getOriginalRenderView() {
        return this.c;
    }

    public final b getAdScreenEventsListener() {
        return this.R;
    }

    public final RenderViewState getState() {
        return this.i;
    }

    public final boolean a() {
        return this.C;
    }

    public final Object getDefaultPositionMonitor() {
        return this.I;
    }

    public final Object getCurrentPositionMonitor() {
        return this.J;
    }

    public final boolean b() {
        return this.y;
    }

    public final boolean c() {
        return this.z;
    }

    public final void setDefaultPositionLock() {
        this.y = true;
    }

    public final void setCurrentPositionLock() {
        this.z = true;
    }

    public final Context getRenderViewContext() {
        return this.e != null ? this.e : getContext();
    }

    public final void setDefaultPosition() {
        int[] iArr = new int[2];
        this.w = new JSONObject();
        if (this.g == null) {
            this.g = (ViewGroup) getParent();
        }
        if (this.g != null) {
            this.g.getLocationOnScreen(iArr);
            try {
                this.w.put("x", DisplayInfo.b(iArr[0]));
                this.w.put("y", DisplayInfo.b(iArr[1]));
                int b = DisplayInfo.b(this.g.getWidth());
                int b2 = DisplayInfo.b(this.g.getHeight());
                this.w.put("width", b);
                this.w.put("height", b2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.w.put("x", 0);
                this.w.put("y", 0);
                this.w.put("width", 0);
                this.w.put("height", 0);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        synchronized (this.I) {
            this.y = false;
            this.I.notifyAll();
        }
    }

    public final String getDefaultPosition() {
        return this.w == null ? com.umeng.a.d : this.w.toString();
    }

    public final void setCurrentPosition() {
        this.x = new JSONObject();
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        try {
            this.x.put("x", DisplayInfo.b(iArr[0]));
            this.x.put("y", DisplayInfo.b(iArr[1]));
            int b = DisplayInfo.b(getWidth());
            int b2 = DisplayInfo.b(getHeight());
            this.x.put("width", b);
            this.x.put("height", b2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        synchronized (this.J) {
            this.z = false;
            this.J.notifyAll();
        }
    }

    public final String getCurrentPosition() {
        return this.x == null ? com.umeng.a.d : this.x.toString();
    }

    public final void setFullScreenActivity(Activity activity) {
        this.e = activity;
        if (this.v != null) {
            setOrientationProperties(this.v);
        }
    }

    public final Activity getFullScreenActivity() {
        return this.e;
    }

    public final e getRenderingConfig() {
        return this.p;
    }

    public final com.inmobi.ads.b.c getMraidConfig() {
        return this.q;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("onSizeChanged (").append(i).append(", ").append(i2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (i != 0 && i2 != 0) {
            a(DisplayInfo.b(i), DisplayInfo.b(i2));
        }
    }

    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        boolean z = i == 0;
        if (this.C != z) {
            e(z);
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.P = !z;
        d(z);
    }

    public final void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        if (i == 0) {
            d(false);
        } else if (!this.P) {
            d(true);
        }
    }

    private void d(boolean z) {
        if (this.C != z) {
            e(z);
        }
    }

    private void e(boolean z) {
        if (!this.K) {
            this.C = z;
            if (z) {
                this.h.d(this);
            } else {
                this.n.a(getRenderViewContext());
            }
            a(this.C);
        }
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        super.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        q();
        if (this.g == null) {
            this.g = (ViewGroup) getParent();
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Touch event received, action:").append(motionEvent.getAction()).toString());
        d();
        return super.onInterceptTouchEvent(motionEvent);
    }

    @TargetApi(11)
    private void q() {
        if (VERSION.SDK_INT >= 11) {
            this.s = isHardwareAccelerated();
        }
    }

    public final void onDetachedFromWindow() {
        this.r.clear();
        this.o.unRegisterBroadcastListener();
        getMediaProcessor().e();
        getMediaProcessor().f();
        getMediaProcessor().h();
        this.n.a(getRenderViewContext());
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Detaching WebView from window encountered an error (").append(e.getMessage()).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "IllegalArgumentException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e.getMessage());
            com.inmobi.commons.core.c.a.a().a("ads", "ExceptionCaught", hashMap);
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    @TargetApi(19)
    public final void a(a aVar, e eVar, com.inmobi.ads.b.c cVar) {
        this.p = eVar;
        this.q = cVar;
        this.h = aVar;
        this.g = (ViewGroup) getParent();
        if ("production".equals("staging") && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (getRenderingConfig() != null) {
            setBackgroundColor(getRenderingConfig().d());
        }
        if (getMraidConfig() != null && r()) {
            new j(getMraidConfig().d(), getMraidConfig().b(), getMraidConfig().c()).a();
        }
        if (VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setGeolocationEnabled(true);
        setWebViewClient(this.S);
        setWebChromeClient(this.T);
        this.o = new a(this, this.j);
        addJavascriptInterface(this.o, "sdkController");
        this.k = new h(this);
        this.l = new k(this);
        this.m = new MraidMediaProcessor(this);
        this.n = new n(this);
        this.t = new d();
        this.u = new m();
        this.v = new l();
    }

    final void setIsInAppBrowser(boolean z) {
        this.f = z;
    }

    @TargetApi(11)
    public final void destroy() {
        this.H.set(true);
        this.K = true;
        this.O = -1;
        removeJavascriptInterface("sdkController");
        z();
        super.destroy();
    }

    @TargetApi(15)
    private void a(Context context) {
        if (VERSION.SDK_INT == 19) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            View webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL(null, com.umeng.a.d, "text/html", GameManager.DEFAULT_CHARSET, null);
            ViewGroup.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = 2005;
            if (VERSION.SDK_INT < 11) {
                layoutParams.flags = 24;
            } else {
                layoutParams.flags = 16777240;
            }
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            windowManager.addView(webView, layoutParams);
        }
    }

    private boolean r() {
        return (System.currentTimeMillis() / 1000) - new i().c() > getMraidConfig().a();
    }

    public final void a(String str) {
        this.K = false;
        new Handler(getRenderViewContext().getMainLooper()).post(new AnonymousClass_2(str));
    }

    public final void stopLoading() {
        if (!this.H.get()) {
            super.stopLoading();
        }
    }

    public final void d() {
        b("window.mraidview.onUserInteraction();");
    }

    private void s() {
        b("window.mraidview.detectAndBlockFraud('redirect')");
    }

    private void t() {
        b("window.imaiview.broadcastEvent('ready');");
        b("window.mraidview.broadcastEvent('ready');");
    }

    private void a(int i, int i2) {
        b(new StringBuilder("window.mraidview.broadcastEvent('sizeChange',").append(i).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(i2).append(");").toString());
    }

    public final void a(boolean z) {
        b(new StringBuilder("window.mraidview.broadcastEvent('viewableChange',").append(z).append(");").toString());
    }

    private void g(String str) {
        b(new StringBuilder("window.mraidview.broadcastEvent('stateChange','").append(str).append("');").toString());
    }

    public final void a(String str, String str2, String str3) {
        a(str, new StringBuilder("broadcastEvent('error',\"").append(str2).append("\", \"").append(str3).append("\")").toString());
    }

    public final void a(String str, String str2) {
        b(str + "." + str2);
    }

    public final void b(String str) {
        new Handler(getRenderViewContext().getMainLooper()).post(new AnonymousClass_3(str));
    }

    public final void a(String str, String str2, MediaContentType mediaContentType) {
        if (PlacementType.FULL_SCREEN != this.j.a() && RenderViewState.EXPANDED != getViewState()) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback is only supported on full screen ads! Ignoring request ...");
        } else if (this.e == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback is  not allowed before it is visible! Ignoring request ...");
            a(str, "Media playback is  not allowed before it is visible! Ignoring request ...", "playVideo");
        } else {
            this.m.a(str, str2, mediaContentType, this.e);
        }
    }

    public final void b(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.a(str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void a(String str, String str2, int i) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.a(str, str2, i);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void c(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.b(str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void d(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.c(str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void b(String str, String str2, int i) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.b(str, str2, i);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final int e(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            return this.m.e(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        return 0;
    }

    public final boolean f(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            return this.m.d(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        return false;
    }

    public final void g(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.a(str, str2, false);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void h(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.f(str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void i(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.j.a() || RenderViewState.EXPANDED == getViewState()) {
            this.m.g(str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public final void j(String str, String str2) {
        if (RenderViewState.DEFAULT == this.i || RenderViewState.RESIZED == this.i) {
            this.K = true;
            this.k.a(str, str2);
            requestLayout();
            invalidate();
            this.B = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:").append(this.i).toString());
    }

    public final boolean e() {
        return this.A;
    }

    public final void setUseCustomClose(boolean z) {
        this.A = z;
    }

    public final boolean f() {
        return this.D;
    }

    public final void setCloseRegionDisabled(boolean z) {
        this.D = z;
    }

    public final void setDisableBackButton(boolean z) {
        this.E = z;
    }

    public final boolean g() {
        return this.E;
    }

    public final void c(String str) {
        this.F = str;
    }

    public final void h() {
        this.F = null;
    }

    private boolean u() {
        return this.F != null;
    }

    public final void i() {
        if (u()) {
            a(this.F, "broadcastEvent('backButtonPressed')");
        }
    }

    public final void b(boolean z) {
        setCloseRegionDisabled(z);
        View rootView = getRootView();
        if (rootView != null) {
            CustomView customView = (CustomView) rootView.findViewById(65531);
            if (customView != null) {
                customView.setVisibility(this.D ? XZBDevice.Wait : 0);
            }
        }
    }

    public final void c(boolean z) {
        setUseCustomClose(z);
        if (getRootView() != null) {
            CustomView customView = (CustomView) getRootView().findViewById(65532);
            if (customView != null) {
                customView.setVisibility(this.A ? XZBDevice.Wait : 0);
            }
        }
    }

    public final void j() {
        if (RenderViewState.DEFAULT != this.i && RenderViewState.RESIZED != this.i) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Render view state must be either DEFAULT or RESIZED to admit the resize request");
        } else if (getResizeProperties() == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Render view state can not resize with invalid resize properties");
        } else {
            this.K = true;
            this.l.a();
            requestLayout();
            invalidate();
            this.B = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            setAndUpdateViewState(RenderViewState.RESIZED);
            this.h.e(this);
            this.K = false;
        }
    }

    public final void k() {
        z();
        this.m.b();
        if (RenderViewState.EXPANDED == this.i) {
            x();
        } else if (RenderViewState.RESIZED == this.i) {
            y();
        } else if (RenderViewState.DEFAULT == this.i) {
            setAndUpdateViewState(RenderViewState.HIDDEN);
            if (this.j.a() == PlacementType.FULL_SCREEN) {
                v();
            } else {
                ((ViewGroup) getParent()).removeAllViews();
            }
        }
        this.r.clear();
        this.B = false;
    }

    private void v() {
        Activity fullScreenActivity = getFullScreenActivity();
        if (fullScreenActivity != null) {
            InMobiAdActivity.b(this);
            ((InMobiAdActivity) fullScreenActivity).a(true);
            fullScreenActivity.finish();
            if (this.O != -1) {
                fullScreenActivity.overridePendingTransition(0, this.O);
            }
        }
    }

    public final void setFullScreenExitAnimation(int i) {
        this.O = i;
    }

    public final void l() {
        if (this.i == RenderViewState.RESIZED && getResizeProperties() != null) {
            this.l.a();
        }
    }

    public final void b(String str, String str2, String str3) {
        try {
            com.inmobi.commons.a.a.a(getRenderViewContext(), Intent.parseUri(str3, 0));
            getListener().g(this);
            a(str2, new StringBuilder("broadcastEvent('").append(str).append("Successful','").append(str3).append("');").toString());
        } catch (URISyntaxException e) {
            a(str2, new StringBuilder("Cannot resolve URI (").append(str3).append(SocializeConstants.OP_CLOSE_PAREN).toString(), str);
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Error message in processing openExternal: ").append(e.getMessage()).toString());
        } catch (ActivityNotFoundException e2) {
            a(str2, new StringBuilder("No app can handle the URI (").append(str3).append(SocializeConstants.OP_CLOSE_PAREN).toString(), str);
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Error message in processing openExternal: ").append(e2.getMessage()).toString());
        }
    }

    public final void c(String str, String str2, String str3) {
        if (str3 == null || (str3.startsWith(HttpConstant.HTTP) && !URLUtil.isValidUrl(str3))) {
            Logger.a(InternalLogLevel.INTERNAL, b, str + " called with invalid url (" + str3 + SocializeConstants.OP_CLOSE_PAREN);
            a(str2, "Invalid URL", str);
        } else if (!str3.startsWith(HttpConstant.HTTP) || str3.contains("play.google.com") || str3.contains("market.android.com") || str3.contains("market%3A%2F%2F")) {
            b(str, str2, str3);
        } else {
            w();
            InMobiAdActivity.c(this);
            Intent intent = new Intent(getRenderViewContext(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_buttonStyle);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str3);
            com.inmobi.commons.a.a.a(getRenderViewContext(), intent);
            a(str2, new StringBuilder("broadcastEvent('").append(str).append("Successful','").append(str3).append("');").toString());
        }
    }

    public final a getListener() {
        if (this.h != null) {
            return this.h;
        }
        a anonymousClass_4 = new a() {
            public void a(RenderView renderView) {
            }

            public void b(RenderView renderView) {
            }

            public void c(RenderView renderView) {
            }

            public void d(RenderView renderView) {
            }

            public void e(RenderView renderView) {
            }

            public void f(RenderView renderView) {
            }

            public void a(RenderView renderView, HashMap<Object, Object> hashMap) {
            }

            public void b(RenderView renderView, HashMap<Object, Object> hashMap) {
            }

            public void g(RenderView renderView) {
            }
        };
        this.h = anonymousClass_4;
        return anonymousClass_4;
    }

    public final RenderViewState getViewState() {
        return this.i;
    }

    public final MraidMediaProcessor getMediaProcessor() {
        return this.m;
    }

    public final d getExpandProperties() {
        return this.t;
    }

    public final void setExpandProperties(d dVar) {
        if (dVar.b()) {
            setUseCustomClose(dVar.a());
        }
        this.t = dVar;
    }

    public final m getResizeProperties() {
        return this.u;
    }

    public final void setResizeProperties(m mVar) {
        this.u = mVar;
    }

    public final void setAndUpdateViewState(RenderViewState renderViewState) {
        this.i = renderViewState;
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("set state:").append(this.i).toString());
        g(this.i.toString().toLowerCase(Locale.ENGLISH));
    }

    public final void m() {
        setVisibility(0);
        requestLayout();
    }

    public final void setAdActiveFlag(boolean z) {
        this.B = z;
    }

    public final l getOrientationProperties() {
        return this.v;
    }

    public final void setOrientationProperties(l lVar) {
        int i = 1;
        this.v = lVar;
        if (this.e != null && !lVar.a) {
            int i2;
            String str = lVar.b;
            Object obj = -1;
            switch (str.hashCode()) {
                case 729267099:
                    if (str.equals("portrait")) {
                        i2 = 1;
                    }
                    break;
                case 1430647483:
                    if (str.equals("landscape")) {
                        i2 = 0;
                    }
                    break;
            }
            switch (i2) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (!(DisplayInfo.b() == ORIENTATION_VALUES.LANDSCAPE.getValue() || DisplayInfo.b() == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue())) {
                        i = 0;
                    }
                    if (i != 0) {
                        if (ORIENTATION_VALUES.LANDSCAPE.getValue() == DisplayInfo.b()) {
                            this.e.setRequestedOrientation(0);
                        } else {
                            this.e.setRequestedOrientation(XZBDevice.Wait);
                        }
                    } else if (lVar.c.equals("left")) {
                        this.e.setRequestedOrientation(XZBDevice.Wait);
                    } else if (lVar.c.equals("right")) {
                        this.e.setRequestedOrientation(0);
                    }
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (DisplayInfo.b() == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        this.e.setRequestedOrientation(XZBDevice.Pause);
                    } else {
                        this.e.setRequestedOrientation(1);
                    }
                default:
                    if (DisplayInfo.b() == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        this.e.setRequestedOrientation(XZBDevice.Pause);
                    } else if (DisplayInfo.b() == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
                        this.e.setRequestedOrientation(XZBDevice.Wait);
                    } else if (DisplayInfo.b() == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
                        this.e.setRequestedOrientation(0);
                    } else {
                        this.e.setRequestedOrientation(1);
                    }
            }
        }
    }

    public final String getMraidJsString() {
        String b = new i().b();
        if (b == null) {
            b = "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var d=String(a);b[d]||(b[d]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var d in b)b[d].apply({},a)};this.toString=function(){var c=[a,\":\"],d;for(d in b)c.push(\"|\",d,\"|\");return c.join(\"\")}},\nInmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&window.imraidview.startListeningHeadphonePluggedEvents();\n\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent()}}catch(d){this.log(d)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\n\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(d){}}};this.sendSaveContentResult=\nfunction(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var d=arguments[c],d=JSON.parse(d);b[c]=d}else b[c]=arguments[c];d=b[1];\"success\"!=d&&(c=b[0].substring(b[0].indexOf(\"_\")+1),imraid.saveContentIDMap[c]&&delete imraid.saveContentIDMap[c]);window.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=\n!0,window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),window.location=a)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){for(var b=this.urlScheme+\"://\"+a,c,d=!0,e=1;e<arguments.length;e+=2)c=arguments[e+1],null!=c&&(d?(b+=\"?\",d=!1):b+=\"&\",b+=arguments[e]+\"=\"+escape(c));__im__iosNativeCall.executeNativeCall(b);return\"OK\"};this.nativeCallComplete=function(a){__im__iosNativeCall.nativeCallComplete(a);\nreturn\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={};a.orientationProperties={allowOrientationChange:!0,forceOrientation:\"none\",direction:\"right\"};var b=[],c=!1;a.detectAndBlockFraud=function(d){a.isPossibleFraud()&&a.fireRedirectFraudBeacon(d);return!1};a.zeroPad=function(a){var e=\"\";10>a&&(e+=\"0\");return e+a};a.supports=function(a){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof a)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",\na)};a.useCustomClose=function(a){try{sdkController.useCustomClose(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"use CustomClose: \"+e)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};a.stackCommands=function(a,e){c?b.push(a):(eval(a),e&&(c=!0))};a.expand=function(a){try{\"undefined\"==typeof a&&(a=null),sdkController.expand(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"executeNativeExpand: \"+e+\", URL = \"+a)}};a.setExpandProperties=\nfunction(d){try{d?this.props=d:d=null;if(\"undefined\"!=typeof d.lockOrientation&&null!=d.lockOrientation&&\"undefined\"!=typeof d.orientation&&null!=d.orientation){var e={};e.allowOrientationChange=!d.lockOrientation;e.forceOrientation=d.orientation;a.setOrientationProperties(e)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(d))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+d)}};a.getExpandProperties=function(){try{return eval(\"(\"+sdkController.getExpandProperties(\"window.mraidview\")+\n\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(d){try{d?(\"undefined\"!=typeof d.allowOrientationChange&&(a.orientationProperties.allowOrientationChange=d.allowOrientationChange),\"undefined\"!=typeof d.forceOrientation&&(a.orientationProperties.forceOrientation=d.forceOrientation)):d=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(a.orientationProperties))}catch(e){imraidview.showAlert(\"setOrientationProperties: \"+e+\", props = \"+\nd)}};a.getOrientationProperties=function(){return{forceOrientation:a.orientationProperties.forceOrientation,allowOrientationChange:a.orientationProperties.allowOrientationChange}};a.resizeProps=null;a.setResizeProperties=function(d){var e,b;try{e=parseInt(d.width);b=parseInt(d.height);if(isNaN(e)||isNaN(b)||1>e||1>b)throw\"Invalid\";d.width=e;d.height=b;a.resizeProps=d;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(d))}catch(c){window.mraid.broadcastEvent(\"error\",\"Invalid properties.\",\n\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"open: \"+e)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+a)}};a.getMaxSize=\nfunction(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var e=\"\";null!=a&&(e=a);try{sdkController.playVideo(\"window.mraidview\",\ne)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(d){if(\"undefined\"===typeof JSON){var e=\"\",b;if(\"undefined\"==typeof d.length)return a.stringifyArg(d);for(b=0;b<d.length;b++)0<b&&(e+=\",\"),e+=a.stringifyArg(d[b]);return e+\"]\"}return JSON.stringify(d)};a.stringifyArg=function(a){var e,b,c;b=typeof a;e=\"\";if(\"number\"===b||\"boolean\"===b)e+=args;else if(a instanceof Array)e=e+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;e+=\"{\";for(c in a)null!==a[c]&&(b||(e+=\",\"),e=e+'\"'+c+'\":',b=\ntypeof a[c],e=\"number\"===b||\"boolean\"===b?e+a[c]:\"function\"===typeof a[c]?e+'\"\"':a[c]instanceof Object?e+this.stringify(args[i][c]):e+'\"'+a[c]+'\"',b=!1);e+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),e=e+'\"'+a+'\"';imraidview.showAlert(\"json:\"+e);return e};getPID=function(a){var e=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(e=a.id);return e};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(b){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var e={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)e={};else{\"string\"==typeof a.summary&&(e.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(e.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(e.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(e.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?e.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}e.daysInMonth=a.recurrence.daysInMonth;e.daysInYear=a.recurrence.daysInYear;e.weeksInMonth=a.recurrence.weeksInMonth;e.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(e),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var e=0;e<a.length;e++)switch(a[e]){case 0:a[e]=\"SU\";break;case 1:a[e]=\"MO\";break;case 2:a[e]=\"TU\";break;case 3:a[e]=\"WE\";break;case 4:a[e]=\"TH\";\nbreak;case 5:a[e]=\"FR\";break;case 6:a[e]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(b){console.log(\"bridge: storePicture\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nb);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,e){};a.fireMediaErrorEvent=function(a,e){};a.fireMediaTimeUpdateEvent=function(a,e,b){};a.fireMediaCloseEvent=function(a,e,b){};a.fireMediaVolumeChangeEvent=function(a,e,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview,c=!1;b.isAdShownToUser=!1;b.onUserInteraction=function(){c=!0};b.isPossibleFraud=function(){return a.supports(\"redirectFraudDetection\")&&(!b.isAdShownToUser||!c)};b.fireRedirectFraudBeacon=function(a){if(\"undefined\"!=typeof inmobi&&inmobi.recordEvent){var e={};e.trigger=a;e.isAdShown=b.isAdShownToUser.toString();inmobi.recordEvent(135,e)}};window.onbeforeunload=function(){b.detectAndBlockFraud(\"redirect\")};a.addEventListener(\"viewableChange\",\nfunction(a){a&&!b.isAdShownToUser&&(b.isAdShownToUser=!0)});a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;a.setOrientationProperties=\nb.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=function(a){b.detectAndBlockFraud(\"mraid.createCalendarEvent\")||b.createCalendarEvent(a)};a.open=function(c){b.detectAndBlockFraud(\"mraid.open\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c))};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};\na.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=b.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.setOrientationProperties=function(e){try{e?(\"undefined\"!=typeof e.allowOrientationChange&&(mraidview.orientationProperties.allowOrientationChange=e.allowOrientationChange),\"undefined\"!=typeof e.forceOrientation&&(mraidview.orientationProperties.forceOrientation=e.forceOrientation),\"undefined\"!=typeof e.direction&&(mraidview.orientationProperties.direction=e.direction)):e=null,sdkController.setOrientationProperties(\"window.imraidview\",mraidview.stringify(mraidview.orientationProperties))}catch(b){a.showAlert(\"setOrientationProperties: \"+\nb+\", props = \"+e)}};a.getOrientationProperties=function(){return mraidview.orientationProperties};a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var d=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=\nfunction(){b=a.getWindowOrientation();window.addEventListener(\"resize\",d,!1);window.addEventListener(\"orientationchange\",d,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",d,!1);window.removeEventListener(\"orientationchange\",d,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,b){var c={};c.name=a;var d=\"inmobi_media_\"+a;\"undefined\"!=typeof b&&(null!=\nb&&\"\"!=b)&&(d=d+\"_\"+b);window.imraid.broadcastEvent(d,c)};a.fireMediaErrorEvent=function(a,b){var c={name:\"error\"};c.code=b;var d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.fireMediaTimeUpdateEvent=function(a,b,c){var d={name:\"timeupdate\",target:{}};d.target.currentTime=b;d.target.duration=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.saveContent=function(a,\nb,c){window.imraid.addEventListener(\"saveContent_\"+a,c);sdkController.saveContent(\"window.imraidview\",a,b)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,b,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"galleryImageSelected\",d)};a.fireCameraPictureCatpturedEvent=function(a,\nb,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"cameraPictureCaptured\",d)};a.fireMediaCloseEvent=function(a,b,c){var d={name:\"close\"};d.viaUserInteraction=b;d.target={};d.target.currentTime=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireMediaVolumeChangeEvent=function(a,b,c){var d={name:\"volumechange\",target:{}};d.target.volume=b;d.target.muted=c;b=\"inmobi_media_\"+d.name;\n\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b){try{sdkController.openExternal(\"window.imraidview\",\nb)}catch(c){a.showAlert(\"openExternal: \"+c)}};a.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(c){a.showAlert(\"log: \"+c)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(c){a.showAlert(\"asyncPing: \"+c)}};a.makeCall=function(b){try{b.startsWith(\"tel:\")?sdkController.openExternal(\"window.imraidview\",b):sdkController.openExternal(\"window.imraidview\",\"tel:\"+b)}catch(c){a.showAlert(\"makeCall: \"+c)}};a.sendMail=\nfunction(b,c,d){try{null==c&&(c=\"\"),null==d&&(d=\"\"),sdkController.sendMail(\"window.imraidview\",b,c,d)}catch(f){a.showAlert(\"sendMail: \"+f)}};a.sendSMS=function(b,c){try{null==c&&(c=\"\"),sdkController.sendSMS(\"window.imraidview\",b,c)}catch(d){a.showAlert(\"sendSMS: \"+d)}};a.pauseAudio=function(b){try{var c=getPID(b);sdkController.pauseAudio(\"window.imraidview\",c)}catch(d){a.showAlert(\"pauseAudio: \"+d)}};a.muteAudio=function(b){try{var c=getPID(b);sdkController.muteAudio(\"window.imraidview\",c)}catch(d){a.showAlert(\"muteAudio: \"+\nd)}};a.unMuteAudio=function(b){try{var c=getPID(b);sdkController.unMuteAudio(\"window.imraidview\",c)}catch(d){a.showAlert(\"unMuteAudio: \"+d)}};a.isAudioMuted=function(b){try{var c=getPID(b);return sdkController.isAudioMuted(\"window.imraidview\",c)}catch(d){a.showAlert(\"isAudioMuted: \"+d)}};a.setAudioVolume=function(b,c){try{var d=getPID(b);c=parseInt(c);sdkController.setAudioVolume(\"window.imraidview\",d,c)}catch(f){a.showAlert(\"setAudioVolume: \"+f)}};a.getAudioVolume=function(b){try{var c=getPID(b);\nreturn sdkController.getAudioVolume(\"window.imraidview\",c)}catch(d){a.showAlert(\"getAudioVolume: \"+d)}};a.seekAudio=function(b,c){try{var d=getPID(b);c=parseInt(c);isNaN(c)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekAudio\"):sdkController.seekAudio(\"window.imraidview\",d,c)}catch(f){a.showAlert(\"seekAudio: \"+f)}};a.playVideo=function(b,c){var d=!1,f=!0,g=!0,p=!1,m=-99999,k=-99999,n=-99999,q=-99999,r=\"normal\",s=\"exit\",t=\"\",u=getPID(c);null!=b&&(t=b);null!=c&&(\"undefined\"!=\ntypeof c.audio&&\"muted\"==c.audio&&(d=!0),\"undefined\"!=typeof c.autoplay&&!1===c.autoplay&&(f=!1),\"undefined\"!=typeof c.controls&&!1===c.controls&&(g=!1),\"undefined\"!=typeof c.loop&&!0===c.loop&&(p=!0),\"undefined\"!=typeof c.inline&&null!=c.inline&&(m=c.inline.top,k=c.inline.left),\"undefined\"!=typeof c.width&&null!=c.width&&(n=c.width),\"undefined\"!=typeof c.height&&null!=c.height&&(q=c.height),\"undefined\"!=typeof c.startStyle&&null!=c.startStyle&&(r=c.startStyle),\"undefined\"!=typeof c.stopStyle&&null!=\nc.stopStyle&&(s=c.stopStyle),m=parseInt(m).toString(),k=parseInt(k).toString(),n=parseInt(n).toString(),q=parseInt(q).toString());try{sdkController.playVideo(\"window.imraidview\",t,d,f,g,p,m,k,n,q,r,s,u)}catch(v){a.showAlert(\"playVideo: \"+v)}};a.playAudio=function(b,c){var d=!0,f=!1,g=\"normal\",p=\"normal\",m=!0,k=\"\",n=getPID(c);null!=b&&(k=b);null!=c&&(\"undefined\"!=typeof c.autoplay&&!1===c.autoplay&&(d=!1),\"undefined\"!=typeof c.loop&&!0===c.loop&&(f=!0),\"undefined\"!=typeof c.startStyle&&null!=c.startStyle&&\n(g=c.startStyle),\"undefined\"!=typeof c.stopStyle&&null!=c.stopStyle&&(p=c.stopStyle),\"fullscreen\"==g&&(m=!0));try{sdkController.playAudio(\"window.imraidview\",k,d,m,f,g,p,n)}catch(q){a.showAlert(\"playAudio: \"+q)}};a.pauseVideo=function(b){try{var c=getPID(b);sdkController.pauseVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"pauseVideo: \"+d)}};a.closeVideo=function(b){try{var c=getPID(b);sdkController.closeVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"closeVideo: \"+d)}};a.hideVideo=function(b){try{var c=\ngetPID(b);sdkController.hideVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"hideVideo: \"+d)}};a.showVideo=function(b){try{var c=getPID(b);sdkController.showVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"showVideo: \"+d)}};a.muteVideo=function(b){try{var c=getPID(b);sdkController.muteVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"muteVideo: \"+d)}};a.unMuteVideo=function(b){try{var c=getPID(b);sdkController.unMuteVideo(\"window.imraidview\",c)}catch(d){a.showAlert(\"unMuteVideo: \"+d)}};a.seekVideo=\nfunction(b,c){try{var d=getPID(b);c=parseInt(c);isNaN(c)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekVideo\"):sdkController.seekVideo(\"window.imraidview\",d,c)}catch(f){a.showAlert(\"seekVideo: \"+f)}};a.isVideoMuted=function(b){try{var c=getPID(b);return sdkController.isVideoMuted(\"window.imraidview\",c)}catch(d){a.showAlert(\"isVideoMuted: \"+d)}};a.setVideoVolume=function(b,c){try{var d=getPID(b);c=parseInt(c);sdkController.setVideoVolume(\"window.imraidview\",d,c)}catch(f){a.showAlert(\"setVideoVolume: \"+\nf)}};a.getVideoVolume=function(b){try{var c=getPID(b);return sdkController.getVideoVolume(\"window.imraidview\",c)}catch(d){a.showAlert(\"getVideoVolume: \"+d)}};a.startListeningMicIntensity=function(){mraidview.onUserInteraction();sdkController.registerMicListener(\"window.imraidview\")};a.stopListeningMicIntensity=function(){sdkController.unRegisterMicListener(\"window.imraidview\")};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=\nfunction(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};a.startListeningVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};\ngetSdkVersionInt=function(){for(var b=a.getSdkVersion().split(\".\"),c=b.length,d=\"\",f=0;f<c;f++)d+=b[f];return parseInt(d)};a.vibrate=function(a){if(null==a||420>getSdkVersionInt())sdkController.vibrate(\"window.imraidview\");else{if(0===arguments.length)return sdkController.vibrate(\"window.imraidview\"),null;if(1==arguments.length)0===a||\"length\"in a&&0===a.length?sdkController.vibrate(\"window.imraidview\",\"[]\",-1):sdkController.vibrate(\"window.imraidview\",\"[0,\"+a+\"]\",-1);else{var b=Array.prototype.slice.call(arguments);\nsdkController.vibrate(\"window.imraidview\",\"[0,\"+String(b)+\"]\",-1)}}};a.takeCameraPicture=function(){sdkController.takeCameraPicture(\"window.imraidview\")};a.getGalleryImage=function(){return sdkController.getGalleryImage(\"window.imraidview\")};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=\nsdkController.supports(\"window.imraidview\",a)};a.postToSocial=function(a,b,c,d){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof d&&(d=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,d))};a.getMicIntensity=function(){return sdkController.getMicIntensity(\"window.imraidview\")};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",\nnull);else try{sdkController.incentCompleted(\"window.imraidview\",JSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+b)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",\nmraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+c+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=sdkController.isDeviceMuted(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,\narguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,arguments)};a.disableBackButton=function(a){void 0==a||\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};\na.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.setOrientationProperties=b.setOrientationProperties;a.getOrientationProperties=b.getOrientationProperties;a.saveContentIDMap={};a.saveContent=function(c,d,e){var l=arguments.length,h,f=null;if(3>l){if(\"function\"===typeof arguments[l-1])h=arguments[l-1];else return;f={reason:1}}else a.saveContentIDMap[c]&&(h=arguments[2],f={reason:11,url:arguments[1]});\"function\"!==!h&&(f?(window.imraid.addEventListener(\"saveContent_failed_\"+\nc,h),window.imraid.sendSaveContentResult(\"saveContent_failed_\"+c,\"failed\",JSON.stringify(f))):(a.removeEventListener(\"saveContent_\"+c),a.saveContentIDMap[c]=!0,b.saveContent(c,d,e)))};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\n\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.makeCall=function(c){mraidview.detectAndBlockFraud(\"imraid.makeCall\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must provide a number to call.\",\"makeCall\"):b.makeCall(c))};a.sendMail=function(c,d,e){mraidview.detectAndBlockFraud(\"imraid.sendMail\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendMail\"):b.sendMail(c,d,e))};a.sendSMS=function(c,\nd){mraidview.detectAndBlockFraud(\"imraid.sendSMS\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendSMS\"):b.sendSMS(c,d))};a.playAudio=function(a,d){\"object\"!=typeof d?\"string\"==typeof a?b.playAudio(a,null):\"object\"==typeof a?b.playAudio(null,a):b.playAudio(null,null):b.playAudio(a,d)};a.getGalleryImage=b.getGalleryImage;a.pauseAudio=b.pauseAudio;a.muteAudio=b.muteAudio;a.unMuteAudio=b.unMuteAudio;a.isAudioMuted=b.isAudioMuted;a.setAudioVolume=function(c){if(\"object\"!=\ntypeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var d=c.volume;isNaN(d)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setAudioVolume\"):(0>d?d=0:100<d&&(d=100),b.setAudioVolume(c,d))}};a.getAudioVolume=b.getAudioVolume;a.pauseVideo=b.pauseVideo;a.closeVideo=b.closeVideo;a.hideVideo=b.hideVideo;a.showVideo=b.showVideo;a.muteVideo=b.muteVideo;a.unMuteVideo=b.unMuteVideo;a.isVideoMuted=b.isVideoMuted;a.setVideoVolume=\nfunction(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var d=c.volume;isNaN(d)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setVideoVolume\"):(0>d?d=0:100<d&&(d=100),b.setVideoVolume(c,d))}};a.getVideoVolume=b.getVideoVolume;a.seekAudio=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"seekAudio\");else{var d=c.time;imIsObjValid(d)?\nb.seekAudio(c,d):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekAudio\")}};a.seekVideo=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\");else{var d=c.time;imIsObjValid(d)?b.seekVideo(c,d):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\")}};a.openExternal=function(a){mraidview.detectAndBlockFraud(\"imraid.openExternal\")||b.openExternal(a)};a.updateToPassbook=function(c){mraidview.detectAndBlockFraud(\"imraid.updateToPassbook\")||\n(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c))};a.vibrate=b.vibrate;a.takeCameraPicture=b.takeCameraPicture;a.getMicIntensity=function(){return!imIsObjValid(a.listeners.micIntensityChange)?-1:b.getMicIntensity()};a.postToSocial=function(a,d,e,l){mraidview.detectAndBlockFraud(\"imraid.postToSocial\")||b.postToSocial(a,d,e,l)};a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=\nfunction(a){mraidview.detectAndBlockFraud(\"imraid.showSKStore\")||b.showSKStore(a)};a.playVideo=function(a,d){\"object\"!=typeof d?\"string\"==typeof a?b.playVideo(a,null):\"object\"==typeof a?b.playVideo(null,a):b.playVideo(null,null):b.playVideo(a,d)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=\nfunction(){return b.getDeviceVolume()};a.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.hideStatusBar=function(){b.hideStatusBar()};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=b.isBackButtonDisabled})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(d){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a){sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||null==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",\nJSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var e=-1;try{e=a.indexOf(b)}catch(l){}return e};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,d,e){if(!imIsObjValid(d)||!imIsObjValid(e))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+d+\",\"+e):a+(\"&u-tap-o=\"+d+\",\"+e));return a};b.performAdClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,l=a.landingConfig;if(!imIsObjValid(e)&&!imIsObjValid(l))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,p=null,m=null,k=null,n=null;if(imIsObjValid(d))try{p=d.changedTouches[0].pageX,m=d.changedTouches[0].pageY}catch(q){m=\np=0}imIsObjValid(l)?imIsObjValid(e)?(k=l.url,n=l.urlType,h=e.url,f=e.pingWV,g=e.fr):(k=l.url,n=l.urlType):(k=e.url,n=e.urlType);e=b.getPlatform();try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=!0;if(0>f||1<f)f=!0;if(\"number\"!=typeof n||null==n)n=0;h=b.appendTapParams(h,p,m);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");if(imIsObjValid(k))switch(imIsObjValid(h)||(k=b.appendTapParams(k,\np,m)),n){case 1:b.openEmbedded(k);break;case 2:\"ios\"==e?b.ios.openItunesProductView(k):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+e);break;default:b.openExternal(k)}else b.log(\"Landing url provided is null.\")}catch(r){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,l=a.landingConfig;if(!imIsObjValid(e)&&!imIsObjValid(l))b.log(\"click/landing config are invalid, Nothing to process .\"),\nthis.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,p=null,m=null;if(imIsObjValid(d))try{p=d.changedTouches[0].pageX,m=d.changedTouches[0].pageY}catch(k){m=p=0}imIsObjValid(e)&&(h=e.url,f=e.pingWV,g=e.fr);try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=!0;if(0>f||1<f)f=!0;h=b.appendTapParams(h,p,m);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):\nb.log(\"clickurl provided is null.\");b.onUserInteraction(l)}catch(n){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=function(b){mraidview.detectAndBlockFraud(\"imai.openEmbedded\")||a.openEmbedded(b)};b.openExternal=function(b){mraidview.detectAndBlockFraud(\"imai.openExternal\")||a.openExternal(b)};\nb.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.loadSKStore=a.loadSKStore;b.showSKStore=function(b){mraidview.detectAndBlockFraud(\"imai.showSKStore\")||a.showSKStore(b)};b.ios.openItunesProductView=function(b){mraidview.detectAndBlockFraud(\"imai.ios.openItunesProductView\")||a.ios.openItunesProductView(b)};b.fireAdReady=a.fireAdReady;b.fireAdFailed=a.fireAdFailed})();";
            Logger.a(InternalLogLevel.INTERNAL, b, "Returning default Mraid Js string.");
            return b;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Returning fetched Mraid Js string.");
        return b;
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.n.a(str, getRenderViewContext(), str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public final void a(String str, int i, String str2, String str3, String str4) {
        if (f("postToSocial")) {
            this.n.a(str, getRenderViewContext(), i, str2, str3, str4);
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "postToSocial called even when it is not supported");
    }

    public final void d(String str) {
        if (!f("vibrate")) {
            Logger.a(InternalLogLevel.INTERNAL, b, "vibrate called despite the fact that it is not supported");
        } else if (a()) {
            this.n.a(str, getRenderViewContext());
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Creative not visible. Will not vibrate.");
            a(str, "Creative not visible. Will not vibrate.", "vibrate");
        }
    }

    public final void c(String str, String str2, int i) {
        if (!f("vibrate")) {
            Logger.a(InternalLogLevel.INTERNAL, b, "vibrate called despite the fact that it is not supported");
        } else if (a()) {
            this.n.a(str, getRenderViewContext(), str2, i);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, "Creative not visible. Will not vibrate.");
            a(str, "Creative not visible. Will not vibrate.", "vibrate");
        }
    }

    private void w() {
        this.m.c();
    }

    private void x() {
        if (RenderViewState.DEFAULT != this.i) {
            this.K = true;
            this.k.a();
            v();
            this.K = false;
        }
    }

    private void y() {
        if (RenderViewState.DEFAULT != this.i) {
            this.K = true;
            this.l.b();
            setAndUpdateViewState(RenderViewState.DEFAULT);
            this.h.f(this);
            this.K = false;
        }
    }

    public final void d(String str, String str2, String str3) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("saveContent called: content ID: ").append(str2).append("; URL: ").append(str3).toString());
        JSONObject jSONObject;
        if (f("saveContent")) {
            File file = new File(com.inmobi.commons.a.a.a(getRenderViewContext()), String.valueOf(hashCode()));
            if (file.mkdirs() || file.isDirectory()) {
                c cVar = new c(str, new File(file, UUID.randomUUID().toString()), str3, str2, this);
                this.d.add(cVar);
                cVar.execute(new Void[0]);
                return;
            }
            Logger.a(InternalLogLevel.INTERNAL, b, "Cannot create temp directory to save ");
            jSONObject = new JSONObject();
            try {
                jSONObject.put(SocialConstants.PARAM_URL, str3);
                jSONObject.put(DownloadManager.COLUMN_REASON, XZBDevice.Pause);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            a(str, new StringBuilder("sendSaveContentResult(\"saveContent_").append(str2).append("\", 'failed', \"").append(jSONObject.toString().replace(com.alipay.sdk.util.h.f, "\\\"")).append("\");").toString());
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "saveContent called despite the fact that it is not supported");
        jSONObject = new JSONObject();
        try {
            jSONObject.put(SocialConstants.PARAM_URL, str3);
            jSONObject.put(DownloadManager.COLUMN_REASON, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        a(str, new StringBuilder("sendSaveContentResult(\"saveContent_").append(str2).append("\", 'failed', \"").append(jSONObject.toString().replace(com.alipay.sdk.util.h.f, "\\\"")).append("\");").toString());
    }

    public final void e(String str) {
        for (c cVar : this.d) {
            if (str != null && str.trim().length() != 0 && str.equals(cVar.a())) {
                cVar.cancel(true);
                return;
            }
        }
    }

    private void z() {
        for (c cVar : this.d) {
            cVar.cancel(true);
        }
        this.d.clear();
        com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.a(getRenderViewContext()), String.valueOf(hashCode()));
    }

    private void h(String str) {
        loadUrl(str);
    }

    @TargetApi(19)
    private void i(String str) {
        evaluateJavascript(str, null);
    }

    public final void n() {
        Logger.a(InternalLogLevel.INTERNAL, b, "disableHardwareAcceleration called.");
        this.L = false;
        if (VERSION.SDK_INT >= 14) {
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, b, "disableHardwareAcceleration failed.", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, b, "disableHardwareAcceleration failed.", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, b, "disableHardwareAcceleration failed.", e22);
            } catch (Throwable e222) {
                Logger.a(InternalLogLevel.INTERNAL, b, "disableHardwareAcceleration failed.", e222);
            }
        }
    }

    public final boolean o() {
        return this.L;
    }

    @TargetApi(16)
    public final boolean f(String str) {
        PackageManager packageManager = getRenderViewContext().getPackageManager();
        Object obj = -1;
        int i;
        switch (str.hashCode()) {
            case -1886160473:
                if (str.equals("playVideo")) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
            case -1647691422:
                if (str.equals("inlineVideo")) {
                    obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                }
                break;
            case -587360353:
                if (str.equals("getGalleryImage")) {
                    obj = XZBDevice.Fail;
                }
                break;
            case -178324674:
                if (str.equals("calendar")) {
                    obj = XZBDevice.Stop;
                }
                break;
            case 114009:
                if (str.equals("sms")) {
                    obj = R.styleable.Toolbar_contentInsetLeft;
                }
                break;
            case 114715:
                if (str.equals("tel")) {
                    i = 1;
                }
                break;
            case 451310959:
                if (str.equals("vibrate")) {
                    obj = XZBDevice.Predownload;
                }
                break;
            case 459238621:
                if (str.equals("storePicture")) {
                    obj = XZBDevice.Upload;
                }
                break;
            case 1247233375:
                if (str.equals("sendMail")) {
                    obj = XZBDevice.Pause;
                }
                break;
            case 1370921258:
                if (str.equals("microphone")) {
                    obj = XZBDevice.DOWNLOAD_LIST_ALL;
                }
                break;
            case 1509574865:
                if (str.equals("html5video")) {
                    obj = R.styleable.Toolbar_contentInsetEnd;
                }
                break;
            case 1642189884:
                if (str.equals("saveContent")) {
                    obj = XZBDevice.Wait;
                }
                break;
            case 1772979069:
                if (str.equals("redirectFraudDetection")) {
                    boolean z = false;
                }
                break;
            case 1895570642:
                if (str.equals("takeCameraPicture")) {
                    i = 11;
                }
                break;
            case 1921345160:
                if (str.equals("postToSocial")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
        }
        switch (z) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return true;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return true;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
                if (VERSION.SDK_INT < 11) {
                    z = true;
                } else if (this.s && o()) {
                    z = true;
                } else {
                    z = false;
                }
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("HTML5 video supported:").append(z).toString());
                return z;
            case XZBDevice.Wait:
                return VERSION.SDK_INT >= 19 || packageManager.checkPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, packageManager.getNameForUid(Binder.getCallingUid())) == 0;
            case XZBDevice.Stop:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setType("vnd.android.cursor.item/event");
                return getRenderViewContext().getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) != null && com.inmobi.commons.a.a.a("android.permission.WRITE_CALENDAR") && com.inmobi.commons.a.a.a("android.permission.READ_CALENDAR");
            case XZBDevice.Fail:
                ResolveInfo resolveActivity = getRenderViewContext().getPackageManager().resolveActivity(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), AccessibilityNodeInfoCompat.ACTION_CUT);
                if (VERSION.SDK_INT >= 16) {
                    z = com.inmobi.commons.a.a.a("android.permission.READ_EXTERNAL_STORAGE");
                } else {
                    z = true;
                }
                return resolveActivity != null && z;
            case XZBDevice.Upload:
                return com.inmobi.commons.a.a.a(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE);
            case XZBDevice.Predownload:
                boolean z2;
                if (packageManager.checkPermission("android.permission.VIBRATE", packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
                    int i2 = 1;
                } else {
                    z2 = false;
                }
                Vibrator vibrator = (Vibrator) getRenderViewContext().getSystemService("vibrator");
                return z2 && vibrator != null && VERSION.SDK_INT >= 11 && a(vibrator);
            default:
                return false;
        }
    }

    @TargetApi(11)
    private boolean a(Vibrator vibrator) {
        return vibrator.hasVibrator();
    }
}
