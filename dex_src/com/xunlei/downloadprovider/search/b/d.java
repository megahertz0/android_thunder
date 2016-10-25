package com.xunlei.downloadprovider.search.b;

import android.text.TextUtils;
import com.android.volley.r.b;
import com.xunlei.downloadprovider.search.bean.a;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SearchNetworkHelper.java
public final class d implements b<String> {
    final /* synthetic */ b a;
    final /* synthetic */ c b;

    public d(c cVar, b bVar) {
        this.b = cVar;
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        c.a;
        try {
            Object obj2;
            if (TextUtils.isEmpty(str)) {
                obj2 = null;
            } else {
                obj2 = a.a(new JSONObject(str));
            }
            this.a.a(obj2);
        } catch (JSONException e) {
            e.printStackTrace();
            this.a.a(null);
        }
    }
}
