package com.inmobi.commons.core.configs;

import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: RootConfig.java
public class g extends a {
    private static final Object g;
    private int a;
    private int b;
    private int c;
    private List<a> d;
    private b e;
    private JSONObject f;

    // compiled from: RootConfig.java
    static final class a {
        private String a;
        private long b;
        private String c;
        private String d;

        a() {
        }

        public final String a() {
            return this.a;
        }

        public final Long b() {
            return Long.valueOf(this.b);
        }

        public final String c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }
    }

    // compiled from: RootConfig.java
    public static final class b {
        private String a;
        private String b;

        public b() {
            this.a = com.inmobi.commons.a.b.c();
            this.b = com.inmobi.commons.a.b.g();
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }
    }

    static {
        g = new Object();
    }

    public g() {
        this.a = 3;
        this.b = 60;
        this.c = 3;
        this.d = new ArrayList();
        this.e = new b();
        this.f = new JSONObject();
    }

    public String a() {
        return "root";
    }

    public JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONArray jSONArray = new JSONArray();
        b.put("maxRetries", this.a);
        b.put("retryInterval", this.b);
        b.put("waitTime", this.c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(GameAppOperation.QQFAV_DATALINE_VERSION, this.e.a);
        jSONObject.put(SocialConstants.PARAM_URL, this.e.b);
        b.put("latestSdkInfo", jSONObject);
        synchronized (g) {
            for (int i = 0; i < this.d.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(JsInterface.FUNPLAY_AD_TRPE, ((a) this.d.get(i)).a);
                jSONObject2.put("expiry", ((a) this.d.get(i)).b);
                jSONObject2.put("protocol", ((a) this.d.get(i)).c);
                jSONObject2.put(SocialConstants.PARAM_URL, ((a) this.d.get(i)).d);
                jSONArray.put(jSONObject2);
            }
        }
        b.put("components", jSONArray);
        return b;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getInt("maxRetries");
        this.b = jSONObject.getInt("retryInterval");
        this.c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.e.a = jSONObject2.getString(GameAppOperation.QQFAV_DATALINE_VERSION);
        this.e.b = jSONObject2.getString(SocialConstants.PARAM_URL);
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        synchronized (g) {
            this.d.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                a aVar = new a();
                aVar.a = jSONObject3.getString(JsInterface.FUNPLAY_AD_TRPE);
                aVar.b = jSONObject3.getLong("expiry");
                aVar.c = jSONObject3.getString("protocol");
                aVar.d = jSONObject3.getString(SocialConstants.PARAM_URL);
                this.d.add(aVar);
            }
        }
    }

    public boolean c() {
        if (this.d == null) {
            return false;
        }
        if (this.a < 0 || this.b < 0 || this.c < 0) {
            return false;
        }
        if (this.e.a().trim().length() == 0 || (!this.e.b().startsWith("http://") && !this.e.b().startsWith("https://"))) {
            return false;
        }
        synchronized (g) {
            int i = 0;
            while (i < this.d.size()) {
                a aVar = (a) this.d.get(i);
                if (aVar.a().trim().length() == 0) {
                    return false;
                }
                if (aVar.b().longValue() >= 0 && aVar.b().longValue() <= 864000) {
                    if (aVar.c().trim().length() == 0) {
                        return false;
                    }
                    if (aVar.d() != null && aVar.d().trim().length() != 0) {
                        if (aVar.d().startsWith("http://") || aVar.d().startsWith("https://")) {
                        }
                        i++;
                    }
                    return false;
                }
                return false;
            }
            return true;
        }
    }

    public a d() {
        return new g();
    }

    public long a(String str) {
        long b;
        synchronized (g) {
            for (int i = 0; i < this.d.size(); i++) {
                a aVar = (a) this.d.get(i);
                if (str.equals(aVar.a)) {
                    b = aVar.b;
                    break;
                }
            }
            b = 86400;
        }
        return b;
    }

    public int e() {
        return this.a;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public String b(String str) {
        String d;
        synchronized (g) {
            for (int i = 0; i < this.d.size(); i++) {
                a aVar = (a) this.d.get(i);
                if (str.equals(aVar.a)) {
                    d = aVar.d;
                    break;
                }
            }
            d = com.umeng.a.d;
        }
        return d;
    }

    public b h() {
        return this.e;
    }

    public JSONObject i() {
        return this.f;
    }
}
