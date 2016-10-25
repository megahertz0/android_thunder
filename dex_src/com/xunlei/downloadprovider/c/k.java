package com.xunlei.downloadprovider.c;

import com.android.volley.r.b;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CommentManager.java
public final class k implements b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public k(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        a.a;
        new StringBuilder("send comment response=>").append(jSONObject);
        long j = -1;
        try {
            int i = jSONObject.getInt("result");
            j = jSONObject.optLong(SHubBatchQueryKeys.cid);
        } catch (JSONException e) {
            JSONException jSONException = e;
            a.a;
            new StringBuilder("send comment error=>").append(jSONException.getMessage());
            i = jSONObject.optInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            jSONException.printStackTrace();
        }
        if (this.a == null) {
            return;
        }
        if (i == 0) {
            this.a.a(Long.valueOf(j));
            return;
        }
        a.b bVar = new a.b();
        bVar.a = i;
        bVar.b = "failed";
        this.a.a(bVar);
    }
}
