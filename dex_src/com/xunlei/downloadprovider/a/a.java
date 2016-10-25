package com.xunlei.downloadprovider.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import java.util.List;

// compiled from: ActivityUtil.java
public final class a {
    public static boolean a(Context context) {
        String packageName = context.getPackageName();
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks.size() > 0 && ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().trim().equals(packageName);
    }

    public static boolean a(Context context, Class<?> cls) {
        String name = cls.getName();
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks.size() > 0 && name.equals(((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName());
    }
}
