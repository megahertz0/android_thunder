package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;
import java.util.Timer;
import java.util.TimerTask;

public class d {
    private static volatile d b;
    private Timer a;
    private Context c;

    static {
        b = null;
    }

    private d(Context context) {
        this.a = null;
        this.c = null;
        this.c = context.getApplicationContext();
        this.a = new Timer(false);
    }

    public static d a(Context context) {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d(context);
                }
            }
        }
        return b;
    }

    public void a() {
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.PERIOD) {
            long sendPeriodMinutes = (long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000);
            if (StatConfig.isDebugEnable()) {
                k.b().i(new StringBuilder("setupPeriodTimer delay:").append(sendPeriodMinutes).toString());
            }
            a(new e(this), sendPeriodMinutes);
        }
    }

    public void a(TimerTask timerTask, long j) {
        if (this.a != null) {
            if (StatConfig.isDebugEnable()) {
                k.b().i(new StringBuilder("setupPeriodTimer schedule delay:").append(j).toString());
            }
            this.a.schedule(timerTask, j);
        } else if (StatConfig.isDebugEnable()) {
            k.b().w("setupPeriodTimer schedule timer == null");
        }
    }
}
