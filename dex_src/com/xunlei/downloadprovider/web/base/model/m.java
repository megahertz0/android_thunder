package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ShortMovieDetailDataLoader.java
final class m implements b<JSONObject> {
    final /* synthetic */ d a;

    m(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("response=>").append(jSONObject.toString());
        List arrayList = new ArrayList();
        if (jSONObject != null) {
            CharSequence optString = jSONObject.optString("result");
            String optString2 = jSONObject.optString("s_ab", BuildConfig.VERSION_NAME);
            String optString3 = jSONObject.optString("sversion", BuildConfig.VERSION_NAME);
            JSONObject optJSONObject = jSONObject.optJSONObject("params");
            String toString = optJSONObject == null ? BuildConfig.VERSION_NAME : optJSONObject.toString();
            if (ITagManager.SUCCESS.contentEquals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("video_list");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        u a = u.a(optJSONArray.optJSONObject(i));
                        a.p = optString2;
                        a.t = toString;
                        a.q = optString3;
                        if (a != null) {
                            arrayList.add(a);
                        }
                    }
                }
            }
        }
        this.a.e.a(arrayList);
    }
}
