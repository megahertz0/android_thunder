package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest$GetUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: NormalScreenLayoutPresenter.java
class r implements ObjectCallBack {
    final /* synthetic */ q a;

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            XLLiveGetUserInfoRequest$GetUserInfoResp xLLiveGetUserInfoRequest$GetUserInfoResp = (XLLiveGetUserInfoRequest$GetUserInfoResp) obj;
            if (xLLiveGetUserInfoRequest$GetUserInfoResp != null && xLLiveGetUserInfoRequest$GetUserInfoResp.data != null && xLLiveGetUserInfoRequest$GetUserInfoResp.data.voiceCallable()) {
                this.a.p();
            }
        }
    }
}
