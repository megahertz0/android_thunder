package com.xunlei.downloadprovider.web.core;

import android.text.TextUtils;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: DefaultJsCallbackListener.java
final class d implements p {
    final /* synthetic */ String a;
    final /* synthetic */ c b;

    d(c cVar, String str) {
        this.b = cVar;
        this.a = str;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (!(c.a(this.b) == null || TextUtils.isEmpty(this.a))) {
            c.a(this.b).a(new StringBuilder("javascript:").append(this.a).append("()").toString());
        }
        LoginHelper.a().b(this);
    }
}
