package com.xunlei.tdlive.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: LiveListBannerAdapter.java
class o implements JsonCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ n b;

    o(n nVar, String str) {
        this.b = nVar;
        this.a = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            this.b.a(jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]"));
        }
        if (this.b.a != null) {
            this.b.a.a(this.a, true, false);
        }
        this.b.c();
    }
}
