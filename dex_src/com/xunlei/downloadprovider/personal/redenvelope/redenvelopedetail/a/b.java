package com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a;

import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a.a.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: RedDetailManager.java
public final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public b(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null) {
            d dVar;
            if (jSONObject == null) {
                dVar = null;
            } else {
                d dVar2 = new d();
                String optString = jSONObject.optString("result");
                JSONObject optJSONObject = jSONObject.optJSONObject("info");
                if (optJSONObject == null) {
                    dVar = null;
                } else {
                    int optInt = optJSONObject.optInt(SocializeConstants.WEIBO_ID);
                    String optString2 = optJSONObject.optString("gift_title");
                    int optInt2 = optJSONObject.optInt("gift_left_time");
                    String optString3 = optJSONObject.optString("gift_poster");
                    int optInt3 = optJSONObject.optInt("gift_status");
                    long optLong = optJSONObject.optLong("gift_rec_time");
                    String optString4 = optJSONObject.optString("gift_status_poster");
                    int optInt4 = optJSONObject.optInt("gift_type");
                    String optString5 = optJSONObject.optString("gift_description");
                    long optLong2 = optJSONObject.optLong("gift_exchange_start_time");
                    long optLong3 = optJSONObject.optLong("gift_exchange_end_time");
                    String optString6 = optJSONObject.optString("gift_usage");
                    long optLong4 = jSONObject.optLong("game_size");
                    String optString7 = optJSONObject.optString(SocialConstants.PARAM_URL);
                    String optString8 = jSONObject.optString(Constants.KEY_HTTP_CODE);
                    dVar2.a = optString;
                    dVar2.b = optInt;
                    dVar2.c = optString2;
                    dVar2.d = optInt2;
                    dVar2.e = optString3;
                    dVar2.f = optInt3;
                    dVar2.e = optString3;
                    dVar2.g = optLong;
                    dVar2.h = optString4;
                    dVar2.i = optInt4;
                    dVar2.j = optString5;
                    dVar2.k = optLong2;
                    dVar2.l = optLong3;
                    dVar2.m = optString6;
                    dVar2.n = optLong4;
                    dVar2.o = optString7;
                    dVar2.p = optString8;
                    JSONArray optJSONArray = jSONObject.optJSONArray("support");
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(Integer.valueOf(optJSONArray.optInt(i)));
                    }
                    dVar2.q = arrayList;
                    dVar = dVar2;
                }
            }
            this.a.a(dVar);
        }
    }
}
