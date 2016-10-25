package com.xunlei.downloadprovider.homepage.recommend.feed;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FeedPullUpVideoModel.java
public final class ai {
    List<ao> a;
    long b;
    long c;
    String d;

    public ai() {
        this.b = 0;
        this.c = 0;
        this.a = new ArrayList();
    }

    public static ai a(JSONObject jSONObject) {
        ai aiVar;
        try {
            String string = jSONObject.getString("result");
            aiVar = new ai();
            try {
                aiVar.d = string;
                JSONObject jSONObject2 = jSONObject.getJSONObject("time_cursor");
                if (jSONObject2 != null) {
                    long j = jSONObject2.getLong("t1");
                    long j2 = jSONObject2.getLong("t2");
                    aiVar.b = j;
                    aiVar.c = j2;
                }
                string = jSONObject.optString("params");
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    ao a = ao.a(jSONArray.getJSONObject(i));
                    if (a != null) {
                        a.x = string;
                        aiVar.a.add(a);
                    }
                }
            } catch (JSONException e) {
                JSONException e2 = e;
                e2.printStackTrace();
                return aiVar;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            aiVar = null;
            e2 = jSONException;
            e2.printStackTrace();
            return aiVar;
        }
        return aiVar;
    }
}
