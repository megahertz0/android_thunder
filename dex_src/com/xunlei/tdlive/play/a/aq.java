package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.play.a.c.a;
import com.xunlei.tdlive.protocol.XLLiveReplayRequest.ReplayResp;
import com.xunlei.tdlive.protocol.XLLiveReplayRequest.ReplayResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: ReplayNormalScreenLayoutPresenter.java
class aq implements ObjectCallBack {
    final /* synthetic */ ap a;

    aq(ap apVar) {
        this.a = apVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && obj != null) {
            ReplayResp replayResp = (ReplayResp) obj;
            a aVar = new a();
            aVar.c = 0;
            aVar.d = replayResp.data.playinfo.total_point;
            aVar.e = replayResp.data.current_user;
            aVar.a.c = replayResp.data.userinfo.avatar;
            aVar.a.a = replayResp.data.userinfo.nickname;
            aVar.a.e = replayResp.data.userinfo.level;
            aVar.a.d = BuildConfig.VERSION_NAME;
            aVar.a.b = replayResp.data.userinfo.userid;
            aVar.f = BuildConfig.VERSION_NAME;
            this.a.d = aVar;
            this.a.g();
            List arrayList = new ArrayList();
            Iterator it = replayResp.data.userlist.iterator();
            while (it.hasNext()) {
                UserInfo userInfo = (UserInfo) it.next();
                d dVar = new d();
                dVar.a = userInfo.userid;
                dVar.c = userInfo.avatar;
                dVar.b = userInfo.nickname;
                dVar.d = userInfo.sign;
                dVar.e = userInfo.level;
                arrayList.add(dVar);
            }
            this.a.a(arrayList, aVar.e);
            this.a.i();
            com.xunlei.tdlive.util.a.a(this.a.d()).a(this.a.a.getConnectMicView().getHeadImageView(), aVar.a.c, com.xunlei.tdlive.util.a.a(this.a.d(), R.drawable.xllive_avatar_default));
        }
    }
}
