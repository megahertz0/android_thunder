package com.xunlei.tdlive.sdk;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: XLLiveSDK.java
class g implements JsonCallBack {
    final /* synthetic */ HttpRequestCallback a;
    final /* synthetic */ XLLiveSDK b;

    g(XLLiveSDK xLLiveSDK, HttpRequestCallback httpRequestCallback) {
        this.b = xLLiveSDK;
        this.a = httpRequestCallback;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i != 0 || jsonWrapper == null) {
            new StringBuilder().append(i).append(str);
            return;
        }
        JsonWrapper object = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
        if (object != null && this.a != null) {
            this.a.onResponse(i, str, object.toString());
        }
    }
}
