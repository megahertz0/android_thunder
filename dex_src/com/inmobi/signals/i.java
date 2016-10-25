package com.inmobi.signals;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.c.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.signals.activityrecognition.b;
import com.inmobi.signals.b.c;
import com.umeng.message.MsgConstant;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: IceCollector.java
class i {
    private static final String a;
    private HandlerThread b;
    private a c;

    // compiled from: IceCollector.java
    static class a extends Handler {
        private List<m> a;

        // compiled from: IceCollector.java
        class AnonymousClass_1 implements com.inmobi.signals.b.c.a {
            final /* synthetic */ m a;

            AnonymousClass_1(m mVar) {
                this.a = mVar;
            }

            public void a() {
                Logger.a(InternalLogLevel.INTERNAL, a, "Wifi scan timeout.");
                a.this.a(this.a);
            }

            public void a(List<com.inmobi.signals.b.a> list) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Wifi scan successful.");
                this.a.a((List) list);
                a.this.a(this.a);
            }
        }

        a(Looper looper) {
            super(looper);
            this.a = new ArrayList();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    b();
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    c();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    Logger.a(InternalLogLevel.INTERNAL, a, "Polling for samples.");
                    if (VERSION.SDK_INT >= 14 || a()) {
                        if (o.a().e().q()) {
                            b.a().b();
                        } else {
                            b.a().c();
                        }
                        d();
                        sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_FAILED, (long) (o.a().e().b() * 1000));
                        return;
                    }
                    sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    a(e());
                    f();
                default:
                    break;
            }
        }

        private void b() {
            Logger.a(InternalLogLevel.INTERNAL, a, "User data collection started.");
            sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
        }

        private void c() {
            Logger.a(InternalLogLevel.INTERNAL, a, "Stopping user data collection.");
            b.a().c();
            removeMessages(XZBDevice.DOWNLOAD_LIST_FAILED);
            sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
        }

        private void d() {
            m mVar = new m();
            mVar.a(com.inmobi.signals.b.b.a());
            mVar.a(LocationInfo.a().f());
            if (!o.a().e().k() || !com.inmobi.signals.b.b.c()) {
                a(mVar);
            } else if (!c.a(new AnonymousClass_1(mVar))) {
                a(mVar);
            }
        }

        private void a(m mVar) {
            if (this.a != null && mVar.a()) {
                this.a.add(mVar);
                if (this.a.size() > o.a().e().d()) {
                    com.inmobi.commons.core.c.a.a().a(new e("signals", "SampleSizeExceeded"));
                    while (this.a.size() > o.a().e().d()) {
                        this.a.remove(0);
                    }
                }
            }
        }

        private l e() {
            l lVar = new l();
            lVar.a(LocationInfo.a().e());
            lVar.a(this.a);
            lVar.a(n.a().d());
            lVar.b(b.a().d());
            return lVar;
        }

        private void f() {
            b.a().e();
            this.a = new ArrayList();
        }

        private void a(l lVar) {
            p.b e = o.a().e();
            new j(new k(e.e(), e.f(), e.g(), o.a().d(), lVar)).a();
        }

        public static boolean a() {
            ActivityManager activityManager = (ActivityManager) com.inmobi.commons.a.a.b().getSystemService("activity");
            if (activityManager != null) {
                try {
                    if (((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName().equalsIgnoreCase(com.inmobi.commons.a.a.b().getPackageName())) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Is app in foreground check for below ICS: true");
                        return true;
                    }
                } catch (Throwable e) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "NPE while determining if app is in foreground for below ICS devices.", e);
                }
            }
            return false;
        }
    }

    static {
        a = i.class.getSimpleName();
    }

    public i() {
        this.b = new HandlerThread("DataCollectionHandler");
        this.b.start();
        this.c = new a(this.b.getLooper());
    }

    public synchronized void a() {
        if (VERSION.SDK_INT < 14 && !b()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "User data collection can not be started as the data collector is not properly initialized.");
        } else if (this.c.hasMessages(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            Logger.a(InternalLogLevel.INTERNAL, a, "User data collection already running.");
        } else {
            this.c.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.c.sendEmptyMessage(1);
        }
    }

    public boolean b() {
        return d.a("signals", MsgConstant.PERMISSION_GET_TASKS);
    }

    public void c() {
        this.c.sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, (long) (o.a().e().c() * 1000));
    }
}
