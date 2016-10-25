package com.inmobi.commons.core.configs;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.stat.DeviceInfo;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PkConfig.java
public class f extends a {
    private String a;
    private String b;
    private String c;
    private String d;

    public f() {
        this.a = "010001";
        this.b = "E72409364B865B757E1D6B8DB73011BBB1D20C1A9F931ADD3C4C09E2794CE102F8AA7F2D50EB88F9880A576E6C7B0E95712CAE9416F7BACB798564627846E93B";
        this.c = "rsa";
        this.d = MessageService.MSG_DB_NOTIFY_REACHED;
    }

    public String a() {
        return IXAdRequestInfo.PACKAGE;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString("e");
        this.b = jSONObject.getString("m");
        this.c = jSONObject.getString("alg");
        this.d = jSONObject.getString(DeviceInfo.TAG_VERSION);
    }

    public JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.put("e", this.a);
        b.put("m", this.b);
        b.put("alg", this.c);
        b.put(DeviceInfo.TAG_VERSION, this.d);
        return b;
    }

    public boolean c() {
        return (this.a.trim().length() == 0 || this.b.trim().length() == 0 || this.c.trim().length() == 0 || this.d.trim().length() == 0) ? false : true;
    }

    public a d() {
        return new f();
    }

    public String e() {
        return this.a;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.d;
    }
}
