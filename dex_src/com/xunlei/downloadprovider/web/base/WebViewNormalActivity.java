package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebChromeClient;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.web.base.core.BaseWebViewActivity;
import com.xunlei.downloadprovider.web.base.core.CustomWebView;
import com.xunlei.downloadprovider.web.base.core.WebTitleBar;
import com.xunlei.downloadprovider.web.base.core.s;
import com.xunlei.downloadprovider.web.base.core.t;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;

public class WebViewNormalActivity extends BaseWebViewActivity {
    private WebTitleBar d;
    private WebChromeClient e;

    public WebViewNormalActivity() {
        this.e = new bp(this);
    }

    protected final void a() {
        super.a();
        setContentView(2130968636);
        this.a = (CustomWebView) findViewById(R.id.webView);
        this.a.setWebChromeClient(this.e);
        this.d = (WebTitleBar) findViewById(2131755350);
        WebTitleBar webTitleBar = this.d;
        CustomWebView customWebView = this.a;
        webTitleBar.a = customWebView;
        customWebView.setWebViewClient(new s(webTitleBar));
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE);
            if (!t.a(stringExtra)) {
                this.d.setTitleText(stringExtra);
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, WebViewNormalActivity.class);
        intent.putExtra("from", str);
        intent.putExtra(SocialConstants.PARAM_URL, str2);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str3);
        context.startActivity(intent);
    }
}
