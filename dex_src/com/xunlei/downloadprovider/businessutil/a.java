package com.xunlei.downloadprovider.businessutil;

import android.annotation.SuppressLint;
import android.content.Context;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: DownloadConfig.java
public final class a {
    private static String a;
    private static final String b;

    static {
        a = "Android/obb/com.xunlei.downloadprovider";
        b = a + "/";
    }

    @SuppressLint({"NewApi"})
    public static void a(Context context) {
        if (b.i() >= 19) {
            a = "Android/obb/com.xunlei.downloadprovider";
            try {
                context.getObbDir();
            } catch (Exception e) {
                e.printStackTrace();
            }
            b.a().a(a);
            return;
        }
        a = "ThunderDownload";
    }

    public static String a() {
        String str;
        int i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        Object obj = 1;
        int d = b.a().d();
        String a = a(d, false);
        if (a.equals("##noexist##")) {
            boolean z;
            boolean z2;
            String str2;
            boolean z3;
            String b = k.b();
            a = k.c();
            String str3 = com.umeng.a.d;
            if (a == null || a.length() <= 0 || k.a(a) <= 0) {
                z = false;
            } else {
                int i2 = 1;
            }
            if (b == null || b.length() <= 0 || k.a(b) <= 0) {
                z2 = false;
            } else {
                int i3 = 1;
            }
            if (z && z2) {
                if (k.b(a) >= k.b(b)) {
                    str2 = a;
                } else {
                    i = 1;
                    str2 = b;
                }
            } else if (z) {
                str2 = a;
            } else if (z2) {
                i = 1;
                str2 = b;
            } else {
                z3 = false;
                str2 = str3;
            }
            if (!str2.endsWith("/")) {
                str2 = str2 + "/";
            }
            str2 = str2 + b;
            if (z3) {
                a(i, a);
            }
            str = str2;
        } else {
            str = d == 1 ? k.b() + a : d == 2 ? k.c() + a : a;
        }
        return !str.endsWith("/") ? str + "/" : str;
    }

    public static String a(int i, boolean z) {
        if (!(i == 1 || i == 2)) {
            i = b.a().d();
        }
        String str = "##noexist##";
        if (1 == i) {
            str = b.a().a.getSharedPreferences("settingstate", 0).getString("name_real_primary_download_path", "##noexist##");
        } else if (2 == i) {
            str = b.a().a.getSharedPreferences("settingstate", 0).getString("name_real_slave_download_path", "##noexist##");
        }
        return ("##noexist##".equals(str) && z) ? a : str;
    }

    public static String a(int i, String str) {
        b.a().a(i);
        if (1 == i) {
            b.a().a.getSharedPreferences("settingstate", 0).edit().putString("name_real_primary_download_path", str).commit();
        } else if (2 == i) {
            b.a().a(str);
        }
        return str;
    }

    public static boolean b() {
        try {
            return new File(j()).exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static String j() {
        String str;
        String b = k.b();
        String c = k.c();
        int c2 = b.a().c();
        if (c == null || c.length() <= 0 || k.a(c) <= 0) {
            Object obj = null;
        } else {
            int i = 1;
        }
        if (b == null || b.length() <= 0 || k.a(b) <= 0) {
            Object obj2 = null;
        } else {
            int i2 = 1;
        }
        if (c2 == 2) {
            str = c;
        } else {
            str = b;
        }
        if (obj == null || obj2 == null) {
            if (obj != null && c2 == 1) {
                str = c;
            } else if (obj2 != null && c2 == 2) {
                str = b;
            }
        }
        return str == null ? com.umeng.a.d : str;
    }

    public static long c() {
        return k.b(j());
    }

    public static String d() {
        return j() + b;
    }

    public static String e() {
        return j() + b + "funtime/";
    }

    public static String f() {
        return d() + ".Torrent";
    }

    public static String g() {
        return a;
    }

    public static String b(Context context) {
        return new StringBuilder("/data/data/").append(context.getPackageName()).append("/Thunder/").toString();
    }

    public static String h() {
        return j() + b;
    }

    public static String i() {
        return j() + b + "ApkIconCache/";
    }
}
