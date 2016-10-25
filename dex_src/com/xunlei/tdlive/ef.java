package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest$GetUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.q;

// compiled from: SDKLiveListFragment.java
class ef implements ObjectCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ ea b;

    ef(ea eaVar, String str) {
        this.b = eaVar;
        this.a = str;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && obj != null) {
            XLLiveGetUserInfoRequest$GetUserInfoResp xLLiveGetUserInfoRequest$GetUserInfoResp = (XLLiveGetUserInfoRequest$GetUserInfoResp) obj;
            int c = this.b.c(this.a + "_level", -1);
            if (c == -1) {
                this.b.b(this.a + "_level", xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current);
            } else if (xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current > c) {
                this.b.b(this.a + "_level", xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current);
                new er(this.b.getActivity(), xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current, xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.getIconFullPath()).show();
                q.e("level_altert_pop").a("level", xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current).b(new String[0]);
            }
        }
    }
}
