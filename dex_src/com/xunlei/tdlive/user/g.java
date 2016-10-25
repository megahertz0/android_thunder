package com.xunlei.tdlive.user;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: UserHelper.java
class g implements JsonCallBack {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        XLog.d("UserHelper", new StringBuilder("XLLiveRsyncAccountRequest onResponse ret:").append(i).append(", msg:").append(str).toString());
    }
}
