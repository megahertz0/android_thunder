package com.xunlei.downloadprovider.model.protocol.f;

import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UpdateInfo.java
public class c {
    private static final String t;
    public String a;
    public String b;
    public int c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public long k;
    public long l;
    public int m;
    public int n;
    public int o;
    public String p;
    public List<a> q;
    public String r;
    public String s;

    // compiled from: UpdateInfo.java
    public static class a implements Comparable<com.xunlei.downloadprovider.model.protocol.f.c.a> {
        public String a;
        public int b;

        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            com.xunlei.downloadprovider.model.protocol.f.c.a aVar = (com.xunlei.downloadprovider.model.protocol.f.c.a) obj;
            if (aVar == null) {
                return 0;
            }
            if (this.b > aVar.b) {
                return 1;
            }
            return this.b < aVar.b ? -1 : 0;
        }
    }

    public c() {
        this.g = "\u65b0\u7248\u8fc5\u96f7\u66f4\u61c2\u4f60";
        this.k = 1;
        this.m = 1;
        this.n = 1;
        this.o = 3;
        this.p = "\u6709\u65b0\u7248\u672c\u5566\uff01\u70b9\u51fb\u7acb\u5373\u4f53\u9a8c~\uff01";
        this.q = null;
    }

    static {
        t = c.class.getSimpleName();
    }

    public final boolean a() {
        boolean z;
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.l;
        new StringBuilder("current time =>").append(System.currentTimeMillis() / 1000);
        new StringBuilder("server time =>").append(this.l);
        if (currentTimeMillis < 0 || currentTimeMillis >= 3600 || !b.w().contentEquals(this.b)) {
            Object obj = null;
        } else {
            z = true;
        }
        return !z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lastest_v=").append(this.a).append(";");
        stringBuilder.append("this_v=").append(this.b).append(";");
        stringBuilder.append("value=").append(this.c).append(";");
        stringBuilder.append("data=").append(this.d).append(";");
        stringBuilder.append("latest_url=").append(this.f).append(";");
        stringBuilder.append("title=").append(this.g).append(";");
        stringBuilder.append("message=").append(this.h).append(";");
        stringBuilder.append("intro=").append(this.i).append(";");
        stringBuilder.append("desc=").append(this.j).append(";");
        stringBuilder.append("mCircle=").append(this.k).append(";");
        stringBuilder.append("mServerTime=").append(this.l).append(";");
        stringBuilder.append("mUpdatefrom=").append(this.m).append(";");
        stringBuilder.append("mDisplayType=").append(this.n).append(";");
        stringBuilder.append("maxCount=").append(this.o).append(";");
        stringBuilder.append("barMsg=").append(this.p).append(";");
        stringBuilder.append("cancelText=").append(this.r).append(";");
        stringBuilder.append("confirmText=").append(this.s).append(";");
        return stringBuilder.toString();
    }

    public final String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cur_version", this.b);
            jSONObject.put("new_version", this.a);
            jSONObject.put("value", this.c);
            jSONObject.put(AgooConstants.MESSAGE_FLAG, this.e);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_DATA, this.d);
            jSONObject.put("download_url", this.f);
            jSONObject.put(SetKey.TITLE, this.g);
            jSONObject.put(j.C, this.h);
            jSONObject.put("intro", this.i);
            jSONObject.put("desc", this.j);
            jSONObject.put("circle", this.k);
            jSONObject.put("server_time", this.l);
            jSONObject.put("update_from", this.m);
            jSONObject.put("display_type", this.n);
            jSONObject.put("max_count", this.o);
            jSONObject.put("bar_message", this.p);
            jSONObject.put("cancel_text", this.r);
            jSONObject.put("confirm_text", this.s);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public static c a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        c cVar = new c();
        cVar.b = jSONObject.optString("cur_version");
        cVar.a = jSONObject.optString("new_version");
        cVar.c = jSONObject.optInt("value", 0);
        cVar.e = jSONObject.optString(AgooConstants.MESSAGE_FLAG);
        cVar.d = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
        cVar.f = jSONObject.optString("download_url");
        cVar.g = jSONObject.optString(SetKey.TITLE, "\u65b0\u7248\u8fc5\u96f7\u66f4\u61c2\u4f60");
        cVar.h = jSONObject.optString(j.C);
        cVar.i = jSONObject.optString("intro");
        cVar.j = jSONObject.optString("desc");
        cVar.k = jSONObject.optLong("circle");
        cVar.m = jSONObject.optInt("update_from");
        cVar.l = jSONObject.optLong("server_time");
        cVar.n = jSONObject.optInt("display_type", 1);
        cVar.o = jSONObject.optInt("max_count", MqttConnectOptions.MQTT_VERSION_3_1);
        cVar.p = jSONObject.optString("bar_message", "\u6709\u65b0\u7248\u672c\u5566\uff01\u70b9\u51fb\u7acb\u5373\u4f53\u9a8c~\uff01");
        String str = "\u53d6\u6d88";
        if (cVar.c == 3) {
            str = "\u9000\u51fa\u7a0b\u5e8f";
        }
        String str2 = "\u7acb\u5373\u4f53\u9a8c";
        if (cVar.c == 3) {
            str2 = "\u7acb\u523b\u5347\u7ea7";
        }
        cVar.r = jSONObject.optString("cancel_text", str);
        cVar.s = jSONObject.optString("confirm_text", str2);
        return cVar;
    }
}
