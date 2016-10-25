package com.xunlei.tdlive;

import com.xunlei.tdlive.im.DeniedChatMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bt extends OnMessageCallback<DeniedChatMessage> {
    final /* synthetic */ au a;

    bt(au auVar) {
        this.a = auVar;
    }

    public void onMessage(DeniedChatMessage deniedChatMessage) {
        XLog.d("LivePlayerDialog", "onDeniedChatMessage");
        this.a.a.getPresenter().c(true);
    }
}
