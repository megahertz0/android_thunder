package com.xunlei.tdlive;

import android.text.TextUtils;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest$GetUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LiveGiftDialog.java
class j implements ObjectCallBack {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void onResponse(int i, String str, Object obj) {
        Object obj2 = null;
        if (i == 0) {
            XLLiveGetUserInfoRequest$GetUserInfoResp xLLiveGetUserInfoRequest$GetUserInfoResp = (XLLiveGetUserInfoRequest$GetUserInfoResp) obj;
            if (xLLiveGetUserInfoRequest$GetUserInfoResp == null) {
                obj2 = new StringBuilder("\u7528\u6237\u4fe1\u606f\u7ed3\u6784\u9519\u8bef\uff01").append(str).toString();
            } else {
                this.a.a((long) xLLiveGetUserInfoRequest$GetUserInfoResp.data.current_coin);
            }
        } else {
            obj2 = new StringBuilder("\u83b7\u53d6\u91d1\u5e01\u6570\u5931\u8d25\uff0c").append(str).toString();
        }
        if (!TextUtils.isEmpty(obj2)) {
            n.a(this.a.getContext(), obj2);
        }
    }
}
