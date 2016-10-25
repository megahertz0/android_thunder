package com.tencent.wxop.stat.common;

import com.tencent.stat.DeviceInfo;
import com.umeng.message.MsgConstant;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private int f;
    private long g;

    public a() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = MessageService.MSG_DB_READY_REPORT;
        this.f = 0;
        this.g = 0;
    }

    public a(String str, String str2, int i) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = MessageService.MSG_DB_READY_REPORT;
        this.f = 0;
        this.g = 0;
        this.a = str;
        this.b = str2;
        this.e = i;
    }

    JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            q.a(jSONObject, DeviceInfo.TAG_IMEI, this.a);
            q.a(jSONObject, DeviceInfo.TAG_MAC, this.b);
            q.a(jSONObject, DeviceInfo.TAG_MID, this.d);
            q.a(jSONObject, DeviceInfo.TAG_ANDROID_ID, this.c);
            jSONObject.put(MsgConstant.KEY_TS, this.g);
            jSONObject.put(DeviceInfo.TAG_VERSION, this.f);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void a(int i) {
        this.e = i;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public int d() {
        return this.e;
    }

    public String toString() {
        return a().toString();
    }
}
