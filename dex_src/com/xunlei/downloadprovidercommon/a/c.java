package com.xunlei.downloadprovidercommon.a;

import android.text.TextUtils;
import com.umeng.a;
import java.io.Serializable;
import java.util.HashMap;

// compiled from: StatEvent.java
public final class c implements Serializable {
    public final String a;
    protected final HashMap<String, String> b;
    public String c;
    public String d;

    private c(String str) {
        this.b = new HashMap();
        this.c = null;
        this.d = null;
        this.a = str;
    }

    public static c a(String str) {
        return new c(str);
    }

    public final HashMap<String, String> a() {
        return this.b;
    }

    public final c a(String str, int i) {
        return b(str, String.valueOf(i));
    }

    public final c a(String str, long j) {
        return b(str, String.valueOf(j));
    }

    public final c a(String str, String str2) {
        return b(str, str2);
    }

    public final c b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = this.b;
            if (str2 == null) {
                str2 = a.d;
            }
            hashMap.put(str, str2);
        }
        return this;
    }

    public final c b(String str, long j) {
        return b(str, String.valueOf(j));
    }

    public final boolean b() {
        return this.b.size() != 0;
    }

    public final String toString() {
        return new StringBuilder("StatEvent{mEventId='").append(this.a).append('\'').append(", mExtraData=").append(this.b).append(", mReserve1='").append(this.c).append('\'').append(", mReserve2='").append(this.d).append('\'').append('}').toString();
    }
}
