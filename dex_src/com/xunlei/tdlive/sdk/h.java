package com.xunlei.tdlive.sdk;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: XLLiveSDK.java
class h implements JsonCallBack {
    final /* synthetic */ XLLiveSDK a;

    h(XLLiveSDK xLLiveSDK) {
        this.a = xLLiveSDK;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        XLog.d("XLLiveSDK", new StringBuilder("managePushTag ret: ").append(i).append(", msg: ").append(str).toString());
        if (i == 0) {
            XLLiveSDK.b(this.a, true);
        }
    }
}
