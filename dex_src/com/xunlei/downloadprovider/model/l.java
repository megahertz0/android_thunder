package com.xunlei.downloadprovider.model;

import android.os.Handler;
import android.os.Message;
import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SignInUtil.java
public final class l implements a {
    final /* synthetic */ Handler a;

    public l(Handler handler) {
        this.a = handler;
    }

    public final void onErrorResponse(w wVar) {
        Message message = new Message();
        message.setTarget(this.a);
        message.what = 10000;
        message.sendToTarget();
    }
}
