package com.xiaomi.smack.packet;

import com.umeng.message.proguard.j;

public class g {
    private String a;

    public g(String str) {
        this.a = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stream:error (").append(this.a).append(j.t);
        return stringBuilder.toString();
    }
}
