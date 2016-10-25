package com.xunlei.downloadprovider.homepage.recommend.a;

import android.os.Message;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: ShortTimeVideoListAdapter.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(Message message) {
        switch (message.what) {
            case ChatMessage.FLAG_SYS_NOTIFY:
                a.a(this.a, message);
            case XLPayErrorCode.XLP_GATE_PARAM_ERROR:
                a.b(this.a, message);
            default:
                break;
        }
    }
}
