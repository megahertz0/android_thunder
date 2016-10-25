package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveQueryStreamInfoRequest.QueryStreamInfoResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LivePlayerActivity.java
class am implements ObjectCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ LivePlayerActivity b;

    am(LivePlayerActivity livePlayerActivity, String str) {
        this.b = livePlayerActivity;
        this.a = str;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            QueryStreamInfoResp queryStreamInfoResp = (QueryStreamInfoResp) obj;
            LivePlayerActivity.c.a(this.b.h, this.b.c(this.a), this.b.k = queryStreamInfoResp.data.stream.w, this.b.l = queryStreamInfoResp.data.stream.h, this.b.j = queryStreamInfoResp.data.stream.fps, this.b.m = queryStreamInfoResp.data.stream.bitrate, new an(this));
            return;
        }
        this.b.showToast(new StringBuilder("\u521b\u5efa\u76f4\u64ad\u5931\u8d25\r\n").append(str).toString(), 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
    }
}
