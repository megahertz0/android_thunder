package com.xunlei.downloadprovider.model.protocol.c;

import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.c.a.a.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: GroupUtil.java
public final class c {
    public static a a(JSONObject jSONObject) throws JSONException {
        a aVar = new a();
        aVar.a = jSONObject.getLong("resourceID");
        aVar.d = jSONObject.getInt("good");
        aVar.c = jSONObject.getInt("play");
        aVar.e = jSONObject.getInt("down");
        aVar.b = jSONObject.getInt("commentNum");
        aVar.f = jSONObject.optInt("is_good");
        JSONArray optJSONArray = jSONObject.optJSONArray("commentInfoList");
        aVar.g = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    a aVar2 = new a();
                    aVar2.a = jSONObject2.getString("userName");
                    aVar2.d = jSONObject2.getString("content");
                    aVar.g.add(aVar2);
                }
            }
        }
        return aVar;
    }

    public static String a() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        if (!LoginHelper.a().f()) {
            i = 0;
        }
        stringBuilder.append("version=").append(b.f(BrothersApplication.a)).append("&productID=").append(b.h()).append("&peerID=").append(b.d()).append("&imeiID=").append(b.f()).append("&partnerID=").append(b.g()).append("&isVip=").append(i).append(new StringBuilder("&screenWidth=").append(b.t()).toString()).append(new StringBuilder("&screenHeight=").append(b.u()).toString()).append("&versionCode=").append(b.x());
        return stringBuilder.toString();
    }
}
