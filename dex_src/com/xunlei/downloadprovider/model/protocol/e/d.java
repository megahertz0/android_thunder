package com.xunlei.downloadprovider.model.protocol.e;

import android.os.Handler;
import android.os.Message;
import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: ShortTimeVideoManager.java
final class d implements a {
    final /* synthetic */ Handler a;
    final /* synthetic */ int b;
    final /* synthetic */ a c;

    d(a aVar, Handler handler, int i) {
        this.c = aVar;
        this.a = handler;
        this.b = i;
    }

    public final void onErrorResponse(w wVar) {
        if (this.a != null) {
            Message obtainMessage = this.a.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY);
            a$b com_xunlei_downloadprovider_model_protocol_e_a_b = new a$b();
            com_xunlei_downloadprovider_model_protocol_e_a_b.b = this.b;
            com_xunlei_downloadprovider_model_protocol_e_a_b.c = 1;
            obtainMessage.obj = com_xunlei_downloadprovider_model_protocol_e_a_b;
            obtainMessage.sendToTarget();
        }
    }
}
