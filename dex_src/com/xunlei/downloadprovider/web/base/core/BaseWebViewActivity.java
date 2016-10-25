package com.xunlei.downloadprovider.web.base.core;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.task.ThunderTask;

public abstract class BaseWebViewActivity extends ThunderTask {
    public CustomWebView a;
    protected String b;
    protected String c;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
        a();
        if (!TextUtils.isEmpty(this.b)) {
            String str = this.b;
            if (!TextUtils.isEmpty(str)) {
                StringBuilder stringBuilder = new StringBuilder(str);
                if (str.contains("?")) {
                    stringBuilder.append('&');
                } else {
                    stringBuilder.append('?');
                }
                stringBuilder.append("_time_=");
                stringBuilder.append(System.currentTimeMillis() / 600000);
                str = stringBuilder.toString();
            }
            this.b = str;
            this.a.a(this.b);
        }
        this.a.setFrom(this.c);
    }

    public void a(Intent intent) {
        this.b = intent.getStringExtra(SocialConstants.PARAM_URL);
        this.c = intent.getStringExtra("from");
    }

    public void a() {
    }

    protected void onResume() {
        super.onResume();
        CustomWebView customWebView = this.a;
        if (customWebView.a != null) {
            customWebView.a.onResume();
        }
    }

    protected void onPause() {
        CustomWebView customWebView = this.a;
        if (customWebView.a != null) {
            customWebView.a.onPause();
        }
        super.onPause();
    }

    protected void onDestroy() {
        this.a.e();
        super.onDestroy();
    }

    public void onClickGoback(View view) {
        finish();
    }

    public void onBackPressed() {
        if (this.a != null) {
            CustomWebView customWebView = this.a;
            if (customWebView.a != null) {
                customWebView.a.stopLoading();
            }
        }
        if (this.a.f()) {
            this.a.g();
        } else {
            super.onBackPressed();
        }
    }
}
