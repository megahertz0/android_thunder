package com.inmobi.commons.core.utilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.a.b;
import com.taobao.accs.common.Constants;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: ApplicationFocusChangeObserver.java
public class a {
    private static final String a;
    private static List<b> b;
    private static Object c;
    private static boolean d;
    private static HandlerThread e;
    private static final Object f;
    private static volatile a g;

    // compiled from: ApplicationFocusChangeObserver.java
    public static interface b {
        void a(boolean z);
    }

    // compiled from: ApplicationFocusChangeObserver.java
    final class AnonymousClass_2 implements Runnable {
        final /* synthetic */ boolean a;

        AnonymousClass_2(boolean z) {
            this.a = z;
        }

        public final void run() {
            for (b bVar : b) {
                bVar.a(this.a);
            }
        }
    }

    // compiled from: ApplicationFocusChangeObserver.java
    static class a extends Handler {
        boolean a;

        public a(Looper looper) {
            super(looper);
            this.a = true;
        }

        public void handleMessage(Message message) {
            if (!d) {
                if (message.what == 1001 && this.a) {
                    this.a = false;
                    a.b(false);
                    Logger.a(InternalLogLevel.INTERNAL, a, "App has gone to background.");
                } else if (message.what == 1002 && !this.a) {
                    this.a = true;
                    a.b(true);
                    Logger.a(InternalLogLevel.INTERNAL, a, "App has come to foreground.");
                }
            }
        }
    }

    static {
        a = a.class.getSimpleName();
        b = new ArrayList();
        d = false;
        e = null;
        f = new Object();
    }

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    @TargetApi(14)
    private void h() {
        HandlerThread handlerThread = new HandlerThread("ApplicationFocusChangeObserverHandler");
        e = handlerThread;
        handlerThread.start();
        Class[] declaredClasses = Application.class.getDeclaredClasses();
        Class cls = null;
        int length = declaredClasses.length;
        int i = 0;
        while (i < length) {
            Class cls2 = declaredClasses[i];
            if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                new Class[1][0] = cls2;
            } else {
                cls2 = cls;
            }
            i++;
            cls = cls2;
        }
        c = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final Handler b;

            {
                this.b = new com.inmobi.commons.core.utilities.a.a(e.getLooper());
            }

            public void a(Activity activity) {
                this.b.sendEmptyMessageDelayed(IHost.HOST_NOFITY_PAGE_SELECTED, TabsImpl.SYNC_TIME_OUT);
            }

            public void b(Activity activity) {
                this.b.removeMessages(IHost.HOST_NOFITY_PAGE_SELECTED);
                this.b.sendEmptyMessage(IHost.HOST_NOFITY_PAGE_DESELECTED);
            }

            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (objArr != null) {
                    if (method.getName().equals("onActivityPaused")) {
                        a((Activity) objArr[0]);
                    } else if (method.getName().equals("onActivityResumed")) {
                        b((Activity) objArr[0]);
                    }
                }
                return null;
            }
        });
        Application application = (Application) com.inmobi.commons.a.a.b();
        if (c != null) {
            try {
                Application.class.getMethod("registerActivityLifecycleCallbacks", new Class[]{cls}).invoke(application, new Object[]{c});
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error while registering activity life cycle listener.", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error while registering activity life cycle listener.", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error while registering activity life cycle listener.", e22);
            } catch (NullPointerException e3) {
                Map hashMap = new HashMap();
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "NullPointerException");
                hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e3.getMessage());
                com.inmobi.commons.core.c.a.a().a("root", "ExceptionCaught", hashMap);
            }
        }
    }

    @TargetApi(14)
    private void i() {
        try {
            Application application = (Application) com.inmobi.commons.a.a.b();
            if (c != null) {
                Application.class.getMethod("unregisterActivityLifecycleCallbacks", null).invoke(application, (Object[]) c);
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while unregistering activity life cycle listener.", e);
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while unregistering activity life cycle listener.", e2);
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while unregistering activity life cycle listener.", e22);
        } catch (NullPointerException e3) {
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "NullPointerException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e3.getMessage());
            com.inmobi.commons.core.c.a.a().a("root", "ExceptionCaught", hashMap);
        }
        e.stop();
        e = null;
    }

    public void a(b bVar) {
        if (VERSION.SDK_INT >= 14) {
            b.add(bVar);
            if (b.size() == 1) {
                h();
            }
        }
    }

    public void b(b bVar) {
        if (VERSION.SDK_INT >= 14) {
            b.remove(bVar);
            if (b.size() == 0) {
                i();
            }
        }
    }

    private static void b(boolean z) {
        new Handler(com.inmobi.commons.a.a.b().getMainLooper()).post(new AnonymousClass_2(z));
    }

    public static void b() {
        d = true;
    }

    public static void c() {
        d = false;
    }
}
