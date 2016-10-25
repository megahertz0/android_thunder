package com.baidu.mobads.h;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.mobads.h.g.c;

class n implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ Handler b;
    final /* synthetic */ g c;

    n(g gVar, c cVar, Handler handler) {
        this.c = gVar;
        this.a = cVar;
        this.b = handler;
    }

    public void run() {
        Editor edit;
        try {
            synchronized (g.class) {
                this.c.b(this.a, this.b);
            }
            Editor edit2 = this.c.j().edit();
            edit2.putString("previousProxyVersion", this.c.a());
            if (VERSION.SDK_INT >= 9) {
                edit2.apply();
            } else {
                edit2.commit();
            }
        } catch (Throwable th) {
            try {
                String toString = new StringBuilder("Load APK Failed: ").append(th.toString()).toString();
                this.c.l.e("XAdApkLoader", toString);
                this.c.b(false);
                edit2 = this.c.j().edit();
                edit2.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit2.apply();
                } else {
                    edit2.commit();
                }
            } catch (Throwable th2) {
                edit = this.c.j().edit();
                edit.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            }
        }
    }
}
