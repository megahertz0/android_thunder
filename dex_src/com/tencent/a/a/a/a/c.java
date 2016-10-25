package com.tencent.a.a.a.a;

import com.tencent.stat.DeviceInfo;
import com.umeng.message.MsgConstant;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    long T;
    String a;
    String b;
    String c;

    public c() {
        this.a = null;
        this.b = null;
        this.c = MessageService.MSG_DB_READY_REPORT;
        this.T = 0;
    }

    static c e(String str) {
        c cVar = new c();
        if (h.b(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(DeviceInfo.TAG_IMEI)) {
                    cVar.a = jSONObject.getString(DeviceInfo.TAG_IMEI);
                }
                if (!jSONObject.isNull(DeviceInfo.TAG_MAC)) {
                    cVar.b = jSONObject.getString(DeviceInfo.TAG_MAC);
                }
                if (!jSONObject.isNull(DeviceInfo.TAG_MID)) {
                    cVar.c = jSONObject.getString(DeviceInfo.TAG_MID);
                }
                if (!jSONObject.isNull(MsgConstant.KEY_TS)) {
                    cVar.T = jSONObject.getLong(MsgConstant.KEY_TS);
                }
            } catch (JSONException e) {
            }
        }
        return cVar;
    }

    private JSONObject n() {
        JSONObject jSONObject = new JSONObject();
        try {
            h.a(jSONObject, DeviceInfo.TAG_IMEI, this.a);
            h.a(jSONObject, DeviceInfo.TAG_MAC, this.b);
            h.a(jSONObject, DeviceInfo.TAG_MID, this.c);
            jSONObject.put(MsgConstant.KEY_TS, this.T);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final String a() {
        return this.c;
    }

    public final String toString() {
        return n().toString();
    }
}
