package com.xiaomi.push.log;

import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.ag;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

class b$c extends b$b {
    String a;
    String b;
    File c;
    int d;
    boolean e;
    boolean f;
    final /* synthetic */ b g;

    b$c(b bVar, String str, String str2, File file, boolean z) {
        this.g = bVar;
        super(bVar);
        this.a = str;
        this.b = str2;
        this.c = file;
        this.f = z;
    }

    private boolean f() {
        SharedPreferences sharedPreferences = b.a(this.g).getSharedPreferences("log.timestamp", 0);
        String string = sharedPreferences.getString("log.requst", BuildConfig.VERSION_NAME);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject(string);
            currentTimeMillis = jSONObject.getLong(AgooConstants.MESSAGE_TIME);
            int i = jSONObject.getInt("times");
        } catch (JSONException e) {
            i = 0;
        }
        if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
            currentTimeMillis = System.currentTimeMillis();
            i = 0;
        } else if (i > 10) {
            return false;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(AgooConstants.MESSAGE_TIME, currentTimeMillis);
            jSONObject2.put("times", i + 1);
            sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
        } catch (JSONException e2) {
            b.c(new StringBuilder("JSONException on put ").append(e2.getMessage()).toString());
        }
        return true;
    }

    public void b() {
        try {
            if (f()) {
                Map hashMap = new HashMap();
                hashMap.put(c.f, ag.e());
                hashMap.put("token", this.b);
                hashMap.put("net", d.f(b.a(this.g)));
                d.a(this.a, hashMap, this.c, "file");
            }
            this.e = true;
        } catch (IOException e) {
        }
    }

    public void c() {
        if (!this.e) {
            this.d++;
            if (this.d < 3) {
                b.b(this.g).add(this);
            }
        }
        if (this.e || this.d >= 3) {
            this.c.delete();
        }
        b.a(this.g, (long) ((1 << this.d) * 1000));
    }

    public boolean d() {
        return d.e(b.a(this.g)) || (this.f && d.d(b.a(this.g)));
    }
}
