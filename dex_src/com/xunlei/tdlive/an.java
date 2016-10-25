package com.xunlei.tdlive;

import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.d.b.a;
import com.xunlei.tdlive.util.XLog;
import org.android.spdy.SpdyProtocol;

// compiled from: LivePlayerActivity.java
class an implements a {
    final /* synthetic */ am a;

    an(am amVar) {
        this.a = amVar;
    }

    public void a(String str) {
        this.a.b.showToast(str, 1, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
    }

    public void b(String str) {
        XLog.e("LivePlayerActivity", str + "\uff0c\u5173\u95ed\u623f\u95f4");
        if (!this.a.b.isFinishing() && !this.a.b.isDestroyed()) {
            new c(this.a.b, "\u63d0\u793a", str, "\u786e\u5b9a", new String[0]).b(new ao(this, str));
        }
    }

    public void a() {
        if (LivePlayerActivity.e(this.a.b) != null && LivePlayerActivity.e(this.a.b).isShowing()) {
            LivePlayerActivity.e(this.a.b).a("\u5f00\u59cb\u76f4\u64ad", true);
        }
    }

    public void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
        LivePlayerActivity.b(this.a.b, j, i, i2, i3, i4, str, i5);
    }
}
