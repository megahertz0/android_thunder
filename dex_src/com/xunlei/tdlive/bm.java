package com.xunlei.tdlive;

import com.xunlei.tdlive.im.CloseRoomMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class bm extends OnMessageCallback<CloseRoomMessage> {
    final /* synthetic */ au a;

    bm(au auVar) {
        this.a = auVar;
    }

    public void onMessage(CloseRoomMessage closeRoomMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onCloseRoomMessage:").append(closeRoomMessage.userid).append(", roomid:").append(closeRoomMessage.roomid).toString());
        if (au.i(this.a)) {
            au.d(this.a, true);
            au.a(this.a, "\u670d\u52a1\u5668\u901a\u77e5\u5173\u95ed\uff0c\u539f\u56e0\u53ef\u80fd\u6709\uff08\u8fde\u7eed\u9ed1\u5c4f\u4e94\u5206\u949f\u3001\u4eba\u5de5\u5ba1\u67e5\u4e0d\u901a\u8fc7\u7b49\uff09");
        } else {
            au.a(this.a, true);
            au.a(this.a, closeRoomMessage);
        }
        if (!au.v(this.a) && !au.b(this.a, false)) {
            this.a.onBackPressed();
        }
    }
}
