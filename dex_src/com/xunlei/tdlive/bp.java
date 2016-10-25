package com.xunlei.tdlive;

import com.xunlei.tdlive.im.GiftMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bp extends OnMessageCallback<GiftMessage> {
    final /* synthetic */ au a;

    bp(au auVar) {
        this.a = auVar;
    }

    public void onMessage(GiftMessage giftMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onGiftMessage:").append(giftMessage.userInfo.userid).append(", gift:").append(giftMessage.giftInfo.name).toString());
        if (!giftMessage.userInfo.userid.equals(f.a(this.a.getContext()).k())) {
            this.a.a(0, giftMessage);
        }
        au.a(this.a, giftMessage);
    }
}
