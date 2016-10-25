package anet.channel.util;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.SessionCenter;

// compiled from: Taobao
final class b implements ActivityLifecycleCallbacks {
    b() {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        if (GlobalAppRuntimeInfo.isBackground) {
            SessionCenter.getInstance().forground();
        }
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }
}
