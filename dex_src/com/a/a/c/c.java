package com.a.a.c;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import com.a.a.a.a;
import com.android.volley.p;
import com.android.volley.toolbox.u;

// compiled from: XVolley.java
public final class c {
    private static c b;
    private p a;
    private Context c;

    private c(Context context) {
        this.c = context.getApplicationContext();
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c(context);
            }
            cVar = b;
        }
        return cVar;
    }

    public final p a() {
        if (this.a == null) {
            try {
                this.a = u.a(this.c, new a(AndroidHttpClient.newInstance("volley/0")));
            } catch (Exception e) {
                try {
                    this.a = u.a(this.c, null);
                } catch (Exception e2) {
                }
            }
        }
        return this.a;
    }
}
