package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import com.umeng.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashSet;

public class StatNativeCrashReport {
    public static final String PRE_TAG_TOMBSTONE_FNAME = "tombstone_";
    static StatNativeCrashReport a;
    private static StatLogger b;
    private static boolean d;
    private static boolean e;
    private static String f;
    private volatile boolean c;

    static {
        b = k.b();
        a = new StatNativeCrashReport();
        d = false;
        e = false;
        f = null;
        try {
            System.loadLibrary("MtaNativeCrash");
        } catch (Throwable th) {
            d = false;
            b.w(th);
        }
    }

    public StatNativeCrashReport() {
        this.c = false;
    }

    static String a(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append('\n');
            }
            bufferedReader.close();
        } catch (Exception e) {
            b.e(e);
        }
        return stringBuilder.toString();
    }

    static LinkedHashSet<File> a(Context context) {
        LinkedHashSet<File> linkedHashSet = new LinkedHashSet();
        String tombstonesDir = getTombstonesDir(context);
        if (tombstonesDir != null) {
            File file = new File(tombstonesDir);
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.getName().startsWith(PRE_TAG_TOMBSTONE_FNAME) && file2.isFile()) {
                            b.d(new StringBuilder("get tombstone file:").append(file2.getAbsolutePath().toString()).toString());
                            linkedHashSet.add(file2.getAbsoluteFile());
                        }
                    }
                }
            }
        }
        return linkedHashSet;
    }

    static long b(File file) {
        try {
            return Long.valueOf(file.getName().replace(PRE_TAG_TOMBSTONE_FNAME, a.d)).longValue();
        } catch (Exception e) {
            b.e(e);
            return 0;
        }
    }

    public static void doNativeCrashTest() {
        a.makeJniCrash();
    }

    public static String getTombstonesDir(Context context) {
        if (f == null) {
            f = p.a(context, "__mta_tombstone__", a.d);
        }
        return f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initNativeCrash(android.content.Context r3, java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.StatNativeCrashReport.initNativeCrash(android.content.Context, java.lang.String):void");
        /*
        r0 = a;
        r0 = r0.c;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        if (r4 != 0) goto L_0x0015;
    L_0x0009:
        r0 = "tombstones";
        r1 = 0;
        r0 = r3.getDir(r0, r1);	 Catch:{ Throwable -> 0x003a }
        r4 = r0.getAbsolutePath();	 Catch:{ Throwable -> 0x003a }
    L_0x0015:
        r0 = r4.length();	 Catch:{ Throwable -> 0x003a }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r0 <= r1) goto L_0x0041;
    L_0x001d:
        r0 = b;	 Catch:{ Throwable -> 0x003a }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x003a }
        r2 = "The length of tombstones dir: ";
        r1.<init>(r2);	 Catch:{ Throwable -> 0x003a }
        r1 = r1.append(r4);	 Catch:{ Throwable -> 0x003a }
        r2 = " can't exceeds 200 bytes.";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x003a }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x003a }
        r0.e(r1);	 Catch:{ Throwable -> 0x003a }
        goto L_0x0006;
    L_0x003a:
        r0 = move-exception;
        r1 = b;
        r1.w(r0);
        goto L_0x0006;
    L_0x0041:
        f = r4;	 Catch:{ Throwable -> 0x003a }
        r0 = "__mta_tombstone__";
        com.tencent.stat.common.p.b(r3, r0, r4);	 Catch:{ Throwable -> 0x003a }
        r0 = 1;
        setNativeCrashEnable(r0);	 Catch:{ Throwable -> 0x003a }
        r0 = a;	 Catch:{ Throwable -> 0x003a }
        r0.initJNICrash(r4);	 Catch:{ Throwable -> 0x003a }
        r0 = a;	 Catch:{ Throwable -> 0x003a }
        r1 = 1;
        r0.c = r1;	 Catch:{ Throwable -> 0x003a }
        r0 = b;	 Catch:{ Throwable -> 0x003a }
        r1 = "initNativeCrash success.";
        r0.d(r1);	 Catch:{ Throwable -> 0x003a }
        goto L_0x0006;
        */
    }

    public static boolean isNativeCrashDebugEnable() {
        return e;
    }

    public static boolean isNativeCrashEnable() {
        return d;
    }

    public static String onNativeCrashHappened() {
        String str = a.d;
        try {
            new RuntimeException("MTA has caught a native crash, java stack:\n").printStackTrace();
            return str;
        } catch (RuntimeException e) {
            return e.toString();
        }
    }

    public static void setNativeCrashDebugEnable(boolean z) {
        try {
            a.enableNativeCrashDebug(z);
            e = z;
        } catch (Throwable th) {
            b.w(th);
        }
    }

    public static void setNativeCrashEnable(boolean z) {
        try {
            a.enableNativeCrash(z);
            d = z;
        } catch (Throwable th) {
            b.w(th);
        }
    }

    public native void enableNativeCrash(boolean z);

    public native void enableNativeCrashDebug(boolean z);

    public native boolean initJNICrash(String str);

    public native String makeJniCrash();

    public native String stringFromJNI();
}
