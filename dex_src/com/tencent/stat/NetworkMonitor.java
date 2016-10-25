package com.tencent.stat;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkMonitor {
    private long a;
    private int b;
    private String c;
    private int d;
    private String e;

    public NetworkMonitor() {
        this.a = 0;
        this.b = 0;
        this.c = a.d;
        this.d = 0;
        this.e = a.d;
    }

    public String getDomain() {
        return this.c;
    }

    public long getMillisecondsConsume() {
        return this.a;
    }

    public int getPort() {
        return this.d;
    }

    public String getRemoteIp() {
        return this.e;
    }

    public int getStatusCode() {
        return this.b;
    }

    public void setDomain(String str) {
        this.c = str;
    }

    public void setMillisecondsConsume(long j) {
        this.a = j;
    }

    public void setPort(int i) {
        this.d = i;
    }

    public void setRemoteIp(String str) {
        this.e = str;
    }

    public void setStatusCode(int i) {
        this.b = i;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IXAdRequestInfo.MAX_TITLE_LENGTH, this.a);
            jSONObject.put("st", this.b);
            if (this.c != null) {
                jSONObject.put("dm", this.c);
            }
            jSONObject.put("pt", this.d);
            if (this.e != null) {
                jSONObject.put("rip", this.e);
            }
            jSONObject.put(MsgConstant.KEY_TS, System.currentTimeMillis() / 1000);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
