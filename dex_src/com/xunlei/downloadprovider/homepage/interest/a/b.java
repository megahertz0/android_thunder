package com.xunlei.downloadprovider.homepage.interest.a;

import com.umeng.common.inter.ITagManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: InterestNetworkHelper.java
final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ com.xunlei.downloadprovider.search.b.b a;
    final /* synthetic */ a b;

    b(a aVar, com.xunlei.downloadprovider.search.b.b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        boolean z = true;
        JSONObject jSONObject = (JSONObject) obj;
        try {
            Object iVar;
            com.xunlei.downloadprovider.search.b.b bVar = this.a;
            if (ITagManager.SUCCESS.equalsIgnoreCase(jSONObject.getString("result"))) {
                iVar = new i();
                JSONArray jSONArray = jSONObject.getJSONArray("interest_tag_list");
                List arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
                }
                iVar.a = arrayList;
                if (jSONObject.getInt("is_show") != 1) {
                    z = false;
                }
                iVar.b = z;
            } else {
                iVar = null;
            }
            bVar.a(iVar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
