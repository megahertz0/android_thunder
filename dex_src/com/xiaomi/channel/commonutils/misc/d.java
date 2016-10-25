package com.xiaomi.channel.commonutils.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.xiaomi.channel.commonutils.misc.d.a;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class d {
    private static volatile d a;
    private ScheduledThreadPoolExecutor b;
    private SparseArray<ScheduledFuture> c;
    private Object d;
    private SharedPreferences e;

    public static abstract class a implements Runnable {
        public abstract int a();
    }

    private static class b implements Runnable {
        a c;

        public b(a aVar) {
            this.c = aVar;
        }

        void a() {
        }

        void b() {
        }

        public void run() {
            a();
            this.c.run();
            b();
        }
    }

    private d(Context context) {
        this.b = new ScheduledThreadPoolExecutor(1);
        this.c = new SparseArray();
        this.d = new Object();
        this.e = context.getSharedPreferences("mipush_extra", 0);
    }

    public static d a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(context);
                }
            }
        }
        return a;
    }

    private static String a(int i) {
        return new StringBuilder("last_job_time").append(i).toString();
    }

    private ScheduledFuture a(a aVar) {
        ScheduledFuture scheduledFuture;
        synchronized (this.d) {
            scheduledFuture = (ScheduledFuture) this.c.get(aVar.a());
        }
        return scheduledFuture;
    }

    public void a(Runnable runnable, int i) {
        this.b.schedule(runnable, (long) i, TimeUnit.SECONDS);
    }

    public boolean a(a aVar, int i, int i2) {
        if (aVar == null || a(aVar) != null) {
            return false;
        }
        String a = a(aVar.a());
        Runnable eVar = new e(this, aVar, a);
        long abs = Math.abs(System.currentTimeMillis() - this.e.getLong(a, 0)) / 1000;
        if (abs < ((long) (i - i2))) {
            i2 = (int) (((long) i) - abs);
        }
        ScheduledFuture scheduleAtFixedRate = this.b.scheduleAtFixedRate(eVar, (long) i2, (long) i, TimeUnit.SECONDS);
        synchronized (this.d) {
            this.c.put(aVar.a(), scheduleAtFixedRate);
        }
        return true;
    }
}
