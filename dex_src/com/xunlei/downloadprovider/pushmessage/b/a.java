package com.xunlei.downloadprovider.pushmessage.b;

import android.content.Context;
import com.xunlei.downloadprovider.a.j;

// compiled from: PushUtil.java
public final class a {
    public static boolean a(Context context) {
        return new j(context, "pushmessageservice", (byte) 0).a.getBoolean("isAwaysRun", true);
    }
}
