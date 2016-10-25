package com.xunlei.downloadprovider.search.bean;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: AssociateResponse.java
public final class a {
    public String a;
    public List<a> b;
    private int c;
    private String d;
    private List<b> e;

    // compiled from: AssociateResponse.java
    public static class a {
        public String a;
        int b;
        String c;
        String d;
        int e;
        public String f;
    }

    // compiled from: AssociateResponse.java
    public static class b {
        String a;
        String b;
        int c;
    }

    public a() {
        this.b = new ArrayList();
        this.e = new ArrayList();
    }

    public static a a(JSONObject jSONObject) throws JSONException {
        int i = 0;
        a aVar = new a();
        aVar.c = jSONObject.getInt("ret");
        aVar.d = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
        aVar.a = jSONObject.getString("key");
        JSONArray jSONArray = jSONObject.getJSONArray("words");
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            a aVar2 = new a();
            aVar2.a = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
            aVar2.b = jSONObject2.getInt("hot");
            aVar2.c = jSONObject2.getString(AgooConstants.MESSAGE_TYPE);
            aVar2.d = jSONObject2.getString("typeName");
            aVar2.e = jSONObject2.getInt("isBaidureci");
            aVar.b.add(aVar2);
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray("thirds");
        int length2 = jSONArray2.length();
        while (i < length2) {
            JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
            b bVar = new b();
            bVar.a = jSONObject3.getString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
            bVar.b = jSONObject3.getString(SHubBatchQueryKeys.url);
            bVar.c = jSONObject3.getInt("isAutoSniffing");
            aVar.e.add(bVar);
            i++;
        }
        return aVar;
    }
}
