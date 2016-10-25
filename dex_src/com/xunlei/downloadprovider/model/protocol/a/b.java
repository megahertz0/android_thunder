package com.xunlei.downloadprovider.model.protocol.a;

import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: GetDefinitionHelper.java
public final class b {
    public static List<a> a(JSONObject jSONObject, String str) {
        List<a> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    a aVar = new a();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    aVar.a = optJSONObject.getInt("width");
                    aVar.b = optJSONObject.getInt("height");
                    aVar.c = optJSONObject.getString(SHubBatchQueryKeys.url);
                    aVar.d = optJSONObject.getString("defName");
                    aVar.e = optJSONObject.getString("defType");
                    aVar.f = optJSONObject.optString(SHubBatchQueryKeys.format);
                    arrayList.add(aVar);
                }
                return arrayList;
            }
        } catch (Exception e) {
            new StringBuilder("parseDefinitionList error=").append(e.getMessage());
        }
        return null;
    }
}
