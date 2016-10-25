package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LivePublishEndingActivity.java
class db implements ObjectCallBack {
    final /* synthetic */ b a;

    db(b bVar) {
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
