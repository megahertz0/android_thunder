package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.protocol.XLLiveGetFollowRequest.GetFollowResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;

// compiled from: BaseNormalScreenLayoutPresenter.java
class n implements ObjectCallBack {
    final /* synthetic */ c a;

    n(c cVar) {
        this.a = cVar;
    }

    public void onResponse(int i, String str, Object obj) {
        boolean z = true;
        if (i == 0 && obj != null) {
            GetFollowResp getFollowResp = (GetFollowResp) obj;
            if (!f.a().k().equals(this.a.d.a.b)) {
                boolean z2;
                NormalScreenLayout normalScreenLayout = this.a.a;
                c cVar = this.a;
                if (getFollowResp.data.is_follow != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                cVar.k = z2;
                normalScreenLayout.setAttendSelect(z2);
            }
            c cVar2 = this.a;
            if (getFollowResp.data.is_deniedchat == 0) {
                z = false;
            }
            cVar2.j = z;
        }
    }
}
