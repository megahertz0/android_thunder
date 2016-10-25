package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveSetPublishStateRequest.SetPublishStateResp;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerActivity.java
class ai implements ObjectCallBack {
    final /* synthetic */ LivePlayerActivity a;

    ai(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        SetPublishStateResp setPublishStateResp = (SetPublishStateResp) obj;
        if (i != 0 || setPublishStateResp == null || setPublishStateResp.data == null || setPublishStateResp.data.status != 1) {
            XLog.e("LivePlayerActivity", new StringBuilder("\u76f4\u64ad\u6062\u590d\u5931\u8d25 ret=").append(i).append(" msg=").append(str).toString());
            return;
        }
        this.a.setTimer(IHost.HOST_NOFITY_PAGE_SELECTED, 2000);
        this.a.i.c = setPublishStateResp.data.stream_push;
        LivePlayerActivity.c.a(this.a.i.a, this.a.i.c);
    }
}
