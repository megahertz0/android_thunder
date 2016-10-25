package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.b.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.inmobi.commons.core.utilities.e;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// compiled from: NativeStrandAdCache.java
final class r implements a, com.inmobi.commons.core.utilities.a.b, com.inmobi.commons.core.utilities.e.b {
    private static final String a;
    private s b;
    private List<q> c;
    private b d;
    private boolean e;
    private boolean f;
    private final Handler g;
    private int h;
    private boolean i;
    private a j;

    // compiled from: NativeStrandAdCache.java
    static interface a {
        void a();
    }

    // compiled from: NativeStrandAdCache.java
    private enum b {
        STARTED("Started") {

            // compiled from: NativeStrandAdCache.java
            class AnonymousClass_1 implements Runnable {
                final /* synthetic */ r a;

                AnonymousClass_1(r rVar) {
                    this.a = rVar;
                }

                public void run() {
                    this.a.d.e(this.a);
                }
            }

            final void a(r rVar) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Entering state:").append(this).toString());
                rVar.d = a;
                rVar.h = 0;
                e(rVar);
            }

            final void b(r rVar) {
                a.a(rVar);
            }

            final void c(r rVar) {
                c.a(rVar);
            }

            final void d(r rVar) {
                d.a(rVar);
            }

            final void e(r rVar) {
                if (!rVar.i) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Ignoring Ad load as there are no ad positions");
                } else if (rVar.c.size() > 0) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Serving ad already in cache");
                    g(rVar);
                } else if (rVar.b.z()) {
                    rVar.b.o();
                }
            }

            final q f(r rVar) {
                q qVar = null;
                if (rVar.c.size() > 0) {
                    qVar = (q) rVar.c.remove(0);
                }
                a(rVar, 0, false);
                return qVar;
            }

            final void g(r rVar) {
                rVar.h = 0;
                rVar.j.a();
            }

            final void h(r rVar) {
                r.e(rVar);
                if (rVar.h >= 10) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Exhausted retries for ad response; giving up");
                } else {
                    a(rVar, rVar.n(), true);
                }
            }

            final void a(r rVar, boolean z) {
                if (!z) {
                    b.a(rVar);
                    Logger.a(InternalLogLevel.INTERNAL, a, "App went to background; stopping cache replenish handler");
                }
            }

            final void b(r rVar, boolean z) {
                if (!z) {
                    b.a(rVar);
                    Logger.a(InternalLogLevel.INTERNAL, a, "Connectivity lost; stopping cache replenish handler");
                }
            }

            final void a(r rVar, long j, boolean z) {
                if (!z || rVar.h < 10) {
                    rVar.g.postDelayed(new AnonymousClass_1(rVar), j);
                }
            }
        },
        PAUSED("Paused") {
            final void a(r rVar) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Entering state:").append(this).toString());
                rVar.d = b;
                rVar.j();
            }

            final void c(r rVar) {
                c.a(rVar);
            }

            final void d(r rVar) {
                d.a(rVar);
            }

            final void a(r rVar, boolean z) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Activity in focus:").append(z).toString());
                if (z && rVar.e) {
                    a.a(rVar);
                }
            }

            final void b(r rVar, boolean z) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Network available:").append(z).toString());
                if (z && rVar.f) {
                    a.a(rVar);
                }
            }
        },
        STOPPED("Stopped") {
            final void a(r rVar) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Entering state:").append(this).toString());
                rVar.d = c;
                rVar.j();
            }

            final void b(r rVar) {
                if (rVar.f && rVar.e) {
                    a.a(rVar);
                } else {
                    b.a(rVar);
                }
            }

            final void d(r rVar) {
                d.a(rVar);
            }
        },
        DESTROYED("Destroyed") {
            final void a(r rVar) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Entering state:").append(this).toString());
                rVar.d = d;
                rVar.m();
                rVar.j();
                rVar.b.y();
                for (q qVar : rVar.c) {
                    qVar.c();
                }
                rVar.c.clear();
            }
        };
        private String e;

        abstract void a(r rVar);

        static {
            String str = "Started";
            a = new AnonymousClass_1("STARTED", 0, "Started");
            str = "Paused";
            b = new AnonymousClass_2("PAUSED", 1, "Paused");
            str = "Stopped";
            c = new AnonymousClass_3("STOPPED", 2, "Stopped");
            str = "Destroyed";
            d = new AnonymousClass_4("DESTROYED", 3, "Destroyed");
            f = new b[]{a, b, c, d};
        }

        private b(String str) {
            this.e = str;
        }

        void b(r rVar) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Cannot be started from current state:").append(rVar.d.toString()).toString());
        }

        void c(r rVar) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Cannot be stopped from current state:").append(rVar.d.toString()).toString());
        }

        void d(r rVar) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Cannot be destroyed from current state:").append(rVar.d.toString()).toString());
        }

        void e(r rVar) {
        }

        q f(r rVar) {
            return null;
        }

        void g(r rVar) {
        }

        void h(r rVar) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("AdLoad failed not handled for state:").append(rVar.d.toString()).toString());
        }

        void a(r rVar, boolean z) {
        }

        void b(r rVar, boolean z) {
        }

        void a(r rVar, long j, boolean z) {
        }

        public String toString() {
            return this.e;
        }
    }

    static /* synthetic */ int e(r rVar) {
        int i = rVar.h;
        rVar.h = i + 1;
        return i;
    }

    static {
        a = r.class.getSimpleName();
    }

    r(Context context, long j, InMobiClientPositioning inMobiClientPositioning, a aVar) {
        Integer[] numArr;
        this.c = new LinkedList();
        this.h = 0;
        this.i = false;
        int size = inMobiClientPositioning.getFixedPositions().size();
        if (size == 0) {
            numArr = new Integer[0];
            if (inMobiClientPositioning.getRepeatingInterval() != Integer.MAX_VALUE) {
                this.i = true;
            }
        } else {
            this.i = true;
            numArr = new Integer[size];
            inMobiClientPositioning.getFixedPositions().toArray(numArr);
        }
        this.b = new s(context, j, numArr, this);
        this.g = new Handler(context.getMainLooper());
        this.j = aVar;
        this.e = c.a();
        this.f = true;
        b.c.a(this);
        l();
    }

    public final void e() {
        this.d.b(this);
    }

    public final void f() {
        this.d.c(this);
    }

    public final q g() {
        return this.d.f(this);
    }

    public final void h() {
        this.d.d(this);
    }

    public final void c(Map<String, String> map) {
        this.b.a(map);
    }

    public final void a(String str) {
        this.b.a(str);
    }

    public final f i() {
        return this.b.l().l();
    }

    public final void a() {
        q x = this.b.x();
        if (x != null) {
            this.c.add(x);
        }
        this.d.g(this);
    }

    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        this.d.h(this);
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void a(Map<Object, Object> map) {
    }

    public final void d() {
    }

    public final void b(Map<Object, Object> map) {
    }

    public final void a(boolean z) {
        this.f = z;
        this.d.a(this, z);
    }

    public final void b(boolean z) {
        this.e = z;
        this.d.b(this, z);
    }

    private void l() {
        com.inmobi.commons.core.utilities.a.a().a((com.inmobi.commons.core.utilities.a.b) this);
        e.a().a("android.net.conn.CONNECTIVITY_CHANGE", (com.inmobi.commons.core.utilities.e.b) this);
    }

    private void m() {
        com.inmobi.commons.core.utilities.a.a().b((com.inmobi.commons.core.utilities.a.b) this);
        e.a().a((com.inmobi.commons.core.utilities.e.b) this, "android.net.conn.CONNECTIVITY_CHANGE");
    }

    private long n() {
        return 20000;
    }

    final void j() {
        this.g.removeCallbacksAndMessages(null);
        this.h = 0;
    }
}
