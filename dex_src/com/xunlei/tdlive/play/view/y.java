package com.xunlei.tdlive.play.view;

import android.content.Context;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.play.view.u.a;
import com.xunlei.tdlive.protocol.XLLiveGetPlayerInfoRequest.GetPlayerInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: PlayerInfoWindowHelper.java
class y implements ObjectCallBack {
    final /* synthetic */ a a;
    final /* synthetic */ u b;

    y(u uVar, a aVar) {
        this.b = uVar;
        this.a = aVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            GetPlayerInfoResp getPlayerInfoResp = (GetPlayerInfoResp) obj;
            this.a.e = getPlayerInfoResp.data.userinfo.follow_num;
            this.a.f = getPlayerInfoResp.data.userinfo.fans_num;
            this.a.g = getPlayerInfoResp.data.current_point;
            this.a.d = getPlayerInfoResp.data.userinfo.avatar;
            this.a.j = getPlayerInfoResp.data.server_time;
            this.b.b(false);
            return;
        }
        Context context = (Context) this.b.b.get();
        if (context != null) {
            n.a(context, new StringBuilder("\u83b7\u53d6\u4e3b\u64ad\u4fe1\u606f\u5931\u8d25\uff0c").append(str).toString());
        }
    }
}
