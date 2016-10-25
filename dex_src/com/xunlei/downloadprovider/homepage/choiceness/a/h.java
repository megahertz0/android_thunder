package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.member.payment.a.e;
import com.xunlei.downloadprovider.search.b.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ChoicenessNetworkHelper.java
public final class h extends e {
    h() {
    }

    static /* synthetic */ void a(JSONObject jSONObject, b bVar) {
        int i = 0;
        try {
            a a;
            if (g.b == null) {
                g.b = new g();
            }
            g gVar = g.b;
            com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar2 = new com.xunlei.downloadprovider.homepage.choiceness.a.a.b();
            bVar2.a = jSONObject.getString("result");
            String optString = jSONObject.optString("params");
            JSONArray optJSONArray = jSONObject.optJSONArray("on_the_top");
            if (optJSONArray != null) {
                bVar2.b = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    if (jSONObject2 != null) {
                        a = a.a(jSONObject2);
                        if (!gVar.a(a)) {
                            a.a = true;
                            a.I = optString;
                            bVar2.b.add(a);
                        }
                    }
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("item_list");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                List arrayList = new ArrayList();
                while (i < optJSONArray2.length()) {
                    a = a.a(optJSONArray2.getJSONObject(i));
                    if (!gVar.a(a)) {
                        a.I = optString;
                        arrayList.add(a);
                    }
                    i++;
                }
                bVar2.c = arrayList;
            }
            bVar2.d = jSONObject.optBoolean("clear_cache");
            bVar2.e = jSONObject.optLong("ts");
            bVar.a(bVar2);
        } catch (JSONException e) {
            com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar3 = new com.xunlei.downloadprovider.homepage.choiceness.a.a.b();
            bVar3.g = true;
            bVar.a(bVar3);
        }
    }

    public static String a(String str, long j) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append("&length=8");
        stringBuilder.append("&ts=").append(j);
        stringBuilder.append("&mobile_type=android");
        return stringBuilder.toString();
    }
}
