package com.xunlei.downloadprovider.homepage.a.a;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: VipContinueParser.java
public final class e extends f {
    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        try {
            int i = jSONObject.getInt("result");
            d.b.clear();
            if (i == 200) {
                d.a = jSONObject.getInt("expire_days");
                JSONArray jSONArray = jSONObject.getJSONArray("bubble_list");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    d dVar = new d();
                    if (!TextUtils.isEmpty(jSONObject2.getString("bubbleid"))) {
                        dVar.c = jSONObject2.getString("bubbleid");
                        dVar.d = jSONObject2.getString("text");
                        dVar.e = jSONObject2.getString("button_text");
                        dVar.h = jSONObject2.getString(SocialConstants.PARAM_URL);
                        dVar.f = jSONObject2.getInt("show_time");
                        dVar.g = jSONObject2.getString("style");
                        d.b.put(dVar.g, dVar);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
