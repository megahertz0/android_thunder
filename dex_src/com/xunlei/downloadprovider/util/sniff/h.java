package com.xunlei.downloadprovider.util.sniff;

import com.android.volley.r.b;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONObject;

// compiled from: SnifferRules.java
final class h implements b<JSONObject> {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("[athunder_sniff_rules]downloadOnlineConfigure - ").append(jSONObject.toString());
        try {
            File file = new File(this.a.a());
            new StringBuilder("[athunder_sniff_rules]saveConfigureToFile - cache : ").append(file.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jSONObject.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.a(this.a, jSONObject);
    }
}
