package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.im.MessageDispatcher.ConnectCallback;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;

// compiled from: ReplayDialogPresenter.java
class al extends ConnectCallback {
    final /* synthetic */ aa a;

    al(aa aaVar) {
        this.a = aaVar;
    }

    public void onIMKickout(String str) {
        new c(this.a.e(), "\u63d0\u793a", str, "\u77e5\u9053\u4e86", new String[0]).b(new am(this));
    }

    public void onIMConnected(int i, String str) {
        XLog.d("ReplayDialogPresenter", new StringBuilder("onIMConnected: error").append(i).append(", msg:").append(str).toString());
        if (i == 0) {
            XLog.d("ReplayDialogPresenter", "onIMConnected, send inroom again");
            aa.m(this.a).a(InRoomMessage.build(f.a(this.a.e()).k(), aa.l(this.a), ac.j() ? "android" : "android_sdk", ac.g()));
        } else if ((i == 4 || i == 5) && !ac.j()) {
            f.a().g();
        }
    }
}
