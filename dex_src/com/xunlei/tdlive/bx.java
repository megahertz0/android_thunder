package com.xunlei.tdlive;

import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.VoiceCreplyMessage;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bx extends OnMessageCallback<VoiceCreplyMessage> {
    final /* synthetic */ au a;

    bx(au auVar) {
        this.a = auVar;
    }

    public void onMessage(VoiceCreplyMessage voiceCreplyMessage) {
        XLog.d("LivePlayerDialog", "onVoiceCreplyMessage");
        this.a.a.getPresenter().j().b(voiceCreplyMessage.flag, voiceCreplyMessage.voiceall_push);
    }
}
