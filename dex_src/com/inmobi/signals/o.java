package com.inmobi.signals;

import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.configs.b.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.f;
import com.inmobi.commons.core.utilities.uid.d;

// compiled from: SignalsComponent.java
public class o implements b {
    private static final String a;
    private static final Object b;
    private static volatile o c;
    private i d;
    private g e;
    private p f;
    private boolean g;

    static {
        a = o.class.getSimpleName();
        b = new Object();
    }

    public static o a() {
        o oVar = c;
        if (oVar == null) {
            synchronized (b) {
                oVar = c;
                if (oVar == null) {
                    oVar = new o();
                    c = oVar;
                }
            }
        }
        return oVar;
    }

    private o() {
        this.g = false;
        this.f = new p();
        com.inmobi.commons.core.configs.b.a().a(this.f, (b) this);
        f.a().a(e().i());
        LocationInfo.a().a(e().h());
        a.a().a(this.f.a(), this.f.e());
    }

    public void a(com.inmobi.commons.core.configs.a aVar) {
        this.f = (p) aVar;
        LocationInfo.a().a(e().h());
        f.a().a(e().i());
        a.a().a(this.f.a(), this.f.e());
    }

    public synchronized void b() {
        if (!this.g) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Starting signals component.");
            this.g = true;
            g();
            LocationInfo.a().b();
        }
    }

    public synchronized void c() {
        if (this.g) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Stopping signals component.");
            this.g = false;
            h();
            LocationInfo.a().c();
        }
    }

    d d() {
        return new d(this.f.o().a());
    }

    public p.b e() {
        return this.f.f();
    }

    public p.a f() {
        return this.f.g();
    }

    synchronized void g() {
        if (!this.g) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Ice can not be started as Signals component has not been started.");
        } else if (e().a()) {
            n.a().b();
            if (this.d == null) {
                this.d = new i();
                this.d.a();
            } else {
                this.d.a();
            }
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "User data collection is disabled.");
        }
    }

    void h() {
        n.a().c();
        if (this.d != null) {
            this.d.c();
        }
    }

    public void i() {
        if (!this.g) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Carb can not be started as Signals component has not been started.");
        } else if (!f().a()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Carb is disabled.");
        } else if (this.e == null) {
            this.e = new g();
            this.e.a(f());
        } else {
            this.e.a(f());
        }
    }
}
