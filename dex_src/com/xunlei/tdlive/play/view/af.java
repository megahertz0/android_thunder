package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.text.TextUtils;
import com.xunlei.tdlive.util.h;
import com.xunlei.tdlive.util.y.a;

// compiled from: ShareWindowHelper.java
class af implements a {
    final /* synthetic */ String a;
    final /* synthetic */ Activity b;
    final /* synthetic */ String c;
    final /* synthetic */ ae d;

    af(ae aeVar, String str, Activity activity, String str2) {
        this.d = aeVar;
        this.a = str;
        this.b = activity;
        this.c = str2;
    }

    public void a(int i, String str) {
        if (i == 0 && !TextUtils.isEmpty(this.a)) {
            h.a(this.b, this.c, this.a, ae.a(this.d));
        }
    }
}
