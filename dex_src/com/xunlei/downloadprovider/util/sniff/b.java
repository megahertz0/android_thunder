package com.xunlei.downloadprovider.util.sniff;

import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONObject;

// compiled from: SniffConfigure.java
public final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ SniffConfigure a;

    public b(SniffConfigure sniffConfigure) {
        this.a = sniffConfigure;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        SniffConfigure.h();
        new StringBuilder("downloadOnlineConfigure - ").append(jSONObject.toString());
        try {
            File file = new File(SniffConfigure.a(this.a));
            SniffConfigure.h();
            new StringBuilder("saveConfigureToFile - cache : ").append(file.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jSONObject.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SniffConfigure.a(this.a, jSONObject);
        f.a(true);
    }
}
