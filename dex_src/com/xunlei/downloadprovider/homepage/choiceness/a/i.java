package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: ChoicenessNetworkHelper.java
public final class i implements b<JSONObject> {
    final /* synthetic */ com.xunlei.downloadprovider.search.b.b a;
    final /* synthetic */ h b;

    public i(h hVar, com.xunlei.downloadprovider.search.b.b bVar) {
        this.b = hVar;
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        h.a((JSONObject) obj, this.a);
    }
}
