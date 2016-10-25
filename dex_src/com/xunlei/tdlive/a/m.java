package com.xunlei.tdlive.a;

import android.os.SystemClock;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: LiveListAdapter.java
class m implements JsonCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ String b;
    final /* synthetic */ l c;

    m(l lVar, boolean z, String str) {
        this.c = lVar;
        this.a = z;
        this.b = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        l.a(this.c, jsonWrapper.getInt("grayid", 0));
        if (i == 0) {
            JsonWrapper array = jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            if (!this.a) {
                this.c.a(array, l.b(this.c) * 100, (l.b(this.c) * 100) + 100);
            } else if (array.getLength() <= 0) {
                XLog.d("LiveListAdapter", "no more data");
                l.b(this.c, l.a(this.c) - 1);
                l.a(this.c, SystemClock.uptimeMillis());
            } else {
                this.c.b(array);
            }
        } else if (this.a) {
            XLog.d("LiveListAdapter", "load more error");
            l.b(this.c, l.a(this.c) - 1);
        }
        while (l.c(this.c).size() < l.a(this.c)) {
            l.c(this.c).add(Long.valueOf(0));
        }
        if (i == 0 && l.b(this.c) < l.a(this.c)) {
            l.c(this.c).set(l.b(this.c), Long.valueOf(SystemClock.uptimeMillis()));
        }
        if (this.c.a != null) {
            this.c.a.a(this.b, true, this.a);
        }
        this.c.c();
    }
}
