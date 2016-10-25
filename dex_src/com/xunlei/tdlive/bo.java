package com.xunlei.tdlive;

import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.OutRoomMessage;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bo extends OnMessageCallback<OutRoomMessage> {
    final /* synthetic */ au a;

    bo(au auVar) {
        this.a = auVar;
    }

    public void onMessage(OutRoomMessage outRoomMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onOutRoomMessage:").append(outRoomMessage.userid).toString());
        if (this.a.a != null) {
            this.a.a.getPresenter().a(outRoomMessage.userid);
        }
    }
}
