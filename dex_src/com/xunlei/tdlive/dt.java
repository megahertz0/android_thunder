package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.GetUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: RechargeActivity.java
class dt implements ObjectCallBack {
    final /* synthetic */ RechargeActivity a;

    dt(RechargeActivity rechargeActivity) {
        this.a = rechargeActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && (obj instanceof GetUserInfoResp)) {
            GetUserInfoResp getUserInfoResp = (GetUserInfoResp) obj;
            if (getUserInfoResp.data != null) {
                this.a.i.setText(String.valueOf(getUserInfoResp.data.current_coin));
            }
        }
    }
}
