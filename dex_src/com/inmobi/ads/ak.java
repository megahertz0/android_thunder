package com.inmobi.ads;

import android.content.ContentValues;
import com.umeng.a;
import java.util.Map;

// compiled from: Placement.java
public class ak {
    private static final String a;
    private long b;
    private String c;
    private Map<String, String> d;
    private String e;
    private String f;

    static {
        a = ak.class.getSimpleName();
    }

    public ak(long j, String str) {
        this.f = "int";
        this.b = j;
        this.c = str;
        if (this.c == null) {
            this.c = a.d;
        }
    }

    public void a(Map<String, String> map) {
        this.d = map;
    }

    public void a(String str) {
        this.e = str;
    }

    public Map<String, String> a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public long c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.f;
    }

    public ContentValues f() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("placement_id", Long.valueOf(this.b));
        contentValues.put("last_accessed_ts", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("tp_key", this.c);
        return contentValues;
    }

    public ak(ContentValues contentValues) {
        this.f = "int";
        this.b = contentValues.getAsLong("placement_id").longValue();
        this.c = contentValues.getAsString("tp_key");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ak akVar = (ak) obj;
        if (this.b != akVar.b) {
            return false;
        }
        if (this.c == null && akVar.c == null) {
            return true;
        }
        return (this.c == null || akVar.c == null) ? false : this.c.equals(akVar.c);
    }

    public int hashCode() {
        int i = (int) (this.b ^ (this.b >>> 32));
        return this.c != null ? (i * 31) + this.c.hashCode() : i;
    }
}
