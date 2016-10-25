package com.xunlei.downloadprovider.a;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.d.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

// compiled from: SDCardUtil.java
public class k {
    private static String a;
    private static String b;
    private static boolean c;
    private static String[] d;

    static {
        a = a.d;
        b = k.class.getSimpleName();
        c = false;
        d = null;
    }

    public static void a(Context context) {
        d = b(context);
    }

    private static String f() {
        if (g()) {
            return a;
        }
        if (!TextUtils.isEmpty(a)) {
            return (a(a) == 0 || !new File(a).exists()) ? a.d : a;
        } else if (c) {
            return a;
        } else {
            c = true;
            String b = b();
            long a = a(b);
            try {
                Process exec = Runtime.getRuntime().exec("df");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split(" ");
                    if (split != null) {
                        for (int i = 0; i < split.length; i++) {
                            String trim = split[i].trim();
                            if (trim.startsWith(File.separator + "mnt") || trim.startsWith(File.separator + "storage")) {
                                while (trim.endsWith(":")) {
                                    trim = trim.substring(0, trim.length() - 1);
                                }
                                String str;
                                if (trim.endsWith(File.separator)) {
                                    str = trim;
                                } else {
                                    str = trim + File.separator;
                                }
                                if (b.endsWith(File.separator)) {
                                    readLine = b;
                                } else {
                                    readLine = b + File.separator;
                                }
                                if (!readLine.equals(r2) && !"/mnt/secure/asec".equals(trim)) {
                                    long a2 = a(trim);
                                    if (a2 != 0 && a2 != a && a2 > 0) {
                                        File file = new File(trim);
                                        if (!c.a(file) && file.exists() && file.canRead() && file.canWrite()) {
                                            a = trim;
                                            if (!trim.endsWith(File.separator)) {
                                                a += File.separator;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                exec.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a;
        }
    }

    public static void a() {
        c = false;
        f();
    }

    public static String b() {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (path != null && !path.endsWith("/")) {
            path = path + "/";
            if (g()) {
                return a.d;
            }
            if (a(path) == 0) {
                return a.d;
            }
        } else if (path != null) {
            path = a.d;
        }
        if (!TextUtils.isEmpty(path) || d == null || d.length <= 0) {
            return path;
        }
        int length = d.length;
        int i = 0;
        String str = path;
        while (i < length) {
            path = d[i];
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            if (!TextUtils.isEmpty(r1) || !r0.contains("emulated") || a(r0) == 0) {
                CharSequence charSequence = r1;
            }
            i++;
            str = path;
        }
        return r1;
    }

    public static String c() {
        String f = f();
        if (d != null && d.length > 0) {
            int length = d.length;
            int i = 0;
            while (i < length) {
                String str = d[i];
                if (!str.endsWith("/")) {
                    str = str + "/";
                }
                if (!(!TextUtils.isEmpty(f) || str.contains("emulated") || str.contains("sdcard0"))) {
                    File file = new File(str);
                    if (a(str) != 0 && file.exists()) {
                        i++;
                        f = str;
                    }
                }
                str = f;
                i++;
                f = str;
            }
        }
        return f;
    }

    public static boolean d() {
        if (g()) {
            return false;
        }
        try {
            return "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean g() {
        try {
            return "checking".equalsIgnoreCase(Environment.getExternalStorageState());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long a(java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.a.k.a(java.lang.String):long");
        /*
        r2 = 0;
        r0 = android.text.TextUtils.isEmpty(r8);
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return r2;
    L_0x0009:
        r1 = new java.io.File;
        r1.<init>(r8);
        r0 = 0;
        r0 = com.xunlei.downloadprovider.d.c.a(r1);	 Catch:{ IOException -> 0x0037 }
    L_0x0013:
        r4 = r1.exists();
        if (r4 == 0) goto L_0x0008;
    L_0x0019:
        r1 = r1.isDirectory();
        if (r1 == 0) goto L_0x0008;
    L_0x001f:
        if (r0 != 0) goto L_0x0008;
    L_0x0021:
        r0 = new android.os.StatFs;	 Catch:{ Exception -> 0x0048 }
        r0.<init>(r8);	 Catch:{ Exception -> 0x0048 }
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0048 }
        r4 = 18;
        if (r1 < r4) goto L_0x003c;
    L_0x002c:
        r4 = r0.getBlockSizeLong();	 Catch:{ Exception -> 0x0048 }
        r0 = r0.getBlockCountLong();	 Catch:{ Exception -> 0x0052 }
        r2 = r4;
    L_0x0035:
        r2 = r2 * r0;
        goto L_0x0008;
    L_0x0037:
        r4 = move-exception;
        r4.printStackTrace();
        goto L_0x0013;
    L_0x003c:
        r1 = r0.getBlockSize();	 Catch:{ Exception -> 0x0048 }
        r4 = (long) r1;
        r0 = r0.getBlockCount();	 Catch:{ Exception -> 0x0052 }
        r0 = (long) r0;
        r2 = r4;
        goto L_0x0035;
    L_0x0048:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
    L_0x004b:
        r4.printStackTrace();
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0035;
    L_0x0052:
        r0 = move-exception;
        r6 = r0;
        r0 = r4;
        r4 = r6;
        goto L_0x004b;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long b(java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.a.k.b(java.lang.String):long");
        /*
        r2 = 0;
        r0 = android.text.TextUtils.isEmpty(r8);
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return r2;
    L_0x0009:
        r1 = new java.io.File;
        r1.<init>(r8);
        r0 = 0;
        r0 = com.xunlei.downloadprovider.d.c.a(r1);	 Catch:{ IOException -> 0x0037 }
    L_0x0013:
        r4 = r1.exists();
        if (r4 == 0) goto L_0x0008;
    L_0x0019:
        r1 = r1.isDirectory();
        if (r1 == 0) goto L_0x0008;
    L_0x001f:
        if (r0 != 0) goto L_0x0008;
    L_0x0021:
        r0 = new android.os.StatFs;	 Catch:{ Exception -> 0x0048 }
        r0.<init>(r8);	 Catch:{ Exception -> 0x0048 }
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0048 }
        r4 = 18;
        if (r1 < r4) goto L_0x003c;
    L_0x002c:
        r4 = r0.getBlockSizeLong();	 Catch:{ Exception -> 0x0048 }
        r0 = r0.getAvailableBlocksLong();	 Catch:{ Exception -> 0x0052 }
        r2 = r4;
    L_0x0035:
        r2 = r2 * r0;
        goto L_0x0008;
    L_0x0037:
        r4 = move-exception;
        r4.printStackTrace();
        goto L_0x0013;
    L_0x003c:
        r1 = r0.getBlockSize();	 Catch:{ Exception -> 0x0048 }
        r4 = (long) r1;
        r0 = r0.getAvailableBlocks();	 Catch:{ Exception -> 0x0052 }
        r0 = (long) r0;
        r2 = r4;
        goto L_0x0035;
    L_0x0048:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
    L_0x004b:
        r4.printStackTrace();
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0035;
    L_0x0052:
        r0 = move-exception;
        r6 = r0;
        r0 = r4;
        r4 = r6;
        goto L_0x004b;
        */
    }

    private static String[] b(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            return (String[]) storageManager.getClass().getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static String e() {
        String path = Environment.getExternalStorageDirectory().getPath();
        return !path.endsWith("/") ? path + "/" : path;
    }
}
