package com.xunlei.downloadprovider.model.protocol.e;

import android.os.Message;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: ShortTimeVideoManager.java
final class c implements a$a {
    final /* synthetic */ a$b a;
    final /* synthetic */ b b;

    c(b bVar, a$b com_xunlei_downloadprovider_model_protocol_e_a_b) {
        this.b = bVar;
        this.a = com_xunlei_downloadprovider_model_protocol_e_a_b;
    }

    public final void a() {
        Message obtainMessage = this.b.a.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY);
        obtainMessage.obj = this.a;
        obtainMessage.sendToTarget();
    }
}
