package com.taobao.accs.utl;

import android.text.TextUtils;
import com.taobao.tlog.adapter.AdapterForTLog;
import com.umeng.a;

// compiled from: Taobao
public class ALog {
    private static Object LOG_BREAK;
    private static boolean isPrintLog;
    private static boolean isUseTlog;
    private static String preTag;

    // compiled from: Taobao
    public enum Level {
        V,
        D,
        I,
        W,
        E,
        L
    }

    static {
        preTag = "accs.";
        LOG_BREAK = "|";
        isPrintLog = true;
        isUseTlog = true;
    }

    public static void initALog(String str, int i) {
        preTag = str;
    }

    public static void setPrintLog(boolean z) {
        isPrintLog = z;
    }

    public static void setUseTlog(boolean z) {
        isUseTlog = z;
    }

    @Deprecated
    public static void setEnableTLog(boolean z) {
        isUseTlog = z;
    }

    @Deprecated
    public static boolean isPrintLog() {
        return false;
    }

    public static boolean isPrintLog(Level level) {
        if (!isPrintLog) {
            return false;
        }
        if (!isUseTlog) {
            return true;
        }
        Level level2 = Level.L;
        try {
            level2 = Level.valueOf(AdapterForTLog.getLogLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level.ordinal() >= level2.ordinal();
    }

    public static void d(String str, String str2, Object... objArr) {
        d1(str, str2, null, objArr);
    }

    public static void d1(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(Level.D)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logd(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void i(String str, String str2, Object... objArr) {
        i1(str, str2, null, objArr);
    }

    public static void i1(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(Level.I)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logi(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void w(String str, String str2, Object... objArr) {
        w(str, str2, null, objArr);
    }

    public static void w1(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(Level.W)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void w(String str, String str2, Throwable th, Object... objArr) {
        w1(str, str2, null, th, objArr);
    }

    public static void w1(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (!isPrintLog(Level.W)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        e1(str, str2, null, objArr);
    }

    public static void e1(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(Level.E)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void e(String str, String str2, Throwable th, Object... objArr) {
        e1(str, str2, null, th, objArr);
    }

    public static void e1(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (!isPrintLog(Level.E)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    private static String formatKv(Object obj, Object obj2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (obj == null) {
            obj = a.d;
        }
        stringBuilder = stringBuilder.append(obj).append(":");
        if (obj2 == null) {
            obj2 = a.d;
        }
        return stringBuilder.append(obj2).toString();
    }

    private static String buildLogTag(String str) {
        return preTag + str;
    }

    private static String buildLogTag() {
        return preTag;
    }

    static String buildLogMsg(String str, String str2, Object... objArr) {
        int i = 0;
        if (str == null && str2 == null && objArr == null) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append(LOG_BREAK).append(String.format("[seq:%s]", new Object[]{str2}));
            }
            if (str != null) {
                stringBuilder.append(" ").append(str);
            }
            if (objArr != null) {
                while (i + 1 < objArr.length) {
                    stringBuilder.append(" ");
                    Object obj = objArr[i];
                    i++;
                    stringBuilder.append(formatKv(obj, objArr[i]));
                    i++;
                }
                if (i > 0 && i == objArr.length - 1) {
                    stringBuilder.append(" ");
                    stringBuilder.append(objArr[i]);
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return a.d;
        }
    }
}
