package com.xunlei.downloadprovider.search.ui.hotsite;

import android.os.Handler;
import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: HotSiteHelper.java
public final class a extends com.xunlei.downloadprovider.b.a {
    Handler b;
    private Object c;

    // compiled from: HotSiteHelper.java
    private class a extends f {
        private a() {
        }

        public final Object parseJson(JSONObject jSONObject) throws JSONException {
            new StringBuilder().append(getClass()).append("---parseJson---").append(jSONObject);
            return jSONObject;
        }
    }

    public a(Handler handler) {
        super(handler, Integer.valueOf(0));
        this.b = handler;
        this.c = Integer.valueOf(0);
    }
}
