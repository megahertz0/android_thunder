package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.xunlei.tdlive.util.ac;
import org.android.spdy.SpdyProtocol;

// compiled from: LivePlayerDialog.java
class bg extends BroadcastReceiver {
    final /* synthetic */ au a;

    bg(au auVar) {
        this.a = auVar;
    }

    public void onReceive(Context context, Intent intent) {
        int i = 0;
        if ("com.xunlei.tdlive.ACTION_SHOW_GIFT_DIALOG".equals(intent.getAction())) {
            au.a(this.a, "contribute", -1, false);
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            View a = au.a(this.a);
            if (ac.a()) {
                i = SpdyProtocol.PUBKEY_SEQ_ADASH;
            }
            a.setVisibility(i);
            au.b(this.a).setText("\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\uff01");
        }
    }
}
