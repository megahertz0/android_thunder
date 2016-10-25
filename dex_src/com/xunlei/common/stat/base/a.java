package com.xunlei.common.stat.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.common.stat.base.a.b;
import java.util.HashMap;
import java.util.Map;

// compiled from: XLAlarmTimer.java
public final class a {
    private static String b = "com.xunlei.xlkdemo.timer.CATEGORY";
    private static String c = "com.xunlei.xlkdemo.timer.action_";
    private static String d = "alarm_timer_id";
    private static a e;
    private Map<String, a> a;
    private Context f;
    private BroadcastReceiver g;

    // compiled from: XLAlarmTimer.java
    public static interface b {
        void onTimerTick(int i);
    }

    // compiled from: XLAlarmTimer.java
    public static class a {
        private static String f = "_id";
        private static String g = "url";
        private static String h = "error";
        private static String i = "respt";
        private static String j = "retry";
        private static String k = "ip";
        private static String l = "domain";
        private static String m = "cmd";
        private static String n = "bt";
        private static String o = "date";
        private static String p = "uid";
        private int a;
        private boolean b;
        private b c;
        private int d;
        private PendingIntent e;

        public a(int i, int i2, boolean z, b bVar) {
            this.a = 0;
            this.b = false;
            this.c = null;
            this.d = 0;
            this.e = null;
            this.a = i;
            this.d = i2;
            this.b = z;
            this.c = bVar;
        }

        public static String a(int i) {
            return new StringBuilder("com.xunlei.xlkdemo.timer.action_").append(String.valueOf(i)).toString();
        }

        public final int a() {
            return this.a;
        }

        public final void b() {
            Intent intent = new Intent("com.xunlei.xlkdemo.timer.action_");
            intent.addCategory("com.xunlei.xlkdemo.timer.CATEGORY");
            intent.putExtra("alarm_timer_id", this.a);
            this.e = PendingIntent.getBroadcast(e.f, this.a, intent, 134217728);
            ((AlarmManager) e.f.getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).setRepeating(0, System.currentTimeMillis() + ((long) this.d), (long) this.d, this.e);
        }

        public final void c() {
            ((AlarmManager) e.f.getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).cancel(this.e);
        }

        public final void d() {
            if (this.c != null) {
                this.c.onTimerTick(this.a);
            }
        }

        public final boolean e() {
            return this.b;
        }

        private int f() {
            return this.d;
        }
    }

    static {
        e = null;
    }

    private a() {
        this.a = new HashMap();
        this.f = null;
        this.g = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                com.xunlei.common.stat.base.a.a aVar = (com.xunlei.common.stat.base.a.a) a.this.get(intent.getAction() + intent.getIntExtra("alarm_timer_id", 0));
                if (aVar != null) {
                    aVar.d();
                    if (!aVar.e()) {
                        e.a(aVar.a());
                    }
                }
            }
        };
    }

    public static void a(Context context) {
        a aVar = new a();
        e = aVar;
        aVar.f = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory("com.xunlei.xlkdemo.timer.CATEGORY");
        intentFilter.addAction("com.xunlei.xlkdemo.timer.action_");
        intentFilter.setPriority(InMobiClientPositioning.NO_REPEAT);
        e.f.registerReceiver(e.g, intentFilter);
    }

    public static void a() {
        e.f.unregisterReceiver(e.g);
    }

    public static a b() {
        return e;
    }

    public final void a(int i, int i2, boolean z, b bVar) {
        a aVar = new a(i, 30000, true, bVar);
        this.a.put(a.a(i), aVar);
        aVar.b();
    }

    public final void a(int i) {
        a aVar = (a) this.a.get(a.a(i));
        if (aVar != null) {
            aVar.c();
            this.a.remove(a.a(i));
        }
    }

    private void d() {
        for (String str : this.a.keySet()) {
            ((a) this.a.get(str)).c();
        }
        this.a.clear();
    }
}
