package com.xiaomi.network;

import com.umeng.message.MsgConstant;
import org.json.JSONObject;

public class AccessHistory {
    private int a;
    private long b;
    private long c;
    private String d;
    private long e;

    public AccessHistory() {
        this(0, 0, 0, null);
    }

    public AccessHistory(int i, long j, long j2, Exception exception) {
        this.a = i;
        this.b = j;
        this.e = j2;
        this.c = System.currentTimeMillis();
        if (exception != null) {
            this.d = exception.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.a;
    }

    public AccessHistory a(JSONObject jSONObject) {
        this.b = jSONObject.getLong("cost");
        this.e = jSONObject.getLong("size");
        this.c = jSONObject.getLong(MsgConstant.KEY_TS);
        this.a = jSONObject.getInt("wt");
        this.d = jSONObject.optString("expt");
        return this;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.b);
        jSONObject.put("size", this.e);
        jSONObject.put(MsgConstant.KEY_TS, this.c);
        jSONObject.put("wt", this.a);
        jSONObject.put("expt", this.d);
        return jSONObject;
    }
}
