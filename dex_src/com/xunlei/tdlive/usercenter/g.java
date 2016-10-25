package com.xunlei.tdlive.usercenter;

import com.xunlei.tdlive.protocol.XLLiveFollowRequest$FollowResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: FollowBtnOnClickListener.java
class g implements ObjectCallBack {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public void onResponse(int i, String str, Object obj) {
        boolean z = false;
        if (i == 0 && (obj instanceof XLLiveFollowRequest$FollowResp)) {
            z = ((XLLiveFollowRequest$FollowResp) obj).isFollow();
        } else if (i == -1004) {
            z = true;
        } else if (i == -1005) {
        }
        e.a(this.a, z);
        if (e.b(this.a) != null) {
            e.b(this.a).a(z);
        }
    }
}
