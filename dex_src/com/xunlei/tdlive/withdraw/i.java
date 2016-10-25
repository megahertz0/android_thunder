package com.xunlei.tdlive.withdraw;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: VerifyCodeCheckPage.java
class i implements JsonCallBack {
    final /* synthetic */ f a;

    i(f fVar) {
        this.a = fVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (this.a.c()) {
            if (i == 0) {
                this.a.k.a(b.class, false);
            } else {
                f.a(this.a, str);
            }
            f.a(this.a, false);
        }
    }
}
