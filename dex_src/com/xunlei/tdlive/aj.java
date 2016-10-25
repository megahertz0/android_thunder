package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveQueryStreamInfoRequest.QueryStreamInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerActivity.java
class aj implements ObjectCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ ObjectCallBack d;
    final /* synthetic */ LivePlayerActivity e;

    aj(LivePlayerActivity livePlayerActivity, String str, String str2, String str3, ObjectCallBack objectCallBack) {
        this.e = livePlayerActivity;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = objectCallBack;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            QueryStreamInfoResp queryStreamInfoResp = (QueryStreamInfoResp) obj;
            LivePlayerActivity.c.a(this.e.h, this.e.c(this.a), this.e.k = queryStreamInfoResp.data.stream.w, this.e.l = queryStreamInfoResp.data.stream.h, this.e.j = queryStreamInfoResp.data.stream.fps, this.e.m = queryStreamInfoResp.data.stream.bitrate, new ak(this));
            return;
        }
        XLog.e("LivePlayerActivity", new StringBuilder("\u76f4\u64ad\u6062\u590d\u5931\u8d25 ret=").append(i).append(" msg=").append(str).toString());
        this.e.b(new StringBuilder("\u76f4\u64ad\u6062\u590d\u5931\u8d25 ret=").append(i).append(" msg=").append(str).toString());
    }
}
