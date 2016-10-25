package com.alipay.sdk.auth;

import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;

final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AuthActivity b;

    c(AuthActivity authActivity, String str) {
        this.b = authActivity;
        this.a = str;
    }

    public final void run() {
        try {
            AuthActivity.h(this.b).loadUrl(new StringBuilder(BaseJsInterface.JS_PREFIX).append(this.a).toString());
        } catch (Exception e) {
        }
    }
}
