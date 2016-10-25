package com.xunlei.tdlive;

import com.xunlei.tdlive.im.AllowChatMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bu extends OnMessageCallback<AllowChatMessage> {
    final /* synthetic */ au a;

    bu(au auVar) {
        this.a = auVar;
    }

    public void onMessage(AllowChatMessage allowChatMessage) {
        XLog.d("LivePlayerDialog", "onAllowChatMessage");
        this.a.a.getPresenter().c(false);
    }
}
