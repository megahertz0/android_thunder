package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: BaseNormalScreenLayoutPresenter.java
class e implements ObjectCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;

    e(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            c.a(this.b, this.a);
        }
    }
}
