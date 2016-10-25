package com.xiaomi.push.service.timers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.xiaomi.push.service.z;
import com.xiaomi.smack.j;

class b implements a$a {
    private PendingIntent a;
    private Context b;
    private volatile long c;

    public b(Context context) {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.b = context;
    }

    private void a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke(alarmManager, new Object[]{Integer.valueOf(0), Long.valueOf(j), pendingIntent});
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    public void a() {
        if (this.a != null) {
            ((AlarmManager) this.b.getSystemService("alarm")).cancel(this.a);
            this.a = null;
            com.xiaomi.channel.commonutils.logger.b.c("unregister timer");
        }
        this.c = 0;
    }

    public void a(Intent intent, long j) {
        AlarmManager alarmManager = (AlarmManager) this.b.getSystemService("alarm");
        this.a = PendingIntent.getBroadcast(this.b, 0, intent, 0);
        if (VERSION.SDK_INT >= 19) {
            a(alarmManager, j, this.a);
        } else {
            alarmManager.set(0, j, this.a);
        }
        com.xiaomi.channel.commonutils.logger.b.c(new StringBuilder("register timer ").append(j).toString());
    }

    public void a(boolean z) {
        long c = (long) j.c();
        if (z || this.c != 0) {
            if (z) {
                a();
            }
            if (z || this.c == 0) {
                this.c = (c - (SystemClock.elapsedRealtime() % c)) + System.currentTimeMillis();
            } else {
                this.c += c;
                if (this.c < System.currentTimeMillis()) {
                    this.c = c + System.currentTimeMillis();
                }
            }
            Intent intent = new Intent(z.o);
            intent.setPackage(this.b.getPackageName());
            a(intent, this.c);
        }
    }

    public boolean b() {
        return this.c != 0;
    }
}
