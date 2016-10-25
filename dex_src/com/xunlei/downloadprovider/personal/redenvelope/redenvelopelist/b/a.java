package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b;

import com.android.volley.p;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: RedEnvelopesManager.java
public final class a {
    public static a a;
    public final p b;
    String c;

    // compiled from: RedEnvelopesManager.java
    public static interface a {
        void a(List<d> list);
    }

    public a() {
        this.b = com.xunlei.downloadprovider.j.a.a().e();
    }

    static List<d> a(JSONArray jSONArray) {
        List<d> arrayList = new ArrayList();
        new StringBuilder("jsonArray.length() == ").append(jSONArray.length());
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                d dVar;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    dVar = null;
                } else {
                    dVar = new d();
                    int optInt = jSONObject.optInt(SocializeConstants.WEIBO_ID);
                    String optString = jSONObject.optString("gift_title");
                    int optInt2 = jSONObject.optInt("gift_left_time");
                    String optString2 = jSONObject.optString("gift_poster");
                    int optInt3 = jSONObject.optInt("gift_status");
                    dVar.a = optInt;
                    dVar.b = optString;
                    dVar.c = optInt2;
                    dVar.d = optString2;
                    dVar.e = optInt3;
                }
                arrayList.add(dVar);
                new StringBuilder("redEnvelopesModel == ").append(dVar.toString());
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return arrayList;
    }
}
