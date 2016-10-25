package com.xunlei.downloadprovider.download.tasklist.list.d;

import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class j implements Runnable {
    final /* synthetic */ b a;

    j(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        this.a.e.setVisibility(0);
        b bVar = this.a;
        LoginHelper.a();
        bVar.A = LoginHelper.c();
        this.a.m = "inTrial";
        this.a.B.a(true);
    }
}
