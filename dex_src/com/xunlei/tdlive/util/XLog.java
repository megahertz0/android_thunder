package com.xunlei.tdlive.util;

import com.umeng.socialize.common.SocializeConstants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XLog {
    private static String a;
    private static String b;
    private static SimpleDateFormat c;
    private static a d;
    private static boolean e;

    static {
        a = "xllive/log";
        b = "xllive.log";
        c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        d = new a();
        e = false;
    }

    public static boolean init(String str) {
        return d.a(str);
    }

    public static long getAnrInterval() {
        return d.a();
    }

    public static String getAnrDir() {
        return d.b();
    }

    public static String getCrashDir() {
        return d.d();
    }

    public static boolean needDumpCrash() {
        return d.c();
    }

    public static boolean enableLog() {
        return e;
    }

    public static void enableLog(boolean z) {
        e = z;
    }

    public static StackTraceElement getCurrentStackTraceElement() {
        return Thread.currentThread().getStackTrace()[3];
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void i(String str, String str2) {
        a(b.b, str, str2);
    }

    public static void d(String str, String str2) {
        a(b.a, str, str2);
    }

    public static void w(String str, String str2) {
        a(b.c, str, str2);
    }

    public static void e(String str, String str2) {
        e(str, str2, null);
    }

    public static void e(String str, String str2, Throwable th) {
        if (th != null) {
            a(b.d, str, str2 + ", " + th.toString());
        } else {
            a(b.d, str, str2);
        }
    }

    public static boolean canbeLog(b bVar) {
        return bVar.a() >= d.f().a();
    }

    private static void a(b bVar, String str, String str2) {
        if (enableLog() && bVar.a() >= d.f().a()) {
            int i = 0;
            int length = str2.length();
            while (i < length) {
                String substring;
                if (i + 4000 < length) {
                    substring = str2.substring(i, i + 4000);
                    i += 4000;
                } else {
                    substring = str2.substring(i);
                    i += length;
                }
                b(bVar, str, substring);
            }
        }
    }

    private static void b(b bVar, String str, String str2) {
        if (bVar.a() == b.b.a()) {
            a(str, str2);
        } else if (bVar.a() == b.a.a()) {
            a(str, str2);
        } else if (bVar.a() == b.c.a()) {
            a(str, str2);
        } else if (bVar.a() == b.d.a()) {
            a(str, str2);
        }
        synchronized (XLog.class) {
            c(bVar, str, a(str, str2));
        }
    }

    private static void c(b bVar, String str, String str2) {
        String str3 = c.format(new Date()) + ": " + bVar.toString() + "/" + str + SocializeConstants.OP_OPEN_PAREN + Thread.currentThread().getId() + "):\t" + str2 + "\r";
        try {
            Writer fileWriter = new FileWriter(d.g(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str3);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    private static String a(String str, String str2) {
        return str2 + " " + a(str) + " [" + d.e() + "]";
    }

    private static String a(String str) {
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
