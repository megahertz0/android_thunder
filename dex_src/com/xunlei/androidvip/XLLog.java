package com.xunlei.androidvip;

import android.os.Environment;
import com.umeng.message.proguard.j;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.androidvip.XLLog.LogLevel;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class XLLog {
    private static SimpleDateFormat DATEFORMAT;
    private static LogConfig sLogConfig;

    private static class LogConfig {
        private String mAnrDir;
        private long mAnrInterval;
        private String mCrashDir;
        private boolean mDumpCrash;
        private File mFile;
        private String mFileName;
        private LogLevel mLevel;
        private String mLogDir;
        private String mLogFilter;
        private long mLogSize;
        private int mNext;
        private int mRun;

        public LogConfig() {
            this.mLogSize = 20971520;
            this.mLevel = LogLevel.LOG_LEVEL_OFF;
            this.mLogFilter = BuildConfig.VERSION_NAME;
            this.mFileName = BuildConfig.VERSION_NAME;
            this.mLogDir = BuildConfig.VERSION_NAME;
            this.mFile = null;
            this.mNext = 0;
            this.mRun = 0;
            this.mCrashDir = BuildConfig.VERSION_NAME;
            this.mDumpCrash = false;
            this.mAnrInterval = 0;
            this.mAnrDir = BuildConfig.VERSION_NAME;
        }

        public long getAnrInterval() {
            return this.mAnrInterval;
        }

        public String getAnrDir() {
            return this.mAnrDir == null ? BuildConfig.VERSION_NAME : this.mAnrDir;
        }

        public boolean needDumpCrash() {
            return this.mDumpCrash;
        }

        public String getCrashDir() {
            return this.mCrashDir == null ? BuildConfig.VERSION_NAME : this.mCrashDir;
        }

        public String getLogFilter() {
            return this.mLogFilter == null ? BuildConfig.VERSION_NAME : this.mLogFilter;
        }

        public LogLevel getLogLevel() {
            return this.mLevel;
        }

        public File getLogFile() {
            if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + this.mLogDir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                while (this.mFile == null) {
                    this.mFile = new File(file.getPath() + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".R" + this.mRun + ".0." + this.mFileName);
                    if (!this.mFile.exists()) {
                        break;
                    }
                    this.mRun++;
                    this.mFile = null;
                }
                if (getLogFileSize() >= this.mLogSize) {
                    this.mNext++;
                    this.mFile = new File(file.getPath() + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".R" + this.mRun + "." + this.mNext + "." + this.mFileName);
                    this.mFile.delete();
                }
            }
            return this.mFile;
        }

        public long getLogFileSize() {
            long j = -1;
            if (this.mFile == null) {
                return -1;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(this.mFile);
                j = (long) fileInputStream.available();
                fileInputStream.close();
                return j;
            } catch (Exception e) {
                return j;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean parseConfigFile(java.lang.String r10) {
            throw new UnsupportedOperationException("Method not decompiled: com.xunlei.androidvip.XLLog.LogConfig.parseConfigFile(java.lang.String):boolean");
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
            r9.mAnrDir = r0;	 Catch:{ Exception -> 0x0075 }
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
            r9.mAnrInterval = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x008c:
            r1 = "CRASH_DIR";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x009c;
        L_0x0095:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.mCrashDir = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x009c:
            r1 = "CRASH_DUMP";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00b0;
        L_0x00a5:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = java.lang.Boolean.parseBoolean(r0);	 Catch:{ Exception -> 0x0075 }
            r9.mDumpCrash = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00b0:
            r1 = "LOG_FILTER";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00c0;
        L_0x00b9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.mLogFilter = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00c0:
            r1 = "LOG_FILE";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00d0;
        L_0x00c9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.mFileName = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00d0:
            r1 = "LOG_DIR";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x00e0;
        L_0x00d9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r9.mLogDir = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x00e0:
            r1 = "LOG_FILE_SIZE";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x0102;
        L_0x00e9:
            r0 = r0.toString();	 Catch:{ Exception -> 0x0075 }
            r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0075 }
            r9.mLogSize = r0;	 Catch:{ Exception -> 0x0075 }
            r0 = r9.mLogSize;	 Catch:{ Exception -> 0x0075 }
            r4 = 0;
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 != 0) goto L_0x0067;
        L_0x00fb:
            r0 = 20971520; // 0x1400000 float:3.526483E-38 double:1.03613076E-316;
            r9.mLogSize = r0;	 Catch:{ Exception -> 0x0075 }
            goto L_0x0067;
        L_0x0102:
            r1 = "LOG_LEVEL";
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0075 }
            if (r1 == 0) goto L_0x0067;
        L_0x010b:
            r1 = r9.mLevel;	 Catch:{ Exception -> 0x0075 }
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

    public enum LogLevel {
        LOG_LEVEL_DEBUG(1),
        LOG_LEVEL_INFO(2),
        LOG_LEVEL_WARN(3),
        LOG_LEVEL_ERROR(4),
        LOG_LEVEL_OFF(5);
        private int logLevel;

        private LogLevel(int i) {
            this.logLevel = 0;
            this.logLevel = i;
        }

        public final void setValue(String str) {
            if (str.equals("e") || str.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                this.logLevel = LOG_LEVEL_ERROR.getValue();
            } else if (str.equals("w") || str.equals("warn")) {
                this.logLevel = LOG_LEVEL_WARN.getValue();
            } else if (str.equals("i") || str.equals("info")) {
                this.logLevel = LOG_LEVEL_INFO.getValue();
            } else if (str.equals("d") || str.equals("debug")) {
                this.logLevel = LOG_LEVEL_DEBUG.getValue();
            }
        }

        public final int getValue() {
            return this.logLevel;
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

    static {
        DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sLogConfig = new LogConfig();
    }

    public static boolean init(String str) {
        return sLogConfig.parseConfigFile(str);
    }

    public static long getAnrInterval() {
        return sLogConfig.getAnrInterval();
    }

    public static String getAnrDir() {
        return sLogConfig.getAnrDir();
    }

    public static String getCrashDir() {
        return sLogConfig.getCrashDir();
    }

    public static boolean needDumpCrash() {
        return sLogConfig.needDumpCrash();
    }

    public static void i(String str, String str2) {
        log(LogLevel.LOG_LEVEL_INFO, str, str2);
    }

    public static void d(String str, String str2) {
        log(LogLevel.LOG_LEVEL_DEBUG, str, str2);
    }

    public static void w(String str, String str2) {
        log(LogLevel.LOG_LEVEL_WARN, str, str2);
    }

    public static void e(String str, String str2) {
        log(LogLevel.LOG_LEVEL_ERROR, str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        log(LogLevel.LOG_LEVEL_WARN, str, str2 + ": " + th);
    }

    public static void v(String str, String str2) {
        d(str, str2);
    }

    public static void wtf(String str, String str2, Throwable th) {
        log(LogLevel.LOG_LEVEL_WARN, str, str2 + ": " + th);
    }

    public static boolean canbeLog(LogLevel logLevel) {
        return logLevel.getValue() >= sLogConfig.getLogLevel().getValue();
    }

    private static void log(LogLevel logLevel, String str, String str2) {
        if (logLevel.getValue() < sLogConfig.getLogLevel().getValue()) {
            return;
        }
        if (logLevel.getValue() == LogLevel.LOG_LEVEL_INFO.getValue()) {
            formatMessage(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_DEBUG.getValue()) {
            formatMessage(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_WARN.getValue()) {
            formatMessage(str, str2);
        } else if (logLevel.getValue() == LogLevel.LOG_LEVEL_ERROR.getValue()) {
            formatMessage(str, str2);
        }
    }

    private static void logfile(LogLevel logLevel, String str, String str2) {
        String str3 = DATEFORMAT.format(new Date()) + ": " + logLevel.toString() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + j.s + Thread.currentThread().getId() + "):\t" + str2 + "\r";
        try {
            Writer fileWriter = new FileWriter(sLogConfig.getLogFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str3);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    public static synchronized void printStackTrace(Throwable th) {
        synchronized (XLLog.class) {
            if (LogLevel.LOG_LEVEL_ERROR.getValue() >= sLogConfig.getLogLevel().getValue()) {
                try {
                    Writer fileWriter = new FileWriter(sLogConfig.getLogFile(), true);
                    Writer bufferedWriter = new BufferedWriter(fileWriter);
                    th.printStackTrace(new PrintWriter(bufferedWriter));
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String formatMessage(String str, String str2) {
        return str2 + " " + getMethodLocationInfo(str) + " [" + sLogConfig.getLogFilter() + "]";
    }

    private static String getMethodLocationInfo(String str) {
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
