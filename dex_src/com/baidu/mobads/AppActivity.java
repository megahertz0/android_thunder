package com.baidu.mobads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.baidu.mobads.command.XAdLandingPageExtraInfo;
import com.baidu.mobads.g.d;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.j;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.vo.a.c;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppActivity extends Activity {
    protected static final int ACTIONBAR_VIEW_ID = 1001;
    public static final String EXTRA_DATA = "EXTRA_DATA";
    private static int G;
    private static ActionBarColorTheme J;
    private static final String o;
    private static AtomicBoolean p;
    private c A;
    private XAdLandingPageExtraInfo B;
    private View C;
    private final IXAdLogger D;
    private IXAdCommonUtils E;
    private boolean F;
    private PageFinishedListener H;
    private boolean I;
    float a;
    public Handler actionBarHandler;
    float b;
    com.baidu.mobads.g.a c;
    public ac curWebview;
    RelativeLayout d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    String k;
    String l;
    View m;
    protected d mBottomView;
    protected boolean mBottomViewIsShowing;
    HandlerThread n;
    private Handler q;
    private int r;
    private long s;
    private String t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private boolean z;

    public static class ActionBarColorTheme {
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_BLACK_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_BLUE_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_COFFEE_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_GREEN_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_NAVYBLUE_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_RED_THEME;
        public static final com.baidu.mobads.AppActivity.ActionBarColorTheme ACTION_BAR_WHITE_THEME;
        private int a;
        private int b;
        private int c;
        private int d;

        static {
            ACTION_BAR_WHITE_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-5987164, -6842473, -11113262, -328966);
            ACTION_BAR_RED_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, -12510, -1294276);
            ACTION_BAR_GREEN_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, -11113262, -14303071);
            ACTION_BAR_NAVYBLUE_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, 16764706, -14210226);
            ACTION_BAR_BLUE_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, -12510, -13870424);
            ACTION_BAR_COFFEE_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, -12510, -11255230);
            ACTION_BAR_BLACK_THEME = new com.baidu.mobads.AppActivity.ActionBarColorTheme(-1, -1, -12510, -13749450);
        }

        public ActionBarColorTheme(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public ActionBarColorTheme(com.baidu.mobads.AppActivity.ActionBarColorTheme actionBarColorTheme) {
            this.a = actionBarColorTheme.a;
            this.b = actionBarColorTheme.b;
            this.c = actionBarColorTheme.c;
            this.d = actionBarColorTheme.d;
        }

        public int getCloseColor() {
            return this.a;
        }

        public void setCloseColor(int i) {
            this.a = i;
        }

        public int getTitleColor() {
            return this.b;
        }

        public void setTitleColor(int i) {
            this.b = i;
        }

        public int getProgressColor() {
            return this.c;
        }

        public void setProgressColor(int i) {
            this.c = i;
        }

        public int getBackgroundColor() {
            return this.d;
        }

        public void setBackgroundColor(int i) {
            this.d = i;
        }

        public boolean equals(Object obj) {
            com.baidu.mobads.AppActivity.ActionBarColorTheme actionBarColorTheme = (com.baidu.mobads.AppActivity.ActionBarColorTheme) obj;
            return this.d == actionBarColorTheme.d && this.b == actionBarColorTheme.b && this.a == actionBarColorTheme.a && this.c == actionBarColorTheme.c;
        }
    }

    public static interface PageFinishedListener {
        void onPageFinished(WebView webView);
    }

    class a extends View {
        private Paint b;
        private int c;
        private int d;

        public a(Context context) {
            super(context);
            this.c = 0;
            this.d = 0;
            this.b = new Paint();
            this.b.setColor(J.getProgressColor());
            this.d = AppActivity.this.E.getScreenRect(AppActivity.this.getApplicationContext()).width();
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, (float) ((this.d * this.c) / 100), (float) getLayoutParams().height, this.b);
        }

        public void a(int i) {
            if (i != this.c) {
                this.c = i;
                postInvalidate();
            }
        }
    }

    class b {
        boolean a;
        String b;
        boolean c;
        boolean d;

        b() {
            this.a = false;
            this.b = com.umeng.a.d;
            this.c = false;
            this.d = true;
        }
    }

    public AppActivity() {
        this.a = 1.0f;
        this.b = 1.0f;
        this.c = null;
        this.q = null;
        this.r = 0;
        this.e = 0;
        this.s = 0;
        this.f = 0;
        this.g = -1;
        this.h = 0;
        this.i = 0;
        this.u = false;
        this.v = 1;
        this.w = 0;
        this.x = -1;
        this.y = false;
        this.z = false;
        this.j = 1;
        this.k = "barC";
        this.l = com.umeng.a.d;
        this.D = m.a().f();
        this.F = true;
        this.m = null;
        this.mBottomViewIsShowing = false;
        this.n = new HandlerThread("handler_thread");
        this.I = true;
    }

    static /* synthetic */ int i(AppActivity appActivity) {
        int i = appActivity.r;
        appActivity.r = i + 1;
        return i;
    }

    static {
        o = AppActivity.class.getSimpleName();
        p = new AtomicBoolean(false);
        G = 39;
        J = ActionBarColorTheme.ACTION_BAR_WHITE_THEME;
    }

    public static boolean isAppActivityOpening() {
        return p.get();
    }

    @TargetApi(3)
    private RelativeLayout a(String str) {
        View aVar = new a(this);
        this.curWebview = new ac(this, true, true);
        b bVar = new b();
        this.curWebview.a = str;
        this.curWebview.getSettings().setUseWideViewPort(true);
        this.curWebview.getSettings().setBuiltInZoomControls(true);
        try {
            WebSettings.class.getMethod("setDisplayZoomControls", new Class[]{Boolean.TYPE}).invoke(this.curWebview.getSettings(), new Object[]{Boolean.valueOf(false)});
        } catch (Exception e) {
            this.D.d(o, e.getMessage());
        }
        this.curWebview.setWebChromeClient(new d(this, aVar));
        this.curWebview.setOnTouchListener(new i(this));
        this.curWebview.setWebViewClient(new j(this, bVar));
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.m = d();
        relativeLayout.addView(this.m);
        relativeLayout.addView(this.curWebview, new LayoutParams(-1, -1));
        relativeLayout.addView(aVar, new LayoutParams(-1, this.E.getPixel(getApplicationContext(), XZBDevice.DOWNLOAD_LIST_RECYCLE)));
        return relativeLayout;
    }

    private View d() {
        View aeVar = new ae(this);
        int pixel = m.a().m().getPixel(this, XZBDevice.FailInServer);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(pixel, pixel);
        layoutParams.addRule(XZBDevice.Upload);
        aeVar.setLayoutParams(layoutParams);
        return aeVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        p.set(true);
        this.u = k();
        this.E = m.a().m();
        Intent intent = getIntent();
        String stringExtra;
        if (intent.getBooleanExtra("dealWithDownload", false)) {
            try {
                int intExtra = intent.getIntExtra(Impl.COLUMN_STATUS, -1);
                String stringExtra2 = intent.getStringExtra(IXAdRequestInfo.PACKAGE);
                IOAdDownloader adsApkDownloader = com.baidu.mobads.openad.c.d.a(getApplicationContext()).getAdsApkDownloader(stringExtra2);
                this.D.d(o, new StringBuilder("dealWithDownload now: status=").append(intExtra).append(";pk=").append(stringExtra2).append(";downloader=").append(adsApkDownloader).toString());
                if (intExtra == DownloadStatus.COMPLETED.getCode()) {
                    stringExtra = intent.getStringExtra("localApkPath");
                    if (m.a().l().isInstalled(this, stringExtra2)) {
                        m.a().l().openApp(this, stringExtra2);
                    } else {
                        File file = new File(stringExtra);
                        if (!file.exists() || file.length() <= 0) {
                            this.D.i(o, new StringBuilder("\u6587\u4ef6[").append(stringExtra).append("] \u5df2\u7ecf\u88ab\u5220\u9664").toString());
                        } else {
                            startActivity(m.a().l().getInstallIntent(stringExtra));
                        }
                    }
                } else if (intExtra == DownloadStatus.ERROR.getCode() || intExtra == DownloadStatus.PAUSED.getCode()) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 0) {
                        com.baidu.mobads.openad.c.b a = com.baidu.mobads.openad.c.b.a(stringExtra2);
                        if (a != null) {
                            com.baidu.mobads.command.a a2 = a.a();
                            if (a2 != null) {
                                a2.r = true;
                            }
                        }
                    }
                    if (adsApkDownloader != null) {
                        adsApkDownloader.resume();
                    }
                }
            } catch (Throwable e) {
                this.D.d(o, e);
            }
            finish();
            return;
        }
        this.F = intent.getBooleanExtra("canOpenAppForAPO", this.F);
        com.baidu.mobads.j.d m = m.a().m();
        this.B = (XAdLandingPageExtraInfo) getIntent().getParcelableExtra(EXTRA_DATA);
        this.A = new c(this.B);
        Rect windowRect = m.getWindowRect(this);
        this.b = (float) ((((double) windowRect.width()) * 1.0d) / 640.0d);
        this.a = (float) ((((double) windowRect.height()) * 1.0d) / 960.0d);
        requestWindowFeature(1);
        this.t = this.B.title;
        if (this.B.orientation == 1) {
            setRequestedOrientation(1);
        } else {
            setRequestedOrientation(0);
        }
        this.s = System.currentTimeMillis();
        try {
            if (this.B.isFullScreen) {
                getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
            }
        } catch (Exception e2) {
            this.D.d(o, "exception when getIntent");
        }
        this.e = this.B.from;
        stringExtra = this.B.url;
        if (ac.a(stringExtra)) {
            this.D.d(o, "AppActivity.browser external");
            Intent intent2;
            if (ac.i(stringExtra)) {
                intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(Uri.parse(stringExtra), "audio/*");
                startActivity(intent2);
            } else if (ac.h(stringExtra)) {
                intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(Uri.parse(stringExtra), "video/*");
                startActivity(intent2);
            } else {
                m.browserOutside(this, stringExtra);
            }
            finish();
            return;
        }
        this.q = startUrlHandler(this);
        initActionBar();
        b(stringExtra);
        a(this.curWebview, stringExtra, false, new StringBuilder("http://mobads.baidu.com/").append(m.getAppPackage(this)).toString());
        this.d.setBackgroundColor(-1);
        setContentView(this.d);
    }

    private void b(String str) {
        this.d = new RelativeLayout(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, m.a().m().getPixel(this, R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
        layoutParams.addRule(XZBDevice.Stop);
        this.c.setId(ACTIONBAR_VIEW_ID);
        this.d.addView(this.c, layoutParams);
        View a = a(str);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, ACTIONBAR_VIEW_ID);
        this.d.addView(a, layoutParams2);
        if (this.u && canSupportAnimate()) {
            this.d.getViewTreeObserver().addOnPreDrawListener(new p(this));
        }
    }

    protected boolean canSupportAnimate() {
        try {
            return VERSION.SDK_INT >= 12;
        } catch (Throwable e) {
            j.a().e(e);
            return false;
        }
    }

    protected void initActionBar() {
        this.c = new com.baidu.mobads.g.a(this, J);
        this.c.setId(ACTIONBAR_VIEW_ID);
        this.c.a(new q(this));
    }

    private void e() {
        f();
        initBottomView();
        this.d.addView(this.C);
        this.d.addView(this.mBottomView);
        if (canSupportAnimate()) {
            this.mBottomView.getViewTreeObserver().addOnPreDrawListener(new r(this));
        }
    }

    @TargetApi(11)
    private void f() {
        this.C = new View(this);
        this.C.setOnClickListener(new s(this));
        if (canSupportAnimate()) {
            this.C.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.C.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        this.C.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    }

    @TargetApi(11)
    protected void initBottomView() {
        this.mBottomView = new d(this);
        this.mBottomView.a(new t(this));
        if (canSupportAnimate()) {
            this.mBottomView.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(XZBDevice.Fail);
        this.mBottomView.setLayoutParams(layoutParams);
    }

    @TargetApi(11)
    protected void copyCurrentPageUrl() {
        if (VERSION.SDK_INT < 11) {
            Toast.makeText(this, "\u7cfb\u7edf\u7248\u672c\u4e0d\u652f\u6301", 0).show();
            return;
        }
        try {
            if (!TextUtils.isEmpty(this.curWebview.getUrl())) {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("\u7f51\u9875\u94fe\u63a5", this.curWebview.getUrl()));
                Toast.makeText(this, "\u5df2\u590d\u5236\u5230\u526a\u5207\u677f", 0).show();
            }
        } catch (Throwable e) {
            this.D.e(e);
        }
    }

    private void g() {
        if (this.curWebview != null) {
            this.curWebview.reload();
        }
    }

    @TargetApi(12)
    protected void runBottomViewEnterAnimation(View view, View view2) {
        this.mBottomViewIsShowing = true;
        try {
            view.animate().setDuration(500).alpha(0.5f);
            view2.setTranslationY((float) view2.getHeight());
            view2.animate().setDuration(500).alpha(1.0f).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        } catch (Throwable e) {
            this.D.e(e);
        }
    }

    @TargetApi(16)
    protected void runBottomViewExitAnimation(View view, View view2) {
        this.mBottomViewIsShowing = false;
        if (canSupportAnimate()) {
            try {
                view.clearAnimation();
                view.animate().setDuration(250).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
                view2.setTranslationY(AutoScrollHelper.RELATIVE_UNSPECIFIED);
                ViewPropertyAnimator translationY = view2.animate().setDuration(250).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).translationY((float) view2.getHeight());
                if (VERSION.SDK_INT >= 16) {
                    translationY.withEndAction(new u(this, view2, view));
                    return;
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new e(this, view2, view), 250);
                    return;
                }
            } catch (Throwable e) {
                this.D.e(e);
            }
        }
        b(view2);
        b(view);
    }

    @TargetApi(12)
    private void a(View view) {
        try {
            view.animate().setDuration(700);
            view.setTranslationX((float) view.getWidth());
            view.animate().setDuration(700).translationX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        } catch (Throwable e) {
            this.D.e(e);
        }
    }

    @TargetApi(16)
    private void h() {
        if (canSupportAnimate()) {
            try {
                View view = this.d;
                if (this.u) {
                    view.setTranslationX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    ViewPropertyAnimator translationX = view.animate().setDuration(300).translationX((float) view.getWidth());
                    if (VERSION.SDK_INT >= 16) {
                        translationX.withEndAction(new f(this));
                        return;
                    } else {
                        new Handler(Looper.getMainLooper()).postDelayed(new g(this), 300);
                        return;
                    }
                }
                finish();
                return;
            } catch (Throwable e) {
                this.D.e(e);
            }
        }
        finish();
    }

    private void b(View view) {
        if (view != null) {
            try {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
            } catch (Throwable e) {
                this.D.e(e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r4, android.view.KeyEvent r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.AppActivity.onKeyDown(int, android.view.KeyEvent):boolean");
        /*
        this = this;
        r0 = 1;
        r1 = 4;
        if (r4 != r1) goto L_0x0040;
    L_0x0004:
        r1 = r3.mBottomViewIsShowing;	 Catch:{ Exception -> 0x0026 }
        if (r1 == 0) goto L_0x0018;
    L_0x0008:
        r1 = r3.C;	 Catch:{ Exception -> 0x0026 }
        r2 = r3.mBottomView;	 Catch:{ Exception -> 0x0026 }
        r3.runBottomViewExitAnimation(r1, r2);	 Catch:{ Exception -> 0x0026 }
    L_0x000f:
        r1 = r3.A;	 Catch:{ Exception -> 0x0026 }
        r2 = r1.G;	 Catch:{ Exception -> 0x0026 }
        r2 = r2 + 1;
        r1.G = r2;	 Catch:{ Exception -> 0x0026 }
    L_0x0017:
        return r0;
    L_0x0018:
        r1 = r3.curWebview;	 Catch:{ Exception -> 0x0026 }
        r1 = r1.canGoBack();	 Catch:{ Exception -> 0x0026 }
        if (r1 == 0) goto L_0x0037;
    L_0x0020:
        r1 = r3.curWebview;	 Catch:{ Exception -> 0x0026 }
        r1.goBack();	 Catch:{ Exception -> 0x0026 }
        goto L_0x000f;
    L_0x0026:
        r0 = move-exception;
        r1 = r3.D;
        r2 = o;
        r0 = r0.getMessage();
        r1.d(r2, r0);
    L_0x0032:
        r0 = super.onKeyDown(r4, r5);
        goto L_0x0017;
    L_0x0037:
        r1 = "backC";
        r3.k = r1;	 Catch:{ Exception -> 0x0026 }
        r3.h();	 Catch:{ Exception -> 0x0026 }
        goto L_0x000f;
    L_0x0040:
        r1 = 46;
        if (r4 != r1) goto L_0x0032;
    L_0x0044:
        r1 = r3.curWebview;	 Catch:{ Exception -> 0x0026 }
        r1.reload();	 Catch:{ Exception -> 0x0026 }
        goto L_0x0017;
        */
    }

    public void onDestroy() {
        int i = 0;
        super.onDestroy();
        p.set(false);
        if (this.q != null) {
            this.A.n = this.k;
            c cVar = this.A;
            int i2 = this.r;
            this.r = i2 + 1;
            cVar.o = i2;
            this.A.p = this.curWebview != null ? this.curWebview.getContentHeight() : 0;
            cVar = this.A;
            if (this.curWebview != null) {
                i = this.curWebview.getProgress();
            }
            cVar.q = i;
            this.A.s = this.f;
            this.A.t = this.g;
            this.A.u = System.currentTimeMillis() - this.s;
            this.A.v = this.v;
            this.A.w = this.w;
            this.A.x = this.e;
            this.A.y = this.j;
            Message obtain = Message.obtain();
            obtain.what = G;
            obtain.obj = this.A;
            this.q.sendMessage(obtain);
            try {
                this.curWebview.setVisibility(XZBDevice.Wait);
                this.D.d(o, "onDestroy");
                this.curWebview.stopLoading();
                this.curWebview.destroy();
            } catch (Exception e) {
                this.D.d(o, e.getMessage());
            }
        }
        i();
    }

    private void i() {
        if (this.m != null) {
            b(this.m);
            this.m = null;
        }
    }

    private void a(WebView webView, String str, Runnable runnable, Runnable runnable2) {
        try {
            com.baidu.mobads.j.d m = m.a().m();
            if (this.F ? ac.a(str) : ac.b(str)) {
                Intent intent;
                if (ac.i(str)) {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(Uri.parse(str), "audio/*");
                    webView.getContext().startActivity(intent);
                } else if (ac.h(str)) {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(Uri.parse(str), "video/*");
                    webView.getContext().startActivity(intent);
                } else {
                    m.browserOutside(webView.getContext(), str);
                }
                if (runnable != null) {
                    runnable.run();
                }
            } else if (runnable2 != null) {
                runnable2.run();
            }
        } catch (Exception e) {
            this.D.d(o, e.getMessage());
        }
    }

    private void a(WebView webView, String str, boolean z, String str2) {
        if (webView != null) {
            com.baidu.mobads.j.d m = m.a().m();
            if (this.z) {
                this.f++;
                this.A.r.decrementAndGet();
                this.z = false;
            }
            if (z && str2.equals("ignore")) {
                try {
                    this.curWebview.loadUrl(str);
                    return;
                } catch (Exception e) {
                }
            }
            try {
                Map hashMap = new HashMap();
                if (str2.equals("ignore")) {
                    hashMap.put("Referer", new StringBuilder("http://mobads.baidu.com/").append(m.getAppPackage(this)).toString());
                } else {
                    hashMap.put("Referer", str2);
                }
                this.curWebview.getClass().getMethod("loadUrl", new Class[]{String.class, Map.class}).invoke(this.curWebview, new Object[]{str, hashMap});
            } catch (Exception e2) {
                try {
                    this.curWebview.loadUrl(str);
                } catch (Exception e3) {
                    this.D.d(o, e2.getMessage());
                }
            }
        }
    }

    public Handler startUrlHandler(Context context) {
        this.n.start();
        return new h(this, this.n.getLooper());
    }

    public void setPageFinishedListener(PageFinishedListener pageFinishedListener) {
        this.H = pageFinishedListener;
    }

    private int j() {
        try {
            Method method = Context.class.getMethod("getThemeResId", new Class[0]);
            method.setAccessible(true);
            return ((Integer) method.invoke(this, new Object[0])).intValue();
        } catch (Throwable e) {
            j.a().e(e);
            return -1;
        }
    }

    private boolean k() {
        try {
            return 16973840 == j();
        } catch (Throwable e) {
            j.a().e(e);
            return false;
        }
    }

    public void onPause() {
        super.onPause();
        if (this.I) {
            this.I = false;
            return;
        }
        c cVar = this.A;
        cVar.H++;
    }

    public static ActionBarColorTheme getActionBarColorTheme() {
        return J;
    }

    public static void setActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
        if (actionBarColorTheme != null) {
            J = new ActionBarColorTheme(actionBarColorTheme);
        }
    }
}
