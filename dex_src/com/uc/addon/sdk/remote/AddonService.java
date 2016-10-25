package com.uc.addon.sdk.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.SparseArray;
import com.uc.addon.sdk.remote.protocol.IAddon.Stub;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

public final class AddonService extends Service {
    private static ArrayList b;
    private SparseArray a;
    private Handler c;
    private Stub d;

    class AnonymousClass_2 implements Runnable {
        private /* synthetic */ AddonState a;
        private /* synthetic */ Browser b;

        AnonymousClass_2(AddonState addonState, Browser browser) {
            this.a = addonState;
            this.b = browser;
        }

        public void run() {
            synchronized (b) {
                Iterator it = b.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (weakReference != null) {
                        AddonStateListener addonStateListener = (AddonStateListener) weakReference.get();
                        if (addonStateListener != null) {
                            switch (AnonymousClass_3.a[this.a.ordinal()]) {
                                case SpdyAgent.ACCS_ONLINE_SERVER:
                                    addonStateListener.onBind(this.b);
                                    break;
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                    addonStateListener.onUnbind(this.b);
                                    break;
                                case XZBDevice.DOWNLOAD_LIST_FAILED:
                                    addonStateListener.onAllUnbind();
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }

    /* synthetic */ class AnonymousClass_3 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AddonState.values().length];
            try {
                a[AddonState.BIND.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AddonState.UNBIND.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AddonState.UNBIND_ALL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    enum AddonState {
        BIND,
        UNBIND,
        UNBIND_ALL;

        static {
            BIND = new AddonState("BIND", 0);
            UNBIND = new AddonState("UNBIND", 1);
            UNBIND_ALL = new AddonState("UNBIND_ALL", 2);
            a = new AddonState[]{BIND, UNBIND, UNBIND_ALL};
        }
    }

    class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
        UncaughtExceptionHandler() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            new StringBuilder("Uncaught exception met from thread ").append(thread.toString());
            for (int i = 0; i < AddonService.this.size(); i++) {
                Browser browser = (Browser) AddonService.this.get(i);
                if (browser.stat != null) {
                    browser.stat.addMgrStat(AddonService.this.getPackageName(), StatKeys.KEY_CRASH);
                }
            }
        }
    }

    static {
        b = new ArrayList();
    }

    public AddonService() {
        this.a = new SparseArray();
        this.c = new Handler();
        this.d = new Stub() {
            private synchronized Browser a() {
                Browser b;
                b = b();
                if (b == null) {
                    int callingPid = Binder.getCallingPid();
                    if (!(callingPid == 0 || callingPid == Process.myPid())) {
                        b = new Browser();
                        AddonService.this.put(callingPid, b);
                    }
                }
                return b;
            }

            private synchronized Browser b() {
                return (Browser) AddonService.this.get(Binder.getCallingPid());
            }

            public void onConnected(IApp iApp) throws RemoteException {
                Browser a = a();
                if (a != null) {
                    a.a(iApp, AddonService.this, AddonService.this.c);
                    AddonService.this.a(a, AddonState.BIND);
                }
            }

            public void onDisConnected() throws RemoteException {
                Browser b = b();
                AddonService.this.remove(Binder.getCallingPid());
                AddonService.this.a(b, AddonState.UNBIND);
            }

            public void request(String str, Bundle bundle, IValueCallback iValueCallback) throws RemoteException {
                Browser b = b();
                if (b != null) {
                    b.a(str, bundle, iValueCallback);
                }
            }
        };
    }

    private static WeakReference a(AddonStateListener addonStateListener) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference != null) {
                AddonStateListener addonStateListener2 = (AddonStateListener) weakReference.get();
                if (addonStateListener2 == null || addonStateListener2 == addonStateListener) {
                    return weakReference;
                }
            }
        }
        return null;
    }

    private void a(Browser browser, AddonState addonState) {
        this.c.post(new AnonymousClass_2(addonState, browser));
    }

    public static boolean registerStateListener(AddonStateListener addonStateListener) {
        if (addonStateListener == null) {
            return false;
        }
        synchronized (b) {
            if (a(addonStateListener) == null) {
                b.add(new WeakReference(addonStateListener));
            }
        }
        return true;
    }

    public static void unRegisterStateListener(AddonStateListener addonStateListener) {
        if (addonStateListener != null) {
            synchronized (b) {
                WeakReference a = a(addonStateListener);
                if (a == null) {
                    return;
                }
                b.remove(a);
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        return this.d;
    }

    public final void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    }

    public final boolean onUnbind(Intent intent) {
        a(null, AddonState.UNBIND_ALL);
        return super.onUnbind(intent);
    }
}
