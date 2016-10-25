package com.xunlei.downloadprovider.service.downloads.b;

import android.text.TextUtils;
import com.umeng.a;
import java.io.Serializable;

// compiled from: CidUrl.java
public final class b implements Serializable {
    private String a;
    private String b;
    private String c;
    private long d;
    private String e;

    public b(String str, long j, String str2) {
        this(str, j, str2, (byte) 0);
    }

    private b(String str, long j, String str2, byte b) throws IllegalArgumentException {
        if (str == null || str.isEmpty() || j < 0) {
            throw new IllegalArgumentException("Invalid cid or size.");
        }
        this.b = str;
        this.d = j;
        this.c = str2 == null ? a.d : str2;
        this.e = a.d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cid://").append(str).append("|size|").append(String.valueOf(j));
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("|gcid|").append(str2);
        }
        if (!TextUtils.isEmpty(null)) {
            stringBuilder.append("|file|").append(com.xunlei.c.b.k(null));
        }
        this.a = stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.d != bVar.d) {
            return false;
        }
        if (!this.b.equals(bVar.b)) {
            return false;
        }
        if (this.c != null) {
            if (this.c.equals(bVar.c)) {
                return true;
            }
        } else if (bVar.c == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((this.c != null ? this.c.hashCode() : 0) + (this.b.hashCode() * 31)) * 31) + ((int) (this.d ^ (this.d >>> 32)));
    }

    public final String toString() {
        return this.a;
    }
}
