package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LivePlayEndingActivity.java
class aa implements ObjectCallBack {
    final /* synthetic */ b a;

    aa(b bVar) {
        this.a = bVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && (obj instanceof GetLiveListResp)) {
            GetLiveListResp getLiveListResp = (GetLiveListResp) obj;
            this.a.b = getLiveListResp.grayid;
            this.a.a = getLiveListResp.data;
            if (this.a.c != null) {
                this.a.c.a(this.a.b, this.a.a);
            }
        }
    }
}
