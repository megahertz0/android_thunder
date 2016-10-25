package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.k;
import com.tencent.open.SocialConstants;
import com.umeng.a;

public class H5PayActivity extends Activity {
    private WebView a;
    private WebViewClient b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString(SocialConstants.PARAM_URL);
            if (k.b(string)) {
                String string2 = extras.getString("cookie");
                super.requestWindowFeature(1);
                this.a = k.a((Activity) this, string, string2);
                this.b = new b(this);
                this.a.setWebViewClient(this.b);
                return;
            }
            finish();
        } catch (Exception e) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.a.canGoBack()) {
            h.a = h.a();
            finish();
        } else if (((b) this.b).a) {
            i a = i.a(i.d.h);
            h.a = h.a(a.h, a.i, a.d);
            finish();
        }
    }

    public void finish() {
        a();
        super.finish();
    }

    public void a() {
        Object obj = PayTask.a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.removeAllViews();
            try {
                this.a.destroy();
            } catch (Throwable th) {
            }
            this.a = null;
        }
    }
}
