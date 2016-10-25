package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.BannerInfo;
import com.xunlei.tdlive.protocol.GetPayPageBannerInfoRequest.RespData;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: RechargeActivity.java
class du implements ObjectCallBack {
    final /* synthetic */ RechargeActivity a;

    du(RechargeActivity rechargeActivity) {
        this.a = rechargeActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && (obj instanceof RespData)) {
            RespData respData = (RespData) obj;
            if (respData.data == null || respData.data.size() <= 0) {
                this.a.a(null);
            } else {
                this.a.a((BannerInfo) respData.data.get(0));
            }
        }
    }
}
