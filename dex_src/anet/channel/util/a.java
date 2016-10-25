package anet.channel.util;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.os.Build.VERSION;
import anet.channel.GlobalAppRuntimeInfo;

// compiled from: Taobao
public class a {
    private static ActivityLifecycleCallbacks a;
    private static ComponentCallbacks2 b;

    public static void a() {
        if (VERSION.SDK_INT >= 14) {
            ((Application) GlobalAppRuntimeInfo.getContext().getApplicationContext()).registerActivityLifecycleCallbacks(a);
            GlobalAppRuntimeInfo.getContext().registerComponentCallbacks(b);
        }
    }

    static {
        a = new b();
        b = new c();
    }
}
