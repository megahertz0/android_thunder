package com.xunlei.tdlive.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: RankListAdapter.java
class t implements JsonCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ s b;

    t(s sVar, String str) {
        this.b = sVar;
        this.a = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            this.b.a(jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]"));
        }
        if (this.b.a != null) {
            this.b.a.a(this.a, true, false);
        }
        this.b.c();
    }
}
