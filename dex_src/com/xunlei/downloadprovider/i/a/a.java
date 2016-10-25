package com.xunlei.downloadprovider.i.a;

import android.os.Handler;
import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ReportGrayUpdateHelper.java
public final class a extends com.xunlei.downloadprovider.b.a {
    private Handler b;
    private Object c;

    // compiled from: ReportGrayUpdateHelper.java
    private class a extends f {
        private a() {
        }

        public final Object parseJson(JSONObject jSONObject) throws JSONException {
            new StringBuilder().append(getClass()).append("---parseJson---").append(jSONObject).append(Thread.currentThread().getId());
            return null;
        }
    }

    public a(Handler handler) {
        super(handler, Integer.valueOf(0));
        this.b = handler;
        this.c = Integer.valueOf(0);
    }
}
