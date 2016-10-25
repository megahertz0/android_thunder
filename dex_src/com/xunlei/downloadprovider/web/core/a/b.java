package com.xunlei.downloadprovider.web.core.a;

import android.text.TextUtils;
import com.android.volley.toolbox.o;
import com.xunlei.downloadprovider.j.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: AdBlockHelper.java
public final class b {
    private static b b;
    a a;

    private b() {
        this.a = null;
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    public final void b() {
        if (this.a == null) {
            d();
        }
    }

    public final a c() {
        if (this.a != null) {
            return this.a;
        }
        d();
        return null;
    }

    public static a a(JSONObject jSONObject) {
        a aVar = new a();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("sites");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String str = (String) jSONObject2.names().get(0);
                JSONArray jSONArray2 = jSONObject2.getJSONArray(str);
                List arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList.add((String) jSONArray2.get(i2));
                }
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                    aVar.a.put(str, arrayList);
                }
            }
            return aVar;
        } catch (Exception e) {
            return null;
        }
    }

    private void d() {
        a.b().a(new o(0, "http://m.sjzhushou.com/xlconfig/adblock.txt", null, new c(this), new d(this)));
    }
}
