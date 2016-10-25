package com.xunlei.downloadprovider.homepage.recommend.feed;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FeedPullDownVideoModel.java
public final class af {
    List<ao> a;
    long b;
    long c;
    long d;
    long e;
    long f;
    boolean g;
    String h;

    public af() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.a = new ArrayList();
    }

    public static af a(JSONObject jSONObject) {
        af afVar;
        try {
            afVar = new af();
            try {
                afVar.h = jSONObject.getString("result");
                afVar.f = jSONObject.getLong("ts");
                afVar.g = jSONObject.getBoolean("clear_cache");
                JSONObject jSONObject2 = jSONObject.getJSONObject("time_cursor_refresh");
                if (jSONObject2 != null) {
                    long j = jSONObject2.getLong("t1");
                    long j2 = jSONObject2.getLong("t2");
                    afVar.b = j;
                    afVar.c = j2;
                }
                JSONObject jSONObject3 = jSONObject.getJSONObject("time_cursor_nextpage");
                if (jSONObject2 != null) {
                    j2 = jSONObject3.getLong("t1");
                    j = jSONObject3.getLong("t2");
                    afVar.d = j2;
                    afVar.e = j;
                }
                String optString = jSONObject.optString("params");
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    ao a = ao.a(jSONArray.getJSONObject(i));
                    if (a != null) {
                        a.x = optString;
                        afVar.a.add(a);
                    }
                }
            } catch (JSONException e) {
                JSONException e2 = e;
                e2.printStackTrace();
                return afVar;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            afVar = null;
            e2 = jSONException;
            e2.printStackTrace();
            return afVar;
        }
        return afVar;
    }
}
