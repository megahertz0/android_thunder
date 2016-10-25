package anet.channel.util;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.SessionCenter;

// compiled from: Taobao
final class c implements ComponentCallbacks2 {
    public static final String TAG = "awcn.ComponentCallbacks2";

    c() {
    }

    public final void onTrimMemory(int i) {
        ALog.i(TAG, "onTrimMemory", null, "level", Integer.valueOf(i));
        if (i == 20 && !GlobalAppRuntimeInfo.isBackground) {
            SessionCenter.getInstance().background();
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }
}
