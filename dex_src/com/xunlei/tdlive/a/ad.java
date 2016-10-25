package com.xunlei.tdlive.a;

import android.os.SystemClock;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: SDKLiveListAdapter.java
class ad implements JsonCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ String b;
    final /* synthetic */ ac c;

    ad(ac acVar, boolean z, String str) {
        this.c = acVar;
        this.a = z;
        this.b = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        ac.a(this.c, jsonWrapper.getInt("grayid", 0));
        if (i == 0) {
            JsonWrapper array = jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            if (!this.a) {
                this.c.a(array, ac.b(this.c) * 100, (ac.b(this.c) * 100) + 100);
            } else if (array.getLength() <= 0) {
                XLog.d("LiveListAdapter", "no more data");
                ac.b(this.c, ac.a(this.c) - 1);
                ac.a(this.c, SystemClock.uptimeMillis());
            } else {
                this.c.b(array);
            }
        } else if (this.a) {
            XLog.d("LiveListAdapter", "load more error");
            ac.b(this.c, ac.a(this.c) - 1);
        }
        while (ac.c(this.c).size() < ac.a(this.c)) {
            ac.c(this.c).add(Long.valueOf(0));
        }
        if (i == 0 && ac.b(this.c) < ac.a(this.c)) {
            ac.c(this.c).set(ac.b(this.c), Long.valueOf(SystemClock.uptimeMillis()));
        }
        if (this.c.a != null) {
            this.c.a.a(this.b, true, this.a);
        }
        this.c.c();
    }
}
