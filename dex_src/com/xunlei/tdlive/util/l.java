package com.xunlei.tdlive.util;

import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: RequestHelper.java
class l implements ObjectCallBack {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            n.a(this.a.a.a, "\u4e3e\u62a5\u6210\u529f");
        } else {
            n.a(this.a.a.a, new StringBuilder("\u4e3e\u62a5\u5931\u8d25\uff0c").append(str).toString());
        }
    }
}
