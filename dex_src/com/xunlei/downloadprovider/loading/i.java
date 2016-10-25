package com.xunlei.downloadprovider.loading;

import android.os.Handler;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: LoadingImageHelper.java
public final class i extends com.xunlei.downloadprovider.b.a {
    Handler b;
    int c;
    private final String d;

    // compiled from: LoadingImageHelper.java
    private class a extends f {
        private a() {
        }

        public final Object parseJson(JSONObject jSONObject) throws JSONException {
            new StringBuilder().append(getClass()).append("---obj---").append(jSONObject).append("---").append(Thread.currentThread().getId());
            int i = jSONObject.getInt(Impl.COLUMN_STATUS);
            jSONObject.toString();
            if (i == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.JSON_DATA);
                if (jSONObject2 != null) {
                    h hVar = new h();
                    hVar.b = jSONObject2.optString(SocializeConstants.WEIBO_ID);
                    hVar.c = jSONObject2.optString("img_url");
                    hVar.d = jSONObject2.optString("download_url");
                    i.this = jSONObject2.optString("have_img");
                    hVar.e = jSONObject2.optString("start_date");
                    hVar.f = jSONObject2.optString("end_date");
                    hVar.a();
                    hVar.g = jSONObject2.optString("skipwords");
                    hVar.h = jSONObject2.optString("skip");
                    hVar.i = jSONObject2.optString("skiptitle");
                    hVar.j = jSONObject2.optString("skipurl");
                    hVar.k = jSONObject2.optString("display_times");
                    hVar.l = jSONObject2.optString("duration");
                    return hVar;
                }
            }
            return null;
        }
    }

    public i(Handler handler) {
        super(handler, Integer.valueOf(0));
        this.d = getClass().getName();
        this.b = handler;
        this.c = 0;
    }
}
