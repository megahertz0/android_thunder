package com.xunlei.common.lixian;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.os.Message;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class e {
    private static boolean a = true;
    private static String b = "/xunlei/log";
    private static final String c = "/log.txt";
    private static int d = 0;
    private static int e = 1;
    private static int f = 2;
    private static int g = 3;
    private static int h;
    private static f i;
    private static SimpleDateFormat j;

    static {
        h = 3;
        i = null;
        j = null;
    }

    private e() {
    }

    private static void a(String str) {
        for (StackTraceElement stackTraceElement : (StackTraceElement[]) Thread.getAllStackTraces().get(Thread.currentThread())) {
            stackTraceElement.toString();
        }
    }

    private static void a(String str, Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo2);
        long nativeHeapSize = Debug.getNativeHeapSize();
        long nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        long nativeHeapFreeSize = Debug.getNativeHeapFreeSize();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        new StringBuilder("heap_stats heap_size=").append(decimalFormat.format((double) (((float) nativeHeapSize) / 1048576.0f))).append("M allocated=").append(decimalFormat.format((double) (((float) nativeHeapAllocatedSize) / 1048576.0f))).append("M free=").append(decimalFormat.format((double) (((float) nativeHeapFreeSize) / 1048576.0f))).append("M memory_stats memory_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.getTotalPss()) / 1024.0f))).append("M dalvik_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.dalvikPss) / 1024.0f))).append("M native_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.nativePss) / 1024.0f))).append("M other_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.otherPss) / 1024.0f))).append("M system_stats system_available=").append(decimalFormat.format((double) (((float) memoryInfo.availMem) / 1048576.0f))).append("M");
    }

    public static void a(String str, String str2) {
        if (h == 2 || h == 3) {
            c();
            if (str != null && str2 != null) {
                a("Info", str, str2);
            }
        }
    }

    private static void a(String str, String str2, String str3) {
        if (j == null) {
            j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j.format(Calendar.getInstance().getTime())).append(" [");
        stringBuilder.append("Thread-").append(Thread.currentThread().getId()).append("] ");
        stringBuilder.append(str.toUpperCase()).append(" ");
        stringBuilder.append(str2).append(" : ").append(str3);
        Message obtainMessage = i.obtainMessage();
        obtainMessage.obj = stringBuilder.toString();
        i.sendMessage(obtainMessage);
    }

    private static void a(Throwable th) {
        if (th != null) {
            if (h == 1 || h == 3) {
                th.printStackTrace();
            }
        }
    }

    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static File b() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xunlei/log");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static void b(String str, String str2) {
        if (h == 2 || h == 3) {
            c();
            if (str != null && str2 != null) {
                a("Debug", str, str2);
            }
        }
    }

    private static void c() {
        if (i == null) {
            i = new f();
        }
    }

    private static void c(String str, String str2) {
        if (h == 2 || h == 3) {
            c();
            if (str != null && str2 != null) {
                a("Verbose", str, str2);
            }
        }
    }

    private static void d(String str, String str2) {
        if (h == 2 || h == 3) {
            c();
            if (str != null && str2 != null) {
                a("Warn", str, str2);
            }
        }
    }

    private static void e(String str, String str2) {
        if (h == 2 || h == 3) {
            c();
            if (str != null && str2 != null) {
                a("Error", str, str2);
            }
        }
    }
}
