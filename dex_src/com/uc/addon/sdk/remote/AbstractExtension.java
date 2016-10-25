package com.uc.addon.sdk.remote;

import android.content.Context;

public abstract class AbstractExtension {
    private Context a;
    private Browser b;

    protected static void a() {
    }

    final void a(Context context, Browser browser) {
        this.a = context;
        this.b = browser;
    }

    public Context getApplicationContext() {
        return this.a;
    }

    public final Browser getBrowser() {
        return this.b;
    }

    public final void invoke() {
        onInvoke();
    }

    public abstract void onInvoke();
}
