package com.xunlei.tdlive;

import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.im.MessageDispatcher.ConnectCallback;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;

// compiled from: LivePlayerDialog.java
class bi extends ConnectCallback {
    final /* synthetic */ au a;

    bi(au auVar) {
        this.a = auVar;
    }

    public void onIMKickout(String str) {
        new c(this.a.getContext(), "\u63d0\u793a", str, "\u77e5\u9053\u4e86", new String[0]).b(new bj(this));
    }

    public void onIMConnected(int i, String str) {
        XLog.d("LivePlayerDialog", new StringBuilder("onIMConnected: error: ").append(i).append(", msg:").append(str).toString());
        if (i == 0) {
            if (au.i(this.a)) {
                this.a.i();
                return;
            }
            XLog.d("LivePlayerDialog", "onIMConnected, send inroom again");
            au.q(this.a).a(InRoomMessage.build(f.a(this.a.getContext()).k(), au.k(this.a), ac.j() ? "android" : "android_sdk", ac.g()));
        } else if ((i == 4 || i == 5) && !ac.j()) {
            f.a().g();
        }
    }
}
