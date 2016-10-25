package com.xunlei.tdlive.b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Process;
import android.preference.PreferenceManager;
import java.util.List;

// compiled from: PushHelper.java
public class a {
    private static a a;
    private Context b;
    private boolean c;

    private a(Context context) {
        this.c = false;
        this.b = context;
        this.c = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("push_enable", true);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    public void a() {
        if (c()) {
            b(this.c);
        }
    }

    private void b(boolean z) {
    }

    public void a(String str) {
    }

    public void b(String str) {
    }

    public boolean b() {
        return this.c;
    }

    public void a(boolean z) {
        if (z != this.c) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.b).edit();
            edit.putBoolean("push_enable", z);
            edit.apply();
        }
        this.c = z;
    }

    private boolean c() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.b.getSystemService("activity")).getRunningAppProcesses();
        String packageName = this.b.getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }
}
