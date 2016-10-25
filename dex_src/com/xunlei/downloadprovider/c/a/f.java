package com.xunlei.downloadprovider.c.a;

import android.text.TextUtils;
import com.xunlei.download.proguard.c;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CommentsInfoList.java
public final class f {
    public String a;
    public int b;
    public int c;
    public int d;
    public ArrayList<c> e;

    public static f a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        f fVar = new f();
        fVar.a = jSONObject.getString("tid");
        fVar.c = jSONObject.getInt("rcount");
        JSONArray jSONArray = jSONObject.getJSONArray("comments");
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        fVar.e = arrayList;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            c cVar = new c();
            cVar.a = jSONObject2.getLong(SHubBatchQueryKeys.cid);
            cVar.e = jSONObject2.optString("sourceId");
            cVar.a(jSONObject2.getString("comment"));
            cVar.c = jSONObject2.getLong(AgooConstants.MESSAGE_TIME);
            cVar.j = jSONObject2.getString("userName");
            cVar.i = jSONObject2.getLong(c.f);
            cVar.k = jSONObject2.getString("userImg");
            if (cVar.j == null || !TextUtils.isGraphic(cVar.j.trim())) {
                cVar.j = "\u8fc5\u96f7\u7528\u6237";
            }
            cVar.n = jSONObject2.getLong("gcount");
            cVar.p = jSONObject2.getLong("rcount");
            cVar.o = jSONObject2.optInt("scount", 0);
            cVar.m = jSONObject2.optBoolean("isPraise", false);
            boolean optBoolean = jSONObject2.optBoolean("isPdRiew", false);
            cVar.l = optBoolean;
            if (optBoolean) {
                i++;
            }
            JSONArray optJSONArray = jSONObject2.optJSONArray("replys");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList2 = new ArrayList(1);
                cVar.q = arrayList2;
                JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                if (optJSONObject != null) {
                    n nVar = new n();
                    nVar.a = optJSONObject.getLong(SHubBatchQueryKeys.cid);
                    nVar.a(optJSONObject.getString("content"));
                    nVar.c = optJSONObject.getLong(c.f);
                    nVar.d = optJSONObject.getString("user");
                    nVar.e = optJSONObject.getString("userImg");
                    if (nVar.d == null || !TextUtils.isGraphic(nVar.d.trim())) {
                        nVar.d = "\u8fc5\u96f7\u7528\u6237";
                    }
                    arrayList2.add(nVar);
                }
            }
            cVar.d = jSONObject2.optString("device");
            cVar.g = jSONObject2.optString("po");
            cVar.h = jSONObject2.optString("ci");
            arrayList.add(cVar);
        }
        fVar.d = i;
        return fVar;
    }
}
