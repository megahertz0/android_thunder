package com.xunlei.tdlive.withdraw;

import com.xunlei.tdlive.protocol.XLLiveGetWithdrawInfoRequest.WidthdrawInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: MyInComeHomePage.java
class e implements ObjectCallBack {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (!this.a.c()) {
            return;
        }
        if (i != 0) {
            this.a.a_(str);
        } else if (obj instanceof WidthdrawInfo) {
            WidthdrawInfo widthdrawInfo = (WidthdrawInfo) obj;
            if (widthdrawInfo.data == null || !widthdrawInfo.data.allowed()) {
                this.a.a_(widthdrawInfo.data.error);
            } else {
                this.a.e();
            }
        }
    }
}
