package com.tencent.stat.a;

import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Arrays;
import java.util.Properties;

public class c {
    String a;
    String[] b;
    Properties c;

    public c() {
        this.c = null;
    }

    public c(String str, String[] strArr, Properties properties) {
        this.c = null;
        this.a = str;
        this.b = strArr;
        this.c = properties;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        boolean z;
        c cVar = (c) obj;
        if (this.a.equals(cVar.a) && Arrays.equals(this.b, cVar.b)) {
            z = true;
        } else {
            Object obj2 = null;
        }
        return this.c != null ? z && this.c.equals(cVar.c) : z && cVar.c == null;
    }

    public int hashCode() {
        int i = 0;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        if (this.b != null) {
            i ^= Arrays.hashCode(this.b);
        }
        return this.c != null ? i ^ this.c.hashCode() : i;
    }

    public String toString() {
        String str = this.a;
        String str2 = a.d;
        if (this.b != null) {
            String str3 = this.b[0];
            for (int i = 1; i < this.b.length; i++) {
                str3 = str3 + MiPushClient.ACCEPT_TIME_SEPARATOR + this.b[i];
            }
            str2 = new StringBuilder("[").append(str3).append("]").toString();
        }
        if (this.c != null) {
            str2 = str2 + this.c.toString();
        }
        return str + str2;
    }
}
