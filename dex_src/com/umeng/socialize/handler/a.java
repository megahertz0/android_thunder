package com.umeng.socialize.handler;

import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.utils.Log;

// compiled from: UMAPIShareHandler.java
class a implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ UMAPIShareHandler c;

    a(UMAPIShareHandler uMAPIShareHandler, a aVar, Bundle bundle) {
        this.c = uMAPIShareHandler;
        this.a = aVar;
        this.b = bundle;
    }

    public void run() {
        this.c.sendShareRequest(this.c.getResult(this.a.a, this.b), this.a.b);
        Log.d(SocialConstants.PARAM_ACT, "sent share request");
    }
}
