package com.xunlei.tdlive;

import android.graphics.Color;
import android.text.TextUtils;
import com.xunlei.tdlive.im.LikeMessage;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.e.b;

// compiled from: LivePlayerDialog.java
class bk extends OnMessageCallback<LikeMessage> {
    final /* synthetic */ au a;

    bk(au auVar) {
        this.a = auVar;
    }

    public long getDelayMillis() {
        return 300;
    }

    public void onMessage(LikeMessage likeMessage) {
        Object obj = 1;
        try {
            b bVar;
            int parseColor = Color.parseColor(likeMessage.drawable);
            b bVar2 = b.a;
            if (TextUtils.isEmpty(likeMessage.type) || !likeMessage.type.equals("monkey")) {
                bVar = bVar2;
            } else {
                bVar = b.b;
            }
            boolean b = f.a().b(likeMessage.userid);
            if (!au.i(this.a)) {
                if (bVar == b.b) {
                    if (b) {
                        au.r(this.a);
                    }
                } else if (b || parseColor == au.s(this.a)) {
                    obj = null;
                }
            }
            if (bVar != b.a) {
                XLog.d("LivePlayerDialog", new StringBuilder("onLikeMessage ").append(likeMessage.type).append(" populariy=").append(likeMessage.total_point).append(" roomid=").append(likeMessage.roomid).toString());
                long j = likeMessage.total_point;
                if (j > 0) {
                    this.a.a.getPresenter().b(j);
                }
            }
            if (obj != null) {
                au.t(this.a).addFloatUnit(parseColor, bVar);
            }
        } catch (Exception e) {
        }
    }
}
