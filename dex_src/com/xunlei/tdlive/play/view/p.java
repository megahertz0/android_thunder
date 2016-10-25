package com.xunlei.tdlive.play.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.tdlive.util.ac;
import org.android.spdy.SpdyProtocol;

// compiled from: LiveReplayDialog.java
class p extends BroadcastReceiver {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            o.a(this.a).setVisibility(!ac.a() ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
            o.b(this.a).setText("\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\uff01");
        }
    }
}
