package com.xunlei.downloadprovider.web.base.core;

import com.xunlei.downloadprovider.member.login.LoginHelper.c;

// compiled from: DefaultJsInterface.java
final class k implements c {
    final /* synthetic */ String a;
    final /* synthetic */ DefaultJsInterface b;

    k(DefaultJsInterface defaultJsInterface, String str) {
        this.b = defaultJsInterface;
        this.a = str;
    }

    public final void a(int i) {
        this.b.callbackGetUserInfo(this.a);
    }
}
