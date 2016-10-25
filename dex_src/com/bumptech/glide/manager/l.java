package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.bumptech.glide.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: RequestManagerRetriever.java
public final class l implements Callback {
    private static final l c;
    final Map<FragmentManager, k> a;
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> b;
    private volatile g d;
    private final Handler e;

    static {
        c = new l();
    }

    public static l a() {
        return c;
    }

    l() {
        this.a = new HashMap();
        this.b = new HashMap();
        this.e = new Handler(Looper.getMainLooper(), this);
    }

    public final g a(Context context) {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new g(context.getApplicationContext(), new b(), new g());
                }
            }
        }
        return this.d;
    }

    @TargetApi(17)
    public static void a(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    public final k a(FragmentManager fragmentManager) {
        k kVar = (k) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (kVar != null) {
            return kVar;
        }
        kVar = (k) this.a.get(fragmentManager);
        if (kVar != null) {
            return kVar;
        }
        Fragment kVar2 = new k();
        this.a.put(fragmentManager, kVar2);
        fragmentManager.beginTransaction().add(kVar2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(1, fragmentManager).sendToTarget();
        return kVar2;
    }

    public final SupportRequestManagerFragment a(android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        supportRequestManagerFragment = (SupportRequestManagerFragment) this.b.get(fragmentManager);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        android.support.v4.app.Fragment supportRequestManagerFragment2 = new SupportRequestManagerFragment();
        this.b.put(fragmentManager, supportRequestManagerFragment2);
        fragmentManager.beginTransaction().add(supportRequestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, fragmentManager).sendToTarget();
        return supportRequestManagerFragment2;
    }

    public final boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                FragmentManager fragmentManager = (FragmentManager) message.obj;
                remove = this.a.remove(fragmentManager);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                android.support.v4.app.FragmentManager fragmentManager2 = (android.support.v4.app.FragmentManager) message.obj;
                remove = this.b.remove(fragmentManager2);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && r1 == null && Log.isLoggable("RMRetriever", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
            new StringBuilder("Failed to remove expected request manager fragment, manager: ").append(obj);
        }
        return z;
    }
}
