package com.xunlei.android.xlstat;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.android.xlstat.XLLog.LogLevel;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.text.SimpleDateFormat;

public final class XLLog {
    private static SimpleDateFormat a;
    private static a b;

    public enum LogLevel {
        LOG_LEVEL_DEBUG(1),
        LOG_LEVEL_INFO(2),
        LOG_LEVEL_WARN(3),
        LOG_LEVEL_ERROR(4),
        LOG_LEVEL_OFF(5);
        private int a;

        static {
            LOG_LEVEL_DEBUG = new com.xunlei.android.xlstat.XLLog.LogLevel("LOG_LEVEL_DEBUG", 0, 1);
            LOG_LEVEL_INFO = new com.xunlei.android.xlstat.XLLog.LogLevel("LOG_LEVEL_INFO", 1, 2);
            LOG_LEVEL_WARN = new com.xunlei.android.xlstat.XLLog.LogLevel("LOG_LEVEL_WARN", 2, 3);
            LOG_LEVEL_ERROR = new com.xunlei.android.xlstat.XLLog.LogLevel("LOG_LEVEL_ERROR", 3, 4);
            LOG_LEVEL_OFF = new com.xunlei.android.xlstat.XLLog.LogLevel("LOG_LEVEL_OFF", 4, 5);
            b = new com.xunlei.android.xlstat.XLLog.LogLevel[]{LOG_LEVEL_DEBUG, LOG_LEVEL_INFO, LOG_LEVEL_WARN, LOG_LEVEL_ERROR, LOG_LEVEL_OFF};
        }

        private LogLevel(int i) {
            this.a = 0;
            this.a = i;
        }

        public final void setValue(String str) {
            if (str.equals("e") || str.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                this.a = LOG_LEVEL_ERROR.getValue();
            } else if (str.equals("w") || str.equals("warn")) {
                this.a = LOG_LEVEL_WARN.getValue();
            } else if (str.equals("i") || str.equals("info")) {
                this.a = LOG_LEVEL_INFO.getValue();
            } else if (str.equals("d") || str.equals("debug")) {
                this.a = LOG_LEVEL_DEBUG.getValue();
            }
        }

        public final int getValue() {
            return this.a;
        }

        public final String toString() {
            return toString(true);
        }

        public final String toString(boolean z) {
            if (getValue() == LOG_LEVEL_ERROR.getValue()) {
                return z ? "E" : "ERROR";
            } else {
                if (getValue() == LOG_LEVEL_WARN.getValue()) {
                    return z ? "W" : "WARN";
                } else {
                    if (getValue() == LOG_LEVEL_DEBUG.getValue()) {
                        return z ? "D" : "DEBUG";
                    } else {
                        if (getValue() == LOG_LEVEL_INFO.getValue()) {
                            return z ? "I" : "INFO";
                        } else {
                            return BuildConfig.VERSION_NAME;
                        }
                    }
                }
            }
        }
    }

    private static class a {
        String a;
        LogLevel b;
        private String c;
        private boolean d;
        private String e;
        private long f;
        private String g;
        private String h;
        private long i;
        private File j;
        private int k;
        private int l;

        public a() {
            this.i = 20971520;
            this.b = LogLevel.LOG_LEVEL_OFF;
            this.a = BuildConfig.VERSION_NAME;
            this.h = BuildConfig.VERSION_NAME;
            this.g = BuildConfig.VERSION_NAME;
            this.j = null;
            this.k = 0;
            this.l = 0;
            this.c = BuildConfig.VERSION_NAME;
            this.d = false;
            this.f = 0;
            this.e = BuildConfig.VERSION_NAME;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(java.lang.String r10) {
            throw new UnsupportedOperationException("Method not decompiled: com.xunlei.android.xlstat.XLLog.a.a(java.lang.String):boolean");
            /*
            this = this;
            r8 = 13;
            r7 = 10;
            r6 = -1;
            r0 = new java.io.File;
            r0.<init>(r10);
            r1 = r0.exists();
            if (r1 == 0) goto L_0x0076;
        L_0x0010:
            r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0075 }
            r3.<init>(r0);	 Catch:{ Exception -> 0x0075 }
            r2 = "";
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0075 }
            r1.<init>();	 Catch:{ Exception -> 0x0075 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0075 }
            r0.<init>();	 Catch:{ Exception -> 0x0075 }
        L_0x0022:
            r4 = r3.read();	 Catch:{ Exception -> 0x0075 }
            if (r4 == r6) goto L_0x012c;
        L_0x0028:
            r5 = r1.length();	 Catch:{ Exception -> 0x0075 }
            if (r5 != 0) goto L_0x003d;
        L_0x002e:
            r5 = 35;
            if (r4 != r5) goto L_0x003d;
        L_0x0032:
            r4 = r3.read();	 Catch:{ Exception -> 0x0075 }
            if (r4 == r6) goto L_0x0022;
        L_0x0038:
            if (r4 == r8) goto L_0x0022;
        L_0x003a:
            if (r4 != r7) goto L_0x0032;
        L_0x003c:
            goto L_0x0022;
        L_0x003d:
            r5 = 32;
            if (r4 == r5) goto L_0x0022;
        L_0x0041:
            r5 = 9;
            if (r4 == r5) goto L_0x0022;
        L_0x0045:
            r5 = 61;
            if (r4 != r5) goto L_0x004e;
        L_0x0049:
            r2 = r1.toString();	 Catch:{ Exception -> 0x0075 }
            goto L_0x0022;
        L_0x004e:
            if (r4 == r7) goto L_0x0052;
        L_0x0050:
            if (r4 != r8) goto L_0x011a;
        L_0x0052:
            r4 = r1.length();	 Catch:{ Exception -> 0x0075 }
            if (r4 == 0) goto L_0x0022;
        L_0x0058:
            r1 = "ANR_DIR";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x0078;
        L_0x0061:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.e = r0;	 Catch:{ Exception -> 0x0075 }
        L_0x0067:
            r2 = "";
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0075 }
            r1.<init>();	 Catch:{ Exception -> 0x0075 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0075 }
            r0.<init>();	 Catch:{ Exception -> 0x0075 }
            goto L_0x0022;
        L_0x0075:
            r0 = move-exception;
        L_0x0076:
            r0 = 0;
        L_0x0077:
            return r0;
        L_0x0078:
            r1 = "ANR_INTERVAL";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x008c;
        L_0x0081:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0075 }
            r9.f = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x008c:
            r1 = "CRASH_DIR";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x009c;
        L_0x0095:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.c = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x009c:
            r1 = "CRASH_DUMP";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00b0;
        L_0x00a5:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = java.lang.Boolean.parseBoolean(r0);	 Catch:{ Exception -> 0x0075 }
            r9.d = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00b0:
            r1 = "LOG_FILTER";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00c0;
        L_0x00b9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.a = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00c0:
            r1 = "LOG_FILE";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00d0;
        L_0x00c9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.h = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00d0:
            r1 = "LOG_DIR";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00e0;
        L_0x00d9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.g = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00e0:
            r1 = "LOG_FILE_SIZE";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x0102;
        L_0x00e9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0075 }
            r9.i = r0;	 Catch:{ Exception -> 0x0075 }
            r0 = r9.i;	 Catch:{ Exception -> 0x0075 }
            r4 = 0;
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 != 0) goto L_0x0067;
        L_0x00fb:
            r0 = 20971520; // 0x1400000 float:3.526483E-38 double:1.03613076E-316;
            r9.i = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x0102:
            r1 = "LOG_LEVEL";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x0067;
        L_0x010b:
            r1 = r9.b;	 Catch:{ Exception -> 0x0075 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = r0.toLowerCase();	 Catch:{ Exception -> 0x0075 }
            r1.setValue(r0);	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x011a:
            r5 = r2.length();	 Catch:{ Exception -> 0x0075 }
            if (r5 != 0) goto L_0x0126;
        L_0x0120:
            r4 = (char) r4;	 Catch:{ Exception -> 0x0075 }
            r1.append(r4);	 Catch:{ Exception -> 0x0075 }
            goto L_0x0022;
        L_0x0126:
            r4 = (char) r4;	 Catch:{ Exception -> 0x0075 }
            r0.append(r4);	 Catch:{ Exception -> 0x0075 }
            goto L_0x0022;
        L_0x012c:
            r3.close();	 Catch:{ Exception -> 0x0075 }
            r0 = 1;
            goto L_0x0077;
            */
        }
    }

    static {
        a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        b = new a();
    }

    public static boolean a(String str) {
        return b.a(str);
    }

    public static void a(String str, String str2) {
        a(LogLevel.LOG_LEVEL_INFO, str, str2);
    }

    public static void b(String str, String str2) {
        a(LogLevel.LOG_LEVEL_DEBUG, str, str2);
    }

    public static void c(String str, String str2) {
        a(LogLevel.LOG_LEVEL_ERROR, str, str2);
    }

    private static void a(LogLevel logLevel, String str, String str2) {
        if (logLevel.getValue() < b.b.getValue()) {
            return;
        }
        if (logLevel.getValue() == LogLevel.LOG_LEVEL_INFO.getValue()) {
            d(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_DEBUG.getValue()) {
            d(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_WARN.getValue()) {
            d(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_ERROR.getValue()) {
            d(str, str2);
        }
    }

    private static String d(String str, String str2) {
        StringBuilder append = new StringBuilder().append(str2).append(" ").append(b(str)).append(" [");
        a aVar = b;
        return append.append(aVar.a == null ? BuildConfig.VERSION_NAME : aVar.a).append("]").toString();
    }

    private static String b(String str) {
        try {
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (-1 != stackTraceElement.getClassName().indexOf(str)) {
                    return new StringBuilder("[").append(stackTraceElement.getFileName()).append(":").append(stackTraceElement.getLineNumber()).append(" - ").append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append("]").toString();
                }
            }
            return str;
        } catch (Exception e) {
            return "[]";
        }
    }
}
