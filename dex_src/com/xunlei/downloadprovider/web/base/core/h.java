package com.xunlei.downloadprovider.web.base.core;

// compiled from: DefaultJsInterface.java
final class h implements com.xunlei.downloadprovider.member.login.LoginHelper.h {
    final /* synthetic */ String a;
    final /* synthetic */ DefaultJsInterface b;

    h(DefaultJsInterface defaultJsInterface, String str) {
        this.b = defaultJsInterface;
        this.a = str;
    }

    public final void a(int i) {
        this.b.callback(this.a, null);
    }
}
