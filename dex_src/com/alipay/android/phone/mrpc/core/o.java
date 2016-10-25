package com.alipay.android.phone.mrpc.core;

import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public final class o extends t {
    private String b;
    private byte[] c;
    private String d;
    private ArrayList<Header> e;
    private Map<String, String> f;
    private boolean g;

    public o(String str) {
        this.b = str;
        this.e = new ArrayList();
        this.f = new HashMap();
        this.d = "application/x-www-form-urlencoded";
    }

    public final String a() {
        return this.b;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(byte[] bArr) {
        this.c = bArr;
    }

    public final String b(String str) {
        return this.f == null ? null : (String) this.f.get(str);
    }

    public final byte[] b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final boolean e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.c == null) {
            if (oVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(oVar.c)) {
            return false;
        }
        return this.b == null ? oVar.b == null : this.b.equals(oVar.b);
    }

    public final int hashCode() {
        int i = 1;
        if (this.f != null && this.f.containsKey(SocializeConstants.WEIBO_ID)) {
            i = ((String) this.f.get(SocializeConstants.WEIBO_ID)).hashCode() + 31;
        }
        return (this.b == null ? 0 : this.b.hashCode()) + (i * 31);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.b, this.e});
    }
}
