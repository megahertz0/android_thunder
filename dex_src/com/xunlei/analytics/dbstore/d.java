package com.xunlei.analytics.dbstore;

public class d {
    public String a;
    public String b;
    public String c;
    public int d;

    public d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public String toString() {
        return new StringBuilder("_ID=").append(this.d).append(", mAppId=").append(this.a).append(", mInterId=").append(this.b).append(", mEventData=").append(this.c).toString();
    }
}
