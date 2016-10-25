package com.tencent.wxop.stat.common;

import android.content.Context;
import com.tencent.wxop.stat.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class b {
    static d a;
    private static StatLogger d;
    private static JSONObject e;
    Integer b;
    String c;

    static {
        d = k.b();
        e = new JSONObject();
    }

    public b(Context context) {
        this.b = null;
        this.c = null;
        try {
            a(context);
            this.b = k.m(context.getApplicationContext());
            this.c = a.a(context).b();
        } catch (Throwable th) {
            d.e(th);
        }
    }

    static synchronized d a(Context context) {
        d dVar;
        synchronized (b.class) {
            if (a == null) {
                a = new d(context.getApplicationContext(), null);
            }
            dVar = a;
        }
        return dVar;
    }

    public static void a(Context context, Map<String, String> map) {
        if (map != null) {
            for (Entry entry : new HashMap(map).entrySet()) {
                e.put((String) entry.getKey(), entry.getValue());
            }
        }
    }

    public void a(JSONObject jSONObject, Thread thread) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (a != null) {
                a.a(jSONObject2, thread);
            }
            q.a(jSONObject2, "cn", this.c);
            if (this.b != null) {
                jSONObject2.put("tn", this.b);
            }
            if (thread == null) {
                jSONObject.put("ev", jSONObject2);
            } else {
                jSONObject.put("errkv", jSONObject2.toString());
            }
            if (e != null && e.length() > 0) {
                jSONObject.put("eva", e);
            }
        } catch (Throwable th) {
            d.e(th);
        }
    }
}
