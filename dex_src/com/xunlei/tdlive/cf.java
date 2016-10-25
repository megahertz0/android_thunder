package com.xunlei.tdlive;

import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveSetPublishStateRequest.SetPublishStateResp;

// compiled from: LivePlayerDialog.java
class cf implements ObjectCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ au b;

    cf(au auVar, boolean z) {
        this.b = auVar;
        this.a = z;
    }

    public void onResponse(int i, String str, Object obj) {
        boolean z;
        SetPublishStateResp setPublishStateResp = (SetPublishStateResp) obj;
        if (i != 0 || setPublishStateResp == null || setPublishStateResp.data == null || setPublishStateResp.data.status != 1) {
            if (!this.a) {
                z = true;
            } else if (au.e(this.b) >= e.n) {
                z = true;
            } else {
                au.f(this.b).postDelayed(new cg(this), 60000);
                z = false;
            }
        } else if (setPublishStateResp.data.status == 2) {
            z = true;
        } else {
            au.a(this.b, 0);
            if (this.a) {
                au.f(this.b).postDelayed(new ch(this), 60000);
            }
            z = false;
        }
        if (z) {
            au.d(this.b, true);
            au.a(this.b, new StringBuilder("\u6062\u590d\u76f4\u64ad\u5931\u8d25\uff0c\u9519\u8bef\u7801\uff1a").append(i).toString());
            if (!au.b(this.b, false)) {
                this.b.onBackPressed();
            }
        }
    }
}
