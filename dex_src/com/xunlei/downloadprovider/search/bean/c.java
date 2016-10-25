package com.xunlei.downloadprovider.search.bean;

import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: HotWebsite.java
public final class c {
    public String a;
    public String b;
    public String c;
    private String d;

    public static List<c> a(String str) {
        List arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("list");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                c cVar = new c();
                cVar.a = jSONObject.getString("order");
                cVar.b = jSONObject.getString(SetKey.TITLE);
                cVar.d = jSONObject.getString("count");
                cVar.c = jSONObject.getString(SHubBatchQueryKeys.url);
                arrayList.add(cVar);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
