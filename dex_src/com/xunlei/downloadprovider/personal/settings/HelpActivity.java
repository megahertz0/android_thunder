package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

public class HelpActivity extends BaseActivity implements OnClickListener {
    private static final String[] a;
    private View b;
    private TextView c;
    private ImageView d;
    private WebView e;
    private ImageView f;
    private ImageView g;
    private int h;
    private String i;
    private String j;
    private View k;
    private TextView l;
    private Button m;

    public HelpActivity() {
        this.h = 4;
        this.i = null;
        this.j = null;
    }

    static {
        a = new String[]{"file:///android_asset/help/sl_help.html", "file:///android_asset/help/level2.html", "file:///android_asset/help/code.html", "file:///android_asset/help/pai.html", "file:///android_asset/help/sl_help.html"};
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, HelpActivity.class);
        intent.putExtra("url_idx", str);
        if (context instanceof Activity) {
            context.startActivity(intent);
            return;
        }
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, HelpActivity.class);
        intent.putExtra("url_idx", str);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str2);
        if (context instanceof Activity) {
            context.startActivity(intent);
            return;
        }
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = getIntent().getStringExtra("url_idx");
        this.j = getIntent().getStringExtra(WebBrowserActivity.EXTRA_TITLE);
        setContentView(2130968967);
        b();
    }

    public void onResume() {
        super.onResume();
        if (this.i.equals("file:///android_asset/cloudlist/choudlist_guide_help.html")) {
            LoginHelper.a();
            if (!LoginHelper.c()) {
                finish();
            }
        }
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        a();
        return false;
    }

    private void a() {
        if (c()) {
            a(this.e);
        } else {
            finish();
        }
    }

    private void b() {
        boolean z;
        new f((Activity) this).j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.b = findViewById(R.id.titlebar_left);
        this.b.setOnClickListener(this);
        this.c = (TextView) findViewById(R.id.titlebar_title);
        this.e = (WebView) findViewById(2131756897);
        if (this.i == null || !this.i.startsWith("file:")) {
            z = false;
        } else {
            z = true;
        }
        if (z || b.a(this)) {
            this.d = (ImageView) findViewById(2131756900);
            this.f = (ImageView) findViewById(2131756901);
            this.g = (ImageView) findViewById(2131756902);
            if (this.i != null) {
                findViewById(2131756898).setVisibility(XZBDevice.Wait);
            } else {
                this.d.setOnClickListener(this);
                this.f.setOnClickListener(this);
                this.d.setEnabled(false);
                this.f.setEnabled(false);
                this.g.setOnClickListener(this);
            }
            if (TextUtils.isEmpty(this.j)) {
                this.c.setText("\u5e38\u89c1\u95ee\u9898");
            } else {
                this.c.setText(this.j);
            }
            this.e.setWebViewClient(new q(this));
            this.e.setWebChromeClient(new r(this));
            WebSettings settings = this.e.getSettings();
            settings.setJavaScriptEnabled(true);
            this.e.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
            settings.setJavaScriptEnabled(true);
            settings.setSupportZoom(false);
            settings.setBuiltInZoomControls(false);
            settings.setUseWideViewPort(false);
            settings.setLoadWithOverviewMode(true);
            settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
            this.e.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
            this.e.setBackgroundColor(0);
            this.e.setBackgroundColor(0);
            String str = "file:///android_asset/help/sl_help.html";
            if (this.i != null) {
                str = this.i;
            } else if (4 == this.h && !com.xunlei.downloadprovider.businessutil.b.a().a.getSharedPreferences("settingstate", 0).getBoolean("isClicked2131756897", false)) {
                this.h = 0;
                str = a[this.h];
            }
            if (str != null) {
                try {
                    this.e.loadUrl(str);
                } catch (Exception e) {
                    new StringBuilder("loadWeb error msg=").append(e.getMessage());
                }
            }
            this.k = findViewById(2131756903);
            this.k.setVisibility(XZBDevice.Wait);
            return;
        }
        this.c.setText(this.j);
        this.k = findViewById(2131756903);
        this.k.setVisibility(0);
        this.l = (TextView) findViewById(2131755476);
        this.l.setVisibility(0);
        this.m = (Button) findViewById(2131755477);
        this.m.setOnClickListener(new p(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_left:
                a();
            case 2131756900:
                c();
            case 2131756901:
                if (this.e.canGoForward()) {
                    this.e.goForward();
                }
            case 2131756902:
                try {
                    this.e.reload();
                } catch (Exception e) {
                    new StringBuilder("refresh error msg=").append(e.getMessage());
                }
            default:
                break;
        }
    }

    private boolean c() {
        if (!this.e.canGoBack()) {
            return false;
        }
        this.e.goBack();
        return true;
    }

    private void a(WebView webView) {
        if (webView.canGoBack()) {
            this.d.setEnabled(true);
        } else {
            this.d.setEnabled(false);
        }
        if (webView.canGoForward()) {
            this.f.setEnabled(true);
        } else {
            this.f.setEnabled(false);
        }
    }
}
