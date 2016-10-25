package com.xunlei.downloadprovider.web;

import com.xunlei.downloadprovider.member.login.LoginHelper.h;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;

// compiled from: DetailPageBrowserActivity.java
final class q implements h {
    final /* synthetic */ String a;
    final /* synthetic */ DetailPageBrowserActivity b;

    q(DetailPageBrowserActivity detailPageBrowserActivity, String str) {
        this.b = detailPageBrowserActivity;
        this.a = str;
    }

    public final void a(int i) {
        if (i == 0) {
            this.b.d.a(new StringBuffer().append(BaseJsInterface.JS_PREFIX).append(this.a + "()").toString());
        }
    }
}
