package com.inmobi.commons.core.c;

import android.content.ContentValues;
import com.umeng.a;
import com.umeng.message.MsgConstant;

// compiled from: TelemetryEvent.java
public class e {
    private static final String a;
    private String b;
    private String c;
    private long d;
    private String e;

    static {
        a = e.class.getSimpleName();
    }

    public e(String str, String str2) {
        this.c = str;
        this.b = str2;
        this.e = null;
        this.d = System.currentTimeMillis();
    }

    public e(String str, String str2, String str3) {
        this.c = str;
        this.b = str2;
        this.e = str3;
        this.d = System.currentTimeMillis();
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.e == null ? a.d : this.e;
    }

    public long d() {
        return this.d;
    }

    public void a(String str) {
        this.e = str;
    }

    public String toString() {
        return b() + "@" + a() + " ";
    }

    public static e a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventType");
        String asString2 = contentValues.getAsString("componentType");
        String asString3 = contentValues.getAsString("payload");
        long longValue = Long.valueOf(contentValues.getAsString(MsgConstant.KEY_TS)).longValue();
        e eVar = new e(asString2, asString, asString3);
        eVar.d = longValue;
        return eVar;
    }

    public ContentValues e() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("componentType", a());
        contentValues.put("eventType", b());
        contentValues.put("payload", c());
        contentValues.put(MsgConstant.KEY_TS, String.valueOf(d()));
        return contentValues;
    }
}
