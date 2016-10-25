package com.xunlei.downloadprovider.personal.redenvelope;

import android.widget.TextView;

// compiled from: RedEnvelopeHelper.java
public final class a {
    public static String a;

    static {
        a = "\u8fd8\u5269%s\u5206\u949f\u8fc7\u671f";
    }

    public static void a(TextView textView, long j) {
        new b(1000 * j, textView).start();
    }
}
