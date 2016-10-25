package com.xunlei.tdlive.play.a;

import com.umeng.a;
import com.xunlei.tdlive.LivePublishEndingActivity;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: ReplayActivityPresenter.java
class z implements ObjectCallBack {
    final /* synthetic */ v a;

    z(v vVar) {
        this.a = vVar;
    }

    public void onResponse(int i, String str, Object obj) {
        int i2;
        int i3;
        int i4 = 0;
        this.a.a.hideLoadingDialog();
        GetRoomInfoResp getRoomInfoResp = (GetRoomInfoResp) obj;
        String str2 = a.d;
        String str3 = a.d;
        String str4 = a.d;
        if (i != 0 || getRoomInfoResp == null || getRoomInfoResp.data == null || getRoomInfoResp.data.roominfo == null) {
            i2 = 0;
            i3 = 0;
        } else {
            str3 = getRoomInfoResp.data.roominfo.userinfo.nickname;
            str4 = getRoomInfoResp.data.roominfo.userinfo.avatar;
            str2 = getRoomInfoResp.data.roominfo.userid;
            i3 = (int) getRoomInfoResp.data.roominfo.current_point;
            i2 = (int) getRoomInfoResp.data.roominfo.current_user;
            i4 = this.a.a(getRoomInfoResp.data.roominfo.start_time, getRoomInfoResp.data.roominfo.end_time);
        }
        LivePublishEndingActivity.a(this.a.a, true, true, str2, i3, i2, i4, str3, str4);
        this.a.g();
    }
}
