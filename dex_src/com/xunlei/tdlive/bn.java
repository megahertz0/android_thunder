package com.xunlei.tdlive;

import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bn extends OnMessageCallback<InRoomMessage> {
    final /* synthetic */ au a;

    bn(au auVar) {
        this.a = auVar;
    }

    public void onMessage(InRoomMessage inRoomMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onInRoomMessage:").append(inRoomMessage.userid).toString());
        if (this.a.a != null) {
            this.a.a.getPresenter().a(inRoomMessage);
        }
        if (inRoomMessage.level != null && f.a().b(inRoomMessage.userid)) {
            au.b(this.a, inRoomMessage.level.current);
        }
    }
}
