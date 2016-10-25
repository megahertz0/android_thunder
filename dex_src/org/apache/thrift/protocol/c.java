package org.apache.thrift.protocol;

import com.xunlei.xiazaibao.BuildConfig;

public class c {
    public final String a;
    public final byte b;
    public final short c;

    public c() {
        this(BuildConfig.VERSION_NAME, (byte) 0, (short) 0);
    }

    public c(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return new StringBuilder("<TField name:'").append(this.a).append("' type:").append(this.b).append(" field-id:").append(this.c).append(">").toString();
    }
}
