package com.xunlei.tdlive;

import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LivePublishEndingActivity.java
class cx implements ObjectCallBack {
    final /* synthetic */ cw a;

    cx(cw cwVar) {
        this.a = cwVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 || i == -1) {
            n.a(this.a.a, "\u5df2\u6210\u529f\u5173\u6ce8\u4e3b\u64ad^^");
        } else {
            n.a(this.a.a, str);
        }
    }
}
