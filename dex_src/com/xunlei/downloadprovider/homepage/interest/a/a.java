package com.xunlei.downloadprovider.homepage.interest.a;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.member.payment.a.e;
import com.xunlei.downloadprovider.search.b.b;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: InterestNetworkHelper.java
public final class a extends e {
    public final void a(long j, b<i> bVar) {
        String str = "http://api-shoulei-ssl.xunlei.com/homepage/api/interest_tag";
        if (j != 0) {
            str = new StringBuilder("http://api-shoulei-ssl.xunlei.com/homepage/api/interest_tag?user_id=").append(j).toString();
        }
        a(new com.xunlei.downloadprovidercommon.b.a.a(str, new b(this, bVar), new c(this), (byte) 0));
    }

    public final void a(long j, List<Integer> list, b<Boolean> bVar) {
        try {
            a(new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/homepage/api/interest_tag", a(j, (List) list), new d(this, bVar), new e(this, bVar)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject a(long j, List<Integer> list) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (j != 0) {
            jSONObject.put(SocializeConstants.TENCENT_UID, String.valueOf(j));
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jSONArray.put(i, list.get(i));
        }
        jSONObject.put("interest_tag_list", jSONArray);
        return jSONObject;
    }
}
