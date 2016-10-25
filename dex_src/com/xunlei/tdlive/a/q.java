package com.xunlei.tdlive.a;

import android.os.SystemClock;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.XLog;

// compiled from: MsgListAdapter.java
class q implements JsonCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ String b;
    final /* synthetic */ p c;

    q(p pVar, boolean z, String str) {
        this.c = pVar;
        this.a = z;
        this.b = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            JsonWrapper array = jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            if (!this.a) {
                this.c.a(array, p.b(this.c) * 100, (p.b(this.c) * 100) + 100);
            } else if (array.getLength() <= 0) {
                XLog.d("MsgListAdapter", "no more data");
                p.a(this.c, p.a(this.c) - 1);
                p.a(this.c, SystemClock.uptimeMillis());
            } else {
                this.c.b(array);
            }
        } else if (this.a) {
            XLog.d("MsgListAdapter", "load more error");
            p.a(this.c, p.a(this.c) - 1);
        }
        if (this.c.a != null) {
            this.c.a.a(this.b, true, this.a);
        }
        this.c.c();
    }
}
