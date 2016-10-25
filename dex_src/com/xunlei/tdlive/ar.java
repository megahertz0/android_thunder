package com.xunlei.tdlive;

import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.tdlive.LivePlayerActivity.a;
import com.xunlei.tdlive.protocol.XLLiveCreatePublishRoomRequest.CreatePublishRoomResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.w;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: LivePlayerActivity.java
class ar implements ObjectCallBack {
    final /* synthetic */ w a;
    final /* synthetic */ aq b;

    ar(aq aqVar, w wVar) {
        this.b = aqVar;
        this.a = wVar;
    }

    public void onResponse(int i, String str, Object obj) {
        this.a.d();
        if (!this.b.c.c.isFinishing()) {
            CreatePublishRoomResp createPublishRoomResp = (CreatePublishRoomResp) obj;
            if (i != 0 || createPublishRoomResp == null) {
                LivePlayerActivity.e(this.b.c.c).a("\u5f00\u59cb\u76f4\u64ad", true);
                this.b.c.c.showToast(new StringBuilder("\u521b\u5efa\u76f4\u64ad\u5931\u8d25\r\n").append(str).toString(), 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
                XLog.e("LivePlayerActivity", new StringBuilder("\u521b\u5efa\u76f4\u64ad\u5931\u8d25 ret=").append(i).append(" msg=").append(str).toString());
            } else {
                this.b.c.c.setTimer(XLPayErrorCode.XLP_GATE_PARAM_ERROR, 2000);
                LivePlayerActivity.a(this.b.c.c, new a());
                LivePlayerActivity.b(this.b.c.c).b = createPublishRoomResp.data.userid;
                LivePlayerActivity.b(this.b.c.c).a = createPublishRoomResp.data.roomid;
                LivePlayerActivity.b(this.b.c.c).c = createPublishRoomResp.data.stream_push;
                LivePlayerActivity.a(this.b.c.c, new au(this.b.c.c, true, LivePlayerActivity.b(this.b.c.c).a, LivePlayerActivity.b(this.b.c.c).b));
                LivePlayerActivity.c(this.b.c.c).setOwnerActivity(this.b.c.c);
                LivePlayerActivity.c(this.b.c.c).a(new as(this));
                LivePlayerActivity.c(this.b.c.c).show();
                LivePlayerActivity.e(this.b.c.c).dismiss();
                q.e("live_start").a("playid", q.e("live_room_show").d("playid")).a("network", ac.b()).b(new String[0]);
            }
            q.e("live_ready").a(this.b.b.length() > 0 ? "share" : "unshare").a("playid", q.e("live_room_show").d("playid")).a("network", ac.b()).a("errorcode", i).a("shares", this.b.b).a(SetKey.TITLE, this.b.a).a(SHubBatchQueryKeys.url, LivePlayerActivity.b(this.b.c.c) != null ? LivePlayerActivity.b(this.b.c.c).c : BuildConfig.VERSION_NAME).b(new String[]{SHubBatchQueryKeys.url, "shares", SetKey.TITLE});
        }
    }
}
