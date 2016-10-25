package anet.channel.strategy;

import anet.channel.strategy.k.a;
import anet.channel.strategy.k.b;
import anet.channel.util.ALog;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.utl.BaseMonitor;
import com.umeng.message.MsgConstant;
import com.xunlei.download.Downloads.Impl;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: Taobao
public class k {

    // compiled from: Taobao
    public static class a {
        public final int a;
        public final String b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final String g;
        public final boolean h;
        public final boolean i;
        public final String j;

        public a(JSONObject jSONObject) {
            boolean z;
            boolean z2 = true;
            this.a = jSONObject.optInt("port");
            this.b = jSONObject.optString("protocol");
            this.c = jSONObject.optInt("cto");
            this.d = jSONObject.optInt("rto");
            this.e = jSONObject.optInt("retry");
            this.f = jSONObject.optInt("heartbeat");
            this.g = jSONObject.optString("rtt", com.umeng.a.d);
            if (jSONObject.optInt("l7encript", 0) == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            this.j = jSONObject.optString("publickey");
            if (jSONObject.optInt(BaseMonitor.ALARM_POINT_AUTH, 0) != 1) {
                z2 = false;
            }
            this.h = z2;
        }

        public String toString() {
            return new StringBuilder("{port=").append(this.a).append("protocol=").append(this.b).append("publickey=").append(this.j).append(h.d).toString();
        }
    }

    // compiled from: Taobao
    public static class b {
        public final String a;
        public final int b;
        public final String c;
        public final String d;
        public final String[] e;
        public final a[] f;
        public final String g;
        public final int h;
        public final String i;
        public final int j;
        public final int k;
        public final boolean l;
        public final boolean m;
        public final String n;
        public final boolean o;
        public final int p;
        public final boolean q;

        public b(JSONObject jSONObject) {
            boolean z = true;
            int i = 0;
            this.a = jSONObject.optString(com.taobao.accs.internal.b.ELECTION_KEY_HOST);
            this.b = jSONObject.optInt("ttl");
            this.c = jSONObject.optString("safeAisles");
            this.d = jSONObject.optString("cname");
            this.g = jSONObject.optString("hrStrategy");
            this.h = jSONObject.optInt("hrIntervalTime");
            this.i = jSONObject.optString("hrUrlPath");
            this.j = jSONObject.optInt("hrNum");
            this.k = jSONObject.optInt("parallelConNum");
            this.l = jSONObject.optBoolean("idc");
            this.p = jSONObject.optInt("isHot", -1);
            this.m = jSONObject.optInt("clear") == 1;
            this.n = jSONObject.optString(Impl.COLUMN_BT_INFO_HASH);
            if (jSONObject.optInt("notModified") != 1) {
                z = false;
            }
            this.o = z;
            this.q = jSONObject.optBoolean("effectNow");
            JSONArray optJSONArray = jSONObject.optJSONArray("ips");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.e = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    this.e[i2] = optJSONArray.optString(i2);
                }
            } else {
                this.e = null;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("aisles");
            if (optJSONArray2 != null) {
                int length2 = optJSONArray2.length();
                this.f = new a[length2];
                while (i < length2) {
                    this.f[i] = new a(optJSONArray2.optJSONObject(i));
                    i++;
                }
                return;
            }
            this.f = null;
        }
    }

    // compiled from: Taobao
    public static class c {
        public final String a;
        public final String b;
        public final b[] c;
        public final String d;
        public final String e;
        public final int f;
        public final int g;
        public final int h;

        public c(JSONObject jSONObject) {
            this.a = jSONObject.optString("ip");
            this.b = jSONObject.optString("unit");
            this.d = jSONObject.optString(ParamKey.UID, null);
            this.e = jSONObject.optString(MsgConstant.KEY_UTDID, null);
            this.f = jSONObject.optInt(anet.channel.strategy.dispatch.a.CONFIG_VERSION);
            this.g = jSONObject.optInt("fcl");
            this.h = jSONObject.optInt("fct");
            JSONArray optJSONArray = jSONObject.optJSONArray(BaseMonitor.COUNT_POINT_DNS);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.c = new b[length];
                for (int i = 0; i < length; i++) {
                    this.c[i] = new b(optJSONArray.optJSONObject(i));
                }
                return;
            }
            this.c = null;
        }
    }

    public static c a(JSONObject jSONObject) {
        try {
            return new c(jSONObject);
        } catch (Throwable e) {
            ALog.e("StrategyResultParser", "Parse HttpDns response failed.", null, e, "JSON Content", jSONObject.toString());
            return null;
        }
    }
}
