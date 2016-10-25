package com.xunlei.downloadprovider.web.record;

import android.text.TextUtils;
import com.alipay.sdk.packet.d;
import com.android.volley.Request;
import com.android.volley.f;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: RecordServerUtils.java
public final class aa {
    b a;
    boolean b;
    int c;
    long d;
    boolean e;
    final a f;

    // compiled from: RecordServerUtils.java
    public static interface a {
        void a();

        void a(String str);
    }

    // compiled from: RecordServerUtils.java
    public static interface b {
        void a(int i);

        void a(ArrayList<t> arrayList, int i, long j, boolean z);
    }

    public aa() {
        this.a = null;
        this.b = false;
        this.c = -1;
        this.d = 0;
        this.e = false;
        this.f = new ae(this);
    }

    public final void a(b bVar, long j) {
        this.a = bVar;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", LoginHelper.a().j);
            jSONObject.put(JsInterface.FUNPLAY_AD_TRPE, "retdot");
            jSONObject.put("getUpdate", 1);
            jSONObject.put("timeStamp", j);
            this.c = 1;
            a("http://quan.m.xunlei.com/cgi-bin/favorites", jSONObject.toString(), this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(b bVar, int i) {
        this.a = bVar;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", LoginHelper.a().j);
            jSONObject.put("getYunUrlList", 1);
            jSONObject.put(JsInterface.FUNPLAY_AD_TRPE, "yuntolocal");
            this.c = i;
            a("http://quan.m.xunlei.com/cgi-bin/favorites", jSONObject.toString(), this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void a(String str, String str2, a aVar) {
        Request adVar = new ad(str, new ab(aVar), new ac(aVar), str2);
        adVar.setRetryPolicy(new f(3000, 0, 1.0f));
        adVar.setShouldCache(false);
        com.xunlei.downloadprovider.j.a.c().a(adVar);
    }

    static String a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("productId", com.xunlei.downloadprovider.a.b.h());
        hashMap.put("peerId", com.xunlei.downloadprovider.a.b.d());
        hashMap.put(Constants.KEY_IMEI, com.xunlei.downloadprovider.a.b.f());
        hashMap.put(anet.channel.strategy.dispatch.a.CONFIG_VERSION, com.xunlei.downloadprovider.a.b.w());
        hashMap.put("cvc", String.valueOf(com.xunlei.downloadprovider.a.b.x()));
        hashMap.put("ov", String.valueOf(com.xunlei.downloadprovider.a.b.i()));
        hashMap.put(d.n, com.xunlei.downloadprovider.a.b.y());
        hashMap.put(MsgConstant.KEY_TS, String.valueOf(System.currentTimeMillis()));
        hashMap.put("channelId", com.xunlei.downloadprovider.a.b.g());
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put(Constants.KEY_SECURITY_SIGN, str2);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Entry entry : hashMap.entrySet()) {
            String str3 = (String) entry.getValue();
            String str4 = str3 == null ? com.umeng.a.d : str3;
            int i2 = i + 1;
            if (i2 > 1) {
                stringBuilder.append(com.alipay.sdk.sys.a.b);
            }
            stringBuilder.append((String) entry.getKey()).append("=").append(str4);
            i = i2;
        }
        return str + "?" + stringBuilder.toString();
    }

    protected final boolean a(Object obj) {
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            if (jSONObject.getInt("resultcode") != 0) {
                return false;
            }
            if (jSONObject.getInt("isUpdate") == 1) {
                this.b = true;
            } else {
                this.b = false;
            }
            this.d = jSONObject.optLong("timeStamp");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected final boolean b(Object obj) {
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            if (jSONObject.getInt("resultcode") != 0) {
                return false;
            }
            this.d = jSONObject.optLong("timeStamp");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    final boolean a(Object obj, ArrayList<t> arrayList) {
        if (!(obj instanceof String)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            if (jSONObject.getInt("resultcode") != 0) {
                return false;
            }
            this.d = jSONObject.optLong("timeStamp");
            JSONArray jSONArray = jSONObject.getJSONArray("urlAddressList");
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONObject = (JSONObject) jSONArray.get(i);
                    com.xunlei.downloadprovider.model.b bVar = new com.xunlei.downloadprovider.model.b();
                    bVar.b = jSONObject.optString("urlName");
                    bVar.c = jSONObject.optString("urlAddress");
                    arrayList.add(new t(bVar));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
