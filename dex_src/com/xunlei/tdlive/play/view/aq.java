package com.xunlei.tdlive.play.view;

import android.content.Context;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.play.view.ah.a;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserInfoWindowHelper.java
class aq implements ObjectCallBack {
    final /* synthetic */ ah a;

    aq(ah ahVar) {
        this.a = ahVar;
    }

    public void onResponse(int i, String str, Object obj) {
        boolean z = true;
        if (i == 0) {
            GetOtherUserInfoResp getOtherUserInfoResp = (GetOtherUserInfoResp) obj;
            a aVar = (a) this.a.e();
            aVar.a = getOtherUserInfoResp.data.user_info.avatar;
            aVar.b = getOtherUserInfoResp.data.user_info.nickname;
            aVar.e = getOtherUserInfoResp.data.user_info.sign;
            aVar.f = getOtherUserInfoResp.data.user_info.userid;
            aVar.o = getOtherUserInfoResp.data.user_info.uuid;
            aVar.p = (long) getOtherUserInfoResp.data.user_info.day_coin;
            aVar.g = getOtherUserInfoResp.data.is_follow != 0;
            aVar.c = getOtherUserInfoResp.data.used_coin;
            aVar.a = getOtherUserInfoResp.data.user_info.avatar;
            aVar.n = getOtherUserInfoResp.data.user_info.level;
            aVar.d = (long) getOtherUserInfoResp.data.user_info.fans_num;
            if (getOtherUserInfoResp.data.is_deniedchat == 0) {
                z = false;
            }
            aVar.k = z;
            this.a.a(false);
            return;
        }
        Context context = (Context) this.a.b.get();
        if (context != null) {
            n.a(context, new StringBuilder("\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5931\u8d25\uff0c").append(str).toString());
        }
    }
}
