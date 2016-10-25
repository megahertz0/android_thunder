package com.xunlei.downloadprovider.util;

import org.json.JSONObject;

// compiled from: OnlineConfigure.java
public class r$h {
    JSONObject a;
    private final int b;

    public r$h() {
        this.b = 1;
    }

    public final boolean a() {
        return this.a != null && this.a.optInt("tag_is_show") == 1;
    }
}
