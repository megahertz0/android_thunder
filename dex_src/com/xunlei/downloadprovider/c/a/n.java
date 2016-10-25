package com.xunlei.downloadprovider.c.a;

import java.io.Serializable;

// compiled from: TargetCommentInfo.java
public final class n implements Serializable {
    public long a;
    public String b;
    public long c;
    public String d;
    public String e;
    public String f;

    public final void a(String str) {
        if (str == null) {
            this.b = str;
        } else {
            this.b = str.trim();
        }
    }
}
