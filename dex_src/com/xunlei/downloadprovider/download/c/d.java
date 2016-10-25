package com.xunlei.downloadprovider.download.c;

import com.android.volley.r.b;
import org.json.JSONArray;

// compiled from: SpeedLimitManager.java
final class d implements b<JSONArray> {
    final /* synthetic */ c$a a;
    final /* synthetic */ c b;

    d(c cVar, c$a com_xunlei_downloadprovider_download_c_c_a) {
        this.b = cVar;
        this.a = com_xunlei_downloadprovider_download_c_c_a;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONArray jSONArray = (JSONArray) obj;
        if (jSONArray != null) {
            this.a.a(g.a(jSONArray));
        }
    }
}
