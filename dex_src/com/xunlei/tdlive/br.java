package com.xunlei.tdlive;

import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.RoomUserNumMessage;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class br extends OnMessageCallback<RoomUserNumMessage> {
    final /* synthetic */ au a;

    br(au auVar) {
        this.a = auVar;
    }

    public void onMessage(RoomUserNumMessage roomUserNumMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onRoomUserNumMessage user_count=").append(roomUserNumMessage.user_count).toString());
        this.a.a.getPresenter().a(roomUserNumMessage.user_count);
    }
}
