package com.inmobi.ads;

import android.content.ContentValues;

// compiled from: Ad.java
class a {
    private static final String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private long f;
    private String g;

    static {
        a = a.class.getSimpleName();
    }

    public a(f fVar, String str, String str2) {
        this.b = fVar.b().b();
        this.c = fVar.b().c();
        this.d = str;
        this.e = fVar.b().e();
        this.f = System.currentTimeMillis();
        this.g = str2;
    }

    public a(ContentValues contentValues) {
        this.b = contentValues.getAsString("ad_type");
        this.c = contentValues.getAsString("ad_size");
        this.d = contentValues.getAsString("ad_content");
        this.e = contentValues.getAsLong("placement_id").longValue();
        this.f = contentValues.getAsLong("insertion_ts").longValue();
        this.g = contentValues.getAsString("imp_id");
    }

    public ContentValues a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_type", this.b);
        contentValues.put("ad_size", this.c);
        contentValues.put("ad_content", this.d);
        contentValues.put("placement_id", Long.valueOf(this.e));
        contentValues.put("insertion_ts", Long.valueOf(this.f));
        contentValues.put("imp_id", this.g);
        return contentValues;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.g;
    }

    public long d() {
        return this.f;
    }
}
