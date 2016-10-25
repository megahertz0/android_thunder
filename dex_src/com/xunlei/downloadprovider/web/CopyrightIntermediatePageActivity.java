package com.xunlei.downloadprovider.web;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.vod.ap;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.downloadprovider.web.core.a;
import com.xunlei.downloadprovider.web.core.g;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.List;

public class CopyrightIntermediatePageActivity extends ThunderTask {
    private static final String a;
    private final int b;
    private String c;
    private String d;
    private WebView e;
    private ThunderWebView f;
    private UnifiedLoadingView g;
    private ImageView h;
    private ProgressBar i;
    private EditText j;
    private a k;
    private View l;
    private View m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private final h.a q;
    private final Handler r;
    private final OnClickListener s;
    private final WebViewClient t;
    private final WebChromeClient u;

    public CopyrightIntermediatePageActivity() {
        this.b = 3000;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = new a(this);
        this.r = new b(this.q);
        this.s = new b(this);
        this.t = new c(this);
        this.u = new d(this);
    }

    static {
        a = CopyrightIntermediatePageActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968707);
        this.e = (WebView) findViewById(2131755684);
        this.g = (UnifiedLoadingView) findViewById(2131755687);
        this.h = (ImageView) findViewById(2131755680);
        this.i = (ProgressBar) findViewById(2131755683);
        this.j = (EditText) findViewById(2131755681);
        this.n = (ImageView) findViewById(2131755656);
        this.m = findViewById(2131755659);
        this.l = findViewById(2131755686);
        this.o = (TextView) findViewById(2131755657);
        this.p = (TextView) findViewById(2131755658);
        this.k = new a(this);
        this.h.setOnClickListener(this.s);
        this.m.setOnClickListener(this.s);
        this.l.setVisibility(XZBDevice.Wait);
        this.g.b();
        this.f = (ThunderWebView) findViewById(2131755685);
        LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
        layoutParams.width = com.xunlei.downloadprovider.a.b.t();
        layoutParams.height = (int) (((float) layoutParams.width) / 3.5294118f);
        this.f.setLayoutParams(layoutParams);
        this.f.setJsCallbackMessageListener(new e(this));
        this.f.setThunderWebViewClient(new f(this));
        if (com.xunlei.downloadprovider.a.b.i() > 10) {
            this.e.setLayerType(0, null);
        }
        this.e.setWebViewClient(this.t);
        this.e.setWebChromeClient(this.u);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(-1);
        settings.setLoadWithOverviewMode(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        this.e.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("intent_key_opt_type", -1);
            BrowserUtil.a();
            this.c = BrowserUtil.a(intent.getStringExtra("intent_key_source_url"));
            this.j.setText(this.c);
            this.e.loadUrl(this.c);
            this.d = intent.getStringExtra("intent_key_popup_url");
            if (TextUtils.isEmpty(this.d)) {
                this.f.setVisibility(XZBDevice.Wait);
                switch (intExtra) {
                    case 901:
                        Serializable serializableExtra = intent.getSerializableExtra("intent_key_vod_params");
                        this.g.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        this.g.a();
                        if (serializableExtra instanceof ap) {
                            ap apVar = (ap) serializableExtra;
                            Message obtainMessage = this.r.obtainMessage();
                            obtainMessage.what = 901;
                            obtainMessage.obj = apVar;
                            this.r.sendMessageDelayed(obtainMessage, TabsImpl.SYNC_TIME_OUT);
                            return;
                        }
                        a();
                        return;
                    case 902:
                        a(g.b(intent.getStringExtra("intent_key_add_tasks_json")));
                        return;
                    default:
                        a();
                        return;
                }
            }
            this.f.a(this.d);
            return;
        }
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        if (this.e != null) {
            this.e.onPause();
        }
        super.onPause();
    }

    protected void onDestroy() {
        if (this.e != null) {
            this.e.setWebViewClient(null);
            this.e.setWebChromeClient(null);
            this.e.clearView();
            this.e.clearHistory();
            this.e.clearCache(false);
            this.e.destroy();
            this.e = null;
        }
        this.r.removeMessages(902);
        this.r.removeMessages(901);
        this.r.removeMessages(R.styleable.AppCompatTheme_buttonStyle);
        this.r.removeMessages(R.styleable.AppCompatTheme_buttonStyleSmall);
        super.onDestroy();
        System.gc();
    }

    public void finish() {
        super.finish();
        com.xunlei.downloadprovider.commonview.a.a.d(this);
    }

    public void onBackPressed() {
        finish();
    }

    protected void onCreateTask(boolean z, int i) {
    }

    private final void a(List<DownData> list) {
        this.g.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.g.a();
        if (list == null || list.size() <= 0) {
            a();
            return;
        }
        Message obtainMessage = this.r.obtainMessage();
        obtainMessage.what = 902;
        obtainMessage.obj = list;
        this.r.sendMessageDelayed(obtainMessage, TabsImpl.SYNC_TIME_OUT);
    }

    private final void a() {
        this.g.b();
        XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, getString(2131231122));
    }
}
