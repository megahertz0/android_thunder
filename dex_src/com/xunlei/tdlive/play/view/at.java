package com.xunlei.tdlive.play.view;

import android.content.Context;
import com.xunlei.tdlive.protocol.XLLiveUnBanRequest;
import com.xunlei.tdlive.user.f;

// compiled from: UserInfoWindowHelper.java
class at implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;
    final /* synthetic */ ah d;

    at(ah ahVar, String str, String str2, Context context) {
        this.d = ahVar;
        this.a = str;
        this.b = str2;
        this.c = context;
    }

    public void run() {
        new XLLiveUnBanRequest(f.a().k(), f.a().l(), this.a, this.b).send(new au(this));
    }
}
