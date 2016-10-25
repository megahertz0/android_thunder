package com.xunlei.downloadprovider.download.tasklist.list.f;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.download.tasklist.list.f.a.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: RedEnvelopeInfoParser.java
public final class c {
    public static List<a> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            List arrayList;
            JSONArray jSONArray = jSONObject.getJSONArray("redpacket_conditions");
            if (jSONArray != null) {
                arrayList = new ArrayList();
                int i = 0;
                while (i < jSONArray.length()) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        a aVar = new a();
                        aVar.a = jSONObject2.optString("count_down");
                        aVar.b = jSONObject2.optString("detatil_page_image");
                        aVar.c = jSONObject2.optString("detatil_page_subtitle");
                        aVar.d = jSONObject2.optString("detatil_page_title");
                        aVar.e = jSONObject2.optString("list_page_image");
                        aVar.f = jSONObject2.optString("list_page_title");
                        aVar.g = jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("redpack_type");
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            aVar.h.add(jSONArray2.optString(i2));
                        }
                        aVar.i = jSONObject2.optString("task_finish_count");
                        aVar.j = jSONObject2.optString("user_type");
                        arrayList.add(aVar);
                        i++;
                    } catch (Exception e) {
                        Exception e2 = e;
                    }
                }
            } else {
                arrayList = null;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            arrayList = null;
            e2 = exception;
            e2.printStackTrace();
            return r0;
        }
        return r0;
    }
}
