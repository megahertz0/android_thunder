package com.xunlei.tdlive;

import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.d.b.a;
import com.xunlei.tdlive.protocol.XLLiveSetPublishStateRequest;
import com.xunlei.tdlive.util.XLog;
import org.android.spdy.SpdyProtocol;

// compiled from: LivePlayerActivity.java
class ak implements a {
    final /* synthetic */ aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public void a(String str) {
        this.a.e.showToast(str, 1, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
    }

    public void b(String str) {
        XLog.e("LivePlayerActivity", str + "\uff0c\u5173\u95ed\u623f\u95f4");
        if (!this.a.e.isFinishing() && !this.a.e.isDestroyed()) {
            new c(this.a.e, "\u63d0\u793a", str, "\u786e\u5b9a", new String[0]).b(new al(this, str));
        }
    }

    public void a() {
        XLog.e("LivePlayerActivity", new StringBuilder("doPublishRestore roomid ").append(this.a.b).append(", StateRequest.STATE_RESUME").toString());
        new XLLiveSetPublishStateRequest(this.a.a, this.a.c, this.a.b, 1).send(this.a.d);
    }

    public void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
        LivePlayerActivity.b(this.a.e, j, i, i2, i3, i4, str, i5);
    }
}
