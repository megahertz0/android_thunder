package com.xunlei.downloadprovider.member.payment.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.R;

// compiled from: ActivationBox.java
final class c implements a {
    final /* synthetic */ Context a;
    final /* synthetic */ Handler b;
    final /* synthetic */ a c;

    c(a aVar, Context context, Handler handler) {
        this.c = aVar;
        this.a = context;
        this.b = handler;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("onFailure ").append(wVar.getMessage());
        this.c.a = this.a.getString(R.string.activation_fail);
        Message obtainMessage = this.b.obtainMessage(906);
        obtainMessage.obj = this.c.a;
        obtainMessage.sendToTarget();
        wVar.printStackTrace();
    }
}
