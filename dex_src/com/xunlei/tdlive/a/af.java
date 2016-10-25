package com.xunlei.tdlive.a;

import android.os.Handler.Callback;
import android.os.Message;

// compiled from: VisitorHListAdapter.java
class af implements Callback {
    final /* synthetic */ ae a;

    af(ae aeVar) {
        this.a = aeVar;
    }

    public boolean handleMessage(Message message) {
        if (message.what != 100) {
            return false;
        }
        this.a.notifyDataSetChanged();
        this.a.e = System.currentTimeMillis();
        return true;
    }
}
