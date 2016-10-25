package com.xunlei.downloadprovider.util;

import com.android.volley.r.b;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import org.json.JSONObject;

// compiled from: RedPointOnlineConfigure.java
public final class x implements b<JSONObject> {
    final /* synthetic */ v a;

    public x(v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        String str = v.a;
        new StringBuilder("downloadOnlineConfigure - ").append(jSONObject.toString());
        JSONObject d = this.a.d();
        Map a = v.a(d);
        v.a(this.a, v.a(jSONObject));
        v.a(v.b(this.a), a);
        if (!(v.b(this.a) == null || v.b(this.a).size() <= 0 || v.a(jSONObject, d))) {
            try {
                File file = new File(v.c(this.a));
                String str2 = v.a;
                new StringBuilder("saveConfigureToFile - cache : ").append(file.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jSONObject.toString().getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (v.b(this.a) != null && v.b(this.a).size() > 0) {
            v.f();
            v.a(this.a);
        }
        this.a.e();
    }
}
