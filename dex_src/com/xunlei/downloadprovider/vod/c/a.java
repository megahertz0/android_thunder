package com.xunlei.downloadprovider.vod.c;

import java.io.Serializable;

// compiled from: VodReportInfo.java
public final class a implements Serializable {
    public String a;
    public String b;

    public a(String str, String str2) {
        this.a = "native";
        this.b = "old_detail_other";
        this.b = str;
        this.a = str2;
    }

    public final String toString() {
        return new StringBuilder("VodReportInfo{mPlayType='").append(this.a).append('\'').append(", mPlayFrom='").append(this.b).append('\'').append('}').toString();
    }
}
