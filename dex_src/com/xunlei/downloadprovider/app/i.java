package com.xunlei.downloadprovider.app;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.app.BrothersApplication.b;

// compiled from: BrothersApplication.java
final class i implements ActivityLifecycleCallbacks {
    protected long a;
    protected boolean b;
    final /* synthetic */ b c;

    i(b bVar) {
        this.c = bVar;
        this.a = 0;
        this.b = false;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        new StringBuilder("[ActivityLifecycle - onActivityCreated]").append(activity);
    }

    public final void onActivityStarted(Activity activity) {
        synchronized (this) {
            this.a++;
            new StringBuilder("[ActivityLifecycle - onActivityStarted](").append(this.a).append(SocializeConstants.OP_CLOSE_PAREN).append(activity);
        }
    }

    public final void onActivityResumed(Activity activity) {
        new StringBuilder("[ActivityLifecycle - onActivityResumed](").append(this.a).append(SocializeConstants.OP_CLOSE_PAREN).append(activity);
        boolean z = this.b;
        this.b = true;
        this.c.a(true, z, activity);
    }

    public final void onActivityPaused(Activity activity) {
        new StringBuilder("[ActivityLifecycle - onActivityPaused](").append(this.a).append(SocializeConstants.OP_CLOSE_PAREN).append(activity);
    }

    public final void onActivityStopped(Activity activity) {
        synchronized (this) {
            this.a--;
            new StringBuilder("[ActivityLifecycle - onActivityStopped](").append(this.a).append(SocializeConstants.OP_CLOSE_PAREN).append(activity);
        }
        if (!BrothersApplication.c(this.c.b)) {
            boolean z = this.b;
            this.b = false;
            this.c.a(false, z, activity);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        new StringBuilder("[ActivityLifecycle - onActivityDestroyed]").append(activity);
    }
}
