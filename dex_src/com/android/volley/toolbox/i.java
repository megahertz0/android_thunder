package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Request;
import com.android.volley.p;
import com.android.volley.toolbox.i.c;
import com.android.volley.toolbox.i.d;
import com.android.volley.w;
import java.util.HashMap;
import java.util.LinkedList;

// compiled from: ImageLoader.java
public final class i {
    final p a;
    final b b;
    final HashMap<String, a> c;
    final HashMap<String, a> d;
    Runnable e;
    private int f;
    private final Handler g;

    // compiled from: ImageLoader.java
    private class a {
        Bitmap a;
        w b;
        final LinkedList<c> c;
        private final Request<?> e;

        public a(Request<?> request, c cVar) {
            this.c = new LinkedList();
            this.e = request;
            this.c.add(cVar);
        }

        public final boolean a(c cVar) {
            this.c.remove(cVar);
            if (this.c.size() != 0) {
                return false;
            }
            this.e.cancel();
            return true;
        }
    }

    // compiled from: ImageLoader.java
    public static interface b {
        Bitmap a(String str);

        void a(String str, Bitmap bitmap);
    }

    // compiled from: ImageLoader.java
    public class c {
        Bitmap a;
        final d b;
        final String c;
        private final String e;

        public c(Bitmap bitmap, String str, String str2, d dVar) {
            this.a = bitmap;
            this.c = str;
            this.e = str2;
            this.b = dVar;
        }

        public final void a() {
            if (this.b != null) {
                a aVar = (a) i.this.c.get(this.e);
                if (aVar == null) {
                    aVar = (a) i.this.get(this.e);
                    if (aVar != null) {
                        aVar.a(this);
                        if (aVar.c.size() == 0) {
                            i.this.remove(this.e);
                        }
                    }
                } else if (aVar.a(this)) {
                    i.this.c.remove(this.e);
                }
            }
        }
    }

    // compiled from: ImageLoader.java
    public static interface d extends com.android.volley.r.a {
        void a(c cVar, boolean z);
    }

    public i(p pVar, b bVar) {
        this.f = 100;
        this.c = new HashMap();
        this.d = new HashMap();
        this.g = new Handler(Looper.getMainLooper());
        this.a = pVar;
        this.b = bVar;
    }

    final void a(String str, a aVar) {
        this.d.put(str, aVar);
        if (this.e == null) {
            this.e = new l(this);
            this.g.postDelayed(this.e, (long) this.f);
        }
    }
}
