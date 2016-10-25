package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import com.inmobi.ads.b.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;
import java.util.WeakHashMap;

// compiled from: NativeStrandImpressionTracker.java
class ab implements ActivityLifecycleCallbacks {
    private static final String a;
    private static final a d;
    private static final a e;
    private final Map<Context, n> b;
    private boolean c;

    ab() {
        this.b = new WeakHashMap();
    }

    static {
        a = ab.class.getSimpleName();
        d = new a() {
            public final void a(View view, Object obj) {
                ((q) obj).c(view);
            }
        };
        e = new a() {
            private final Rect a;

            {
                this.a = new Rect();
            }

            public final boolean a(View view, View view2, int i, Object obj) {
                if (!(obj instanceof q)) {
                    return false;
                }
                q qVar = (q) obj;
                if (view2 == null || !view2.isShown() || view.getParent() == null || !view2.getGlobalVisibleRect(this.a)) {
                    return false;
                }
                long e = ((long) qVar.a().e()) * ((long) qVar.a().f());
                return e > 0 && (((long) this.a.height()) * ((long) this.a.width())) * 100 >= e * ((long) i);
            }
        };
    }

    void a(Activity activity, f fVar, View view, q qVar) {
        a(activity, fVar).a(view, qVar, fVar);
    }

    void a(Activity activity, q qVar) {
        n nVar = (n) this.b.get(activity);
        if (nVar != null) {
            nVar.a((Object) qVar);
            if (!nVar.d()) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Impression tracker is free, removing it");
                a(activity);
            }
        }
    }

    private n a(Activity activity, f fVar) {
        n nVar = (n) this.b.get(activity);
        if (nVar == null) {
            nVar = new n(fVar, new m(e, activity), d);
            this.b.put(activity, nVar);
            if (!this.c) {
                activity.getApplication().registerActivityLifecycleCallbacks(this);
                this.c = true;
            }
        }
        return nVar;
    }

    private void a(Activity activity) {
        n nVar = (n) this.b.remove(activity);
        if (nVar != null) {
            nVar.e();
        }
        if (this.b.isEmpty() && this.c) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            this.c = false;
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        n nVar = (n) this.b.get(activity);
        if (nVar != null) {
            nVar.b();
        }
    }

    public void onActivityPaused(Activity activity) {
        n nVar = (n) this.b.get(activity);
        if (nVar != null) {
            nVar.a();
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Activity destroyed, removing impression tracker");
        a(activity);
    }
}
