package com.xunlei.tdlive;

import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.user.f;

// compiled from: LivePlayerDialog.java
class bl extends OnMessageCallback<ChatMessage> {
    final /* synthetic */ au a;

    bl(au auVar) {
        this.a = auVar;
    }

    public long getDelayMillis() {
        return 500;
    }

    public boolean onPreMessage(ChatMessage chatMessage) {
        try {
            String[] c = au.c(this.a, chatMessage.flag == 1 ? "like" : "onsendchat");
            chatMessage.color1 = c[0];
            chatMessage.color2 = c[1];
            if (chatMessage.content.equals(au.u(this.a))) {
                if (!f.a(this.a.getContext()).k().equals(chatMessage.userid)) {
                    return true;
                }
                chatMessage.content = new StringBuilder("\u6211").append(au.u(this.a)).toString();
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void onMessage(ChatMessage chatMessage) {
        au.a(this.a, chatMessage);
        au.j(this.a).a(chatMessage);
        au.f(this.a, false);
    }
}
