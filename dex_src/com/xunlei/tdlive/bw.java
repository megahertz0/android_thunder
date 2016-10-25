package com.xunlei.tdlive;

import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.VoiceConnectMessage;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bw extends OnMessageCallback<VoiceConnectMessage> {
    final /* synthetic */ au a;

    bw(au auVar) {
        this.a = auVar;
    }

    public void onMessage(VoiceConnectMessage voiceConnectMessage) {
        XLog.d("LivePlayerDialog", "onVoiceConnectMessage");
        this.a.a.getPresenter().j().a(voiceConnectMessage.userid, voiceConnectMessage.nickname, au.k(this.a));
    }
}
