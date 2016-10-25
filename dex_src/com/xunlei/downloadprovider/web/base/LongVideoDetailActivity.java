package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.homepage.b;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.base.core.BaseWebViewActivity;
import com.xunlei.downloadprovider.web.base.core.CustomWebView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class LongVideoDetailActivity extends BaseWebViewActivity {
    private View d;
    private ImageView e;
    private TextView f;
    private View g;
    private String h;
    private boolean i;
    private boolean j;
    private int k;
    private b l;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = g.a(this, 187.0f);
        this.l = new b(this);
    }

    protected final void a(Intent intent) {
        super.a(intent);
        this.h = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE);
    }

    protected final void a() {
        super.a();
        setContentView(2130968861);
        this.d = findViewById(2131756159);
        this.e = (ImageView) findViewById(2131756160);
        this.f = (TextView) findViewById(2131756161);
        if (!TextUtils.isEmpty(this.h)) {
            this.f.setText(this.h);
        }
        this.g = findViewById(2131756162);
        this.a = (CustomWebView) findViewById(R.id.webView);
        this.a.setProgressType(XZBDevice.DOWNLOAD_LIST_FAILED);
        this.a.setErrorViewVisibilityListener(new u(this));
        this.a.setProgressVisibilityListener(new v(this));
        this.a.setWebChromeClient(new w(this));
        this.a.setOnScrollChangedListener(new x(this));
    }

    public static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, LongVideoDetailActivity.class);
        intent.putExtra(SocialConstants.PARAM_URL, str2);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str3);
        intent.putExtra("from", str);
        context.startActivity(intent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.l.a(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    static /* synthetic */ void a(LongVideoDetailActivity longVideoDetailActivity, float f) {
        if (f == 0.0f) {
            longVideoDetailActivity.e.setImageResource(2130839158);
        } else {
            longVideoDetailActivity.e.setImageResource(2130839458);
        }
        int i = (int) (255.0f * f);
        longVideoDetailActivity.d.setBackgroundColor(Color.argb(i, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
        longVideoDetailActivity.f.setTextColor(Color.argb(i, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle));
        int color = longVideoDetailActivity.getResources().getColor(2131689799);
        longVideoDetailActivity.g.setBackgroundColor(Color.argb(i, Color.red(color), Color.green(color), Color.blue(color)));
    }
}
