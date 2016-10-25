package com.uc.addon.sdk.remote;

import android.content.Context;

public abstract class AbstractEventReceiver {
    private Context a;
    private Browser b;

    protected static void a() {
    }

    final void a(int i, EventBase eventBase) {
        onEvent(i, eventBase);
    }

    final void a(Context context, Browser browser) {
        this.a = context;
        this.b = browser;
    }

    public final Context getApplicationContext() {
        return this.a;
    }

    public final Browser getBrowser() {
        return this.b;
    }

    public abstract void onEvent(int i, EventBase eventBase);
}
