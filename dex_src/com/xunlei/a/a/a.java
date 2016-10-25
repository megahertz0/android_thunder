package com.xunlei.a.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: XLScreenObserver.java
public final class a {
    public Context a;
    public a b;
    public b c;

    // compiled from: XLScreenObserver.java
    private class a extends BroadcastReceiver {
        private String b;

        private a() {
            this.b = null;
        }

        public final void onReceive(Context context, Intent intent) {
            this.b = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(this.b)) {
                a.this.c.q();
            } else if ("android.intent.action.SCREEN_OFF".equals(this.b)) {
                a.this.c.r();
            } else if ("android.intent.action.USER_PRESENT".equals(this.b)) {
                a.this.c.s();
            }
        }
    }

    public a(Context context) {
        this.a = context;
        this.b = new a();
    }
}
