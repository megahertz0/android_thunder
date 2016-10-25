package com.xunlei.tdlive.user;

import android.os.Handler;
import android.os.Message;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;

// compiled from: UserHelper.java
class f$d extends Handler implements b {
    b a;

    public f$d() {
        f.a().a(this);
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(boolean z) {
        sendEmptyMessageDelayed(z ? ChatMessage.FLAG_SYS_NOTIFY : XLPayErrorCode.XLP_GATE_PARAM_ERROR, 100);
    }

    public void handleMessage(Message message) {
        if (message.what == 1000 || message.what == 1001) {
            if (this.a != null) {
                this.a.a(message.what == 1000);
                this.a = null;
            }
            if (message.what == 1000) {
                q.e("login_return_result").a(q.e("login_start").a()).b("success").a("userid", f.a().k()).a("network", ac.b()).a("errorcode", 0).b(new String[0]);
            }
        }
    }
}
