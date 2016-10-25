package com.xunlei.tdlive;

import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.VoiceCloseMessage;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class by extends OnMessageCallback<VoiceCloseMessage> {
    final /* synthetic */ au a;

    by(au auVar) {
        this.a = auVar;
    }

    public void onMessage(VoiceCloseMessage voiceCloseMessage) {
        XLog.d("LivePlayerDialog", "onVoiceCloseMessage");
        this.a.a.getPresenter().j().a(voiceCloseMessage.type);
    }
}
