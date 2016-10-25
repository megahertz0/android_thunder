package com.xunlei.downloadprovider.model.protocol.g;

import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XunleiScanCodeResult.java
public class k {
    private static final String l;
    public String a;
    public String b;
    public String c;
    public long d;
    public String e;
    public String f;
    public String g;
    public String h;
    public int i;
    public String j;
    public String k;

    static {
        l = k.class.getSimpleName();
    }

    public static k a(JSONObject jSONObject) throws JSONException {
        k kVar = null;
        if (jSONObject == null) {
            return null;
        }
        k kVar2 = new k();
        try {
            kVar2.a = jSONObject.getString("filename");
            kVar2.b = jSONObject.getString("fileurl");
            kVar2.c = jSONObject.getString("filetype");
            kVar2.f = jSONObject.getString("filecid");
            kVar2.g = jSONObject.getString("filegcid");
            kVar = kVar2.b;
            if (kVar != null) {
                kVar2.b = kVar2.b.trim();
            }
            try {
                kVar2.d = jSONObject.getLong("filesize");
            } catch (Exception e) {
                kVar2.d = -1;
            }
            try {
                kVar2.e = jSONObject.getString("pageurl");
                if (kVar2.e != null) {
                    kVar2.e = kVar2.e.trim();
                }
            } catch (Exception e2) {
            }
            try {
                new StringBuilder("json\u4e2d\u7684type = ").append(jSONObject.getInt("optype"));
                kVar2.i = jSONObject.getInt("optype");
            } catch (Exception e3) {
            }
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("z");
                try {
                    kVar2.j = jSONObject2.getString("c");
                } catch (Exception e4) {
                }
                jSONObject2.getString("b");
                try {
                    kVar2.h = jSONObject2.getString("cookie");
                } catch (Exception e5) {
                }
                new StringBuilder("fid = ").append(kVar2.k);
                new StringBuilder(" check code = ").append(kVar2.j);
                new StringBuilder(" cookie = ").append(kVar2.h);
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            return kVar2;
        } catch (JSONException e7) {
            return kVar;
        }
    }

    public String toString() {
        return new StringBuilder("fileName:").append(this.a).append(" url:").append(this.b).toString();
    }
}
