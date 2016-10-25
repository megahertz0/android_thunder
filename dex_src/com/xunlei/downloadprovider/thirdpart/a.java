package com.xunlei.downloadprovider.thirdpart;

import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.a.b;

// compiled from: IntentHandler.java
public abstract class a {
    public static final String a;
    public Context b;
    public Intent c;

    public abstract void a();

    static {
        a = b.b() + ".thirdpart_business";
    }

    public a(Context context, Intent intent) {
        this.b = context;
        this.c = intent;
    }
}
