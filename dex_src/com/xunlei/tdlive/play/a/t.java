package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.play.a.c.a;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest$GetRoomInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: NormalScreenLayoutPresenter.java
class t implements ObjectCallBack {
    final /* synthetic */ q a;

    t(q qVar) {
        this.a = qVar;
    }

    public void onResponse(int i, String str, Object obj) {
        XLog.d(q.v, new StringBuilder("getroominfo:").append(i).append(", ").append(str).toString());
        if (i == 0 && obj != null) {
            XLLiveGetRoomInfoRequest$GetRoomInfoResp xLLiveGetRoomInfoRequest$GetRoomInfoResp = (XLLiveGetRoomInfoRequest$GetRoomInfoResp) obj;
            if (xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.status != 2) {
                a aVar = new a();
                aVar.a.c = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.userinfo.avatar;
                aVar.a.a = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.userinfo.nickname;
                aVar.a.d = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.userinfo.sign;
                aVar.a.b = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.userid;
                aVar.a.e = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.userinfo.level;
                aVar.d = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.playerinfo.total_point;
                aVar.c = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.status;
                aVar.e = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.onlinenum;
                aVar.b = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roomuser;
                aVar.f = xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roominfo.start_time;
                aVar.g = xLLiveGetRoomInfoRequest$GetRoomInfoResp.date;
                this.a.d = aVar;
                this.a.g();
                this.a.a(q.b(xLLiveGetRoomInfoRequest$GetRoomInfoResp.data.roomuser), aVar.e);
                if (!this.a.u) {
                    this.a.i();
                }
                if (this.a.b != null) {
                    this.a.b.a(this.a.f().c);
                }
            } else if (this.a.b != null && !this.a.u) {
                this.a.b.a(true);
            }
        }
    }
}
