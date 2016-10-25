package com.xunlei.tdlive;

import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.im.ChatMessage.User;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.SysNotifyMessage;
import org.android.spdy.SpdyProtocol;

// compiled from: LivePlayerDialog.java
class bv extends OnMessageCallback<SysNotifyMessage> {
    final /* synthetic */ au a;

    bv(au auVar) {
        this.a = auVar;
    }

    public void onMessage(SysNotifyMessage sysNotifyMessage) {
        if (sysNotifyMessage.flag != 0 && sysNotifyMessage.flag != 1) {
            String[] c = au.c(this.a, new StringBuilder("onsysmsg_").append(sysNotifyMessage.flag).toString());
            ChatMessage chatMessage = new ChatMessage();
            User user = chatMessage.user;
            String str = (sysNotifyMessage.nickname == null || sysNotifyMessage.nickname.length() <= 0) ? "\u7cfb\u7edf\u6d88\u606f" : sysNotifyMessage.nickname;
            user.nickname = str;
            chatMessage.content = sysNotifyMessage.msg;
            chatMessage.flag = sysNotifyMessage.flag + 1000;
            chatMessage.color1 = c[0];
            chatMessage.color2 = c[1];
            au.j(this.a).a(chatMessage);
            au.f(this.a, false);
        } else if (sysNotifyMessage.flag == 1) {
            au.a(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            au.b(this.a).setText("\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\uff01");
        } else {
            au.a(this.a).setVisibility(0);
            au.b(this.a).setText(sysNotifyMessage.msg);
        }
    }
}
