package com.xunlei.downloadprovider.member.login;

import com.xunlei.downloadprovider.member.login.LoginHelper.b;
import java.util.Iterator;

// compiled from: LoginHelper.java
final class g implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ b b;
    final /* synthetic */ LoginHelper c;

    g(LoginHelper loginHelper, int i, b bVar) {
        this.c = loginHelper;
        this.a = i;
        this.b = bVar;
    }

    public final void run() {
        Iterator it = LoginHelper.d(this.c).iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(this.a, this.b);
        }
    }
}
