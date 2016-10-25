package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveClosePublishRoomRequest.ClosePublishRoomResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;

// compiled from: LivePlayerActivity.java
class ad implements ObjectCallBack {
    final /* synthetic */ LivePlayerActivity a;

    ad(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        int i2;
        int i3;
        int i4;
        ClosePublishRoomResp closePublishRoomResp = (ClosePublishRoomResp) obj;
        if (i != 0 || closePublishRoomResp == null || closePublishRoomResp.data == null) {
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            i4 = closePublishRoomResp.data.current_point;
            i3 = closePublishRoomResp.data.current_user;
            i2 = this.a.b(closePublishRoomResp.data.start_time, closePublishRoomResp.data.end_time);
        }
        String m = f.a().m();
        String o = f.a().o();
        LivePublishEndingActivity.a(this.a, false, false, this.a.i.b, i4, i3, i2, m, o);
        this.a.c();
    }
}
