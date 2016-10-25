package com.inmobi.signals;

import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.p.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: CarbWorker.java
public class g {
    private static final String a;
    private a b;
    private boolean c;
    private a d;
    private e e;

    static {
        a = g.class.getSimpleName();
    }

    public g() {
        this.c = false;
        this.d = new a();
        this.e = new e();
    }

    public synchronized void a(a aVar) {
        this.b = aVar;
        if (this.c || !a()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Carb worker did not admit Carb start request.");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "Starting Carb worker");
            this.c = true;
            b();
        }
    }

    private boolean a() {
        long b = this.d.b();
        return b == 0 || System.currentTimeMillis() - b >= ((long) (this.b.d() * 1000));
    }

    private void b() {
        new Thread(new Runnable() {
            public void run() {
                c a = g.this.c();
                if (!a.a()) {
                    g.this.d.a(System.currentTimeMillis());
                    if (!a.e()) {
                        g.this.a(a, g.this.a(a.b()));
                    }
                }
                g.this.c = false;
            }
        }).start();
    }

    private c c() {
        b bVar = new b(this.b.b(), this.b.e(), this.b.f(), o.a().d());
        bVar.a(this.b.h());
        bVar.b(this.b.g() * 1000);
        bVar.c(this.b.g() * 1000);
        return this.e.a(bVar);
    }

    private List<d> a(List<d> list) {
        List<d> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!a(((d) list.get(i)).a())) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    private boolean a(String str) {
        try {
            com.inmobi.commons.a.a.b().getPackageManager().getPackageInfo(str, AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void a(c cVar, List<d> list) {
        f fVar = new f(this.b.c(), this.b.e(), this.b.f(), o.a().d(), list, cVar);
        fVar.b(this.b.g() * 1000);
        fVar.c(this.b.g() * 1000);
        this.e.a(fVar);
    }
}
