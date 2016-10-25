package com.taobao.accs.net;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Calendar;
import org.android.agoo.intent.IntentUtil;

// compiled from: Taobao
public class e {
    private static e a;
    private static final int[] b;
    private int c;
    private long d;
    private boolean e;
    private int[] f;
    private Context g;
    private PendingIntent h;
    private AlarmManager i;
    private boolean j;

    static {
        a = null;
        b = new int[]{270, 360, 480};
    }

    private e(Context context) {
        this.e = false;
        this.f = new int[]{0, 0, 0};
        this.j = true;
        try {
            this.g = context;
            this.c = 0;
            this.d = System.currentTimeMillis();
            this.i = (AlarmManager) this.g.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
            this.j = h.b();
        } catch (Throwable th) {
            ALog.e("HeartbeatManager", "HeartbeatManager", th, new Object[0]);
        }
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(context);
            }
            eVar = a;
        }
        return eVar;
    }

    public synchronized void a() {
        if (this.d < 0) {
            this.d = System.currentTimeMillis();
        }
        if (this.h == null) {
            Intent intent = new Intent();
            intent.setPackage(this.g.getPackageName());
            intent.setAction(Constants.ACTION_COMMAND);
            intent.putExtra(IntentUtil.AGOO_COMMAND, Constants.COMMAND_PING);
            this.h = PendingIntent.getBroadcast(this.g, 0, intent, 0);
        }
        int b = b();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("HeartbeatManager", new StringBuilder("set ").append(b).toString(), new Object[0]);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.add(XZBDevice.Upload, b);
        this.i.set(0, instance.getTimeInMillis(), this.h);
    }

    public int b() {
        int i = 270;
        if (this.j) {
            i = b[this.c];
        }
        this.j = h.b();
        return i;
    }

    public void c() {
        int i;
        this.d = -1;
        if (this.e) {
            int[] iArr = this.f;
            int i2 = this.c;
            iArr[i2] = iArr[i2] + 1;
        }
        if (this.c > 0) {
            i = this.c - 1;
        } else {
            i = 0;
        }
        this.c = i;
        ALog.d("HeartbeatManager", "onNetworkTimeout", new Object[0]);
    }

    public void d() {
        this.d = -1;
        ALog.d("HeartbeatManager", "onNetworkFail", new Object[0]);
    }

    public void e() {
        ALog.d("HeartbeatManager", "onHeartbeatSucc", new Object[0]);
        if (System.currentTimeMillis() - this.d <= 7199000) {
            this.e = false;
            this.f[this.c] = 0;
        } else if (this.c < b.length - 1 && this.f[this.c] <= 2) {
            ALog.d("HeartbeatManager", "upgrade", new Object[0]);
            this.c++;
            this.e = true;
            this.d = System.currentTimeMillis();
        }
    }

    public void f() {
        this.c = 0;
        this.d = System.currentTimeMillis();
        ALog.d("HeartbeatManager", "resetLevel", new Object[0]);
    }
}
