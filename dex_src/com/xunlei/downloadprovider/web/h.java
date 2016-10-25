package com.xunlei.downloadprovider.web;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;

// compiled from: DetailPageBrowserActivity.java
final class h implements c {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void a(int i) {
        if (i == 0) {
            this.a.a.d.a(String.format("javascript:loginCallBack('{\"userId\":\"%s\"}')", new Object[]{String.valueOf(LoginHelper.a().j)}));
            this.a.a.d.b();
        }
    }
}
