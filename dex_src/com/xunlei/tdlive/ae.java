package com.xunlei.tdlive;

import android.app.Activity;
import android.content.Intent;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.protocol.XLLivePersonVerifyRequest.PersonVerifyResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: LivePlayerActivity.java
final class ae implements ObjectCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ Activity b;
    final /* synthetic */ int c;

    ae(String str, Activity activity, int i) {
        this.a = str;
        this.b = activity;
        this.c = i;
    }

    public final void onResponse(int i, String str, Object obj) {
        PersonVerifyResp personVerifyResp = (PersonVerifyResp) obj;
        if (i != 0 || personVerifyResp == null || personVerifyResp.data == null || !(personVerifyResp.data.onoff == 0 || personVerifyResp.data.status == 4)) {
            if (i == 0) {
                WebBrowserActivity.start(this.b, new StringBuilder("http://h5.live.xunlei.com/active/realname/?t=").append(System.currentTimeMillis()).toString(), "\u5b9e\u540d\u8ba4\u8bc1", false);
                return;
            }
            new c(this.b, null, str, "\u786e\u5b9a", new String[0]).show();
        } else if (this.a == null || this.a.length() <= 0) {
            this.b.startActivityForResult(new Intent(this.b, LivePlayerActivity.class).setAction("com.xunlei.tdlive.ACTION_LIVE_PUBLISH"), this.c);
        } else {
            this.b.startActivityForResult(new Intent(this.b, LivePlayerActivity.class).setAction("com.xunlei.tdlive.ACTION_LIVE_PUBLISH_RESTORE").putExtra("roomid", this.a), this.c);
        }
    }
}
