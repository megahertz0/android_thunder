package com.xiaomi.push.service.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.c;
import com.xiaomi.channel.commonutils.logger.b;
import com.xunlei.xiazaibao.BuildConfig;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import junit.framework.Assert;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class a {
    private Context a;
    private String b;
    private String c;
    private int d;
    private int e;
    private int f;
    private String g;
    private SharedPreferences h;

    public a(Context context, String str) {
        boolean z = false;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.a = context.getApplicationContext();
        this.b = str;
        this.h = this.a.getSharedPreferences("mipush_extra", 0);
        if (!TextUtils.isEmpty(this.b)) {
            z = true;
        }
        Assert.assertEquals(true, z);
    }

    public static String a(Context context, String str) {
        return b(context, str) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + ".apk";
    }

    private String a(String str) {
        try {
            return this.a.getPackageManager().getPackageArchiveInfo(str, 16512).applicationInfo.metaData.getString("Launcher");
        } catch (Exception e) {
            return null;
        }
    }

    private void a(int i) {
        this.h.edit().putInt(this.b + "_asset_app_version", i).commit();
    }

    private void a(long j) {
        this.h.edit().putLong(this.b + "_asset_modified", j).commit();
    }

    private static String b(Context context, String str) {
        return context.getDir(str, 0).getAbsolutePath();
    }

    private void b(int i) {
        this.h.edit().putInt(this.b + "_asset_version", i).commit();
    }

    private void b(long j) {
        this.h.edit().putLong(this.b + "_local_modified", j).commit();
    }

    private void b(String str) {
        this.h.edit().putString(this.b + "_asset_launcher", str).commit();
    }

    private void c(int i) {
        this.h.edit().putInt(this.b + "_local_version", i).commit();
    }

    private void c(String str) {
        this.h.edit().putString(this.b + "_local_launcher", str).commit();
    }

    private boolean g() {
        File file = new File(l());
        return file.exists() && (s() != file.lastModified() || q() == 0);
    }

    private boolean h() {
        File file = new File(d());
        return file.exists() && (t() == file.lastModified() || r() == 0);
    }

    private boolean i() {
        Throwable th;
        ParcelFileDescriptor parcelFileDescriptor;
        Exception exception;
        AssetFileDescriptor assetFileDescriptor;
        Exception exception2;
        boolean z = false;
        File file = new File(l());
        if (file.exists()) {
            AssetFileDescriptor assetFileDescriptor2 = null;
            try {
                assetFileDescriptor2 = this.a.getAssets().openFd(this.b + ".apk");
                try {
                    if (assetFileDescriptor2.getLength() != file.length()) {
                        z = true;
                    }
                } catch (FileNotFoundException e) {
                    try {
                        b.d(new StringBuilder("no ").append(this.b).append(".apk file in assets of app").toString());
                    } catch (Throwable th2) {
                        th = th2;
                        if (assetFileDescriptor2 != null) {
                            com.xiaomi.channel.commonutils.file.a.a(assetFileDescriptor2.getParcelFileDescriptor());
                        }
                        throw th;
                    }
                    if (assetFileDescriptor2 != null) {
                        parcelFileDescriptor = assetFileDescriptor2.getParcelFileDescriptor();
                        com.xiaomi.channel.commonutils.file.a.a(parcelFileDescriptor);
                    }
                } catch (Exception e2) {
                    exception = e2;
                    assetFileDescriptor = assetFileDescriptor2;
                    exception2 = exception;
                    exception2.printStackTrace();
                    if (assetFileDescriptor != null) {
                        parcelFileDescriptor = assetFileDescriptor.getParcelFileDescriptor();
                        com.xiaomi.channel.commonutils.file.a.a(parcelFileDescriptor);
                    }
                    return z;
                }
                if (assetFileDescriptor2 != null) {
                    parcelFileDescriptor = assetFileDescriptor2.getParcelFileDescriptor();
                    com.xiaomi.channel.commonutils.file.a.a(parcelFileDescriptor);
                }
            } catch (FileNotFoundException e3) {
                try {
                    b.d(new StringBuilder("no ").append(this.b).append(".apk file in assets of app").toString());
                } catch (Throwable th22) {
                    th = th22;
                    if (assetFileDescriptor2 != null) {
                        com.xiaomi.channel.commonutils.file.a.a(assetFileDescriptor2.getParcelFileDescriptor());
                    }
                    throw th;
                }
                if (assetFileDescriptor2 != null) {
                    parcelFileDescriptor = assetFileDescriptor2.getParcelFileDescriptor();
                    com.xiaomi.channel.commonutils.file.a.a(parcelFileDescriptor);
                }
            } catch (Exception e22) {
                exception = e22;
                assetFileDescriptor = null;
                exception2 = exception;
                try {
                    exception2.printStackTrace();
                } catch (Throwable th3) {
                    th = th3;
                    assetFileDescriptor2 = assetFileDescriptor;
                    if (assetFileDescriptor2 != null) {
                        com.xiaomi.channel.commonutils.file.a.a(assetFileDescriptor2.getParcelFileDescriptor());
                    }
                    throw th;
                }
                if (assetFileDescriptor != null) {
                    parcelFileDescriptor = assetFileDescriptor.getParcelFileDescriptor();
                    com.xiaomi.channel.commonutils.file.a.a(parcelFileDescriptor);
                }
                return z;
            }
        }
        return z;
    }

    private void j() {
        String str;
        try {
            b.b("copyAssetFile start");
            String[] list = this.a.getAssets().list(BuildConfig.VERSION_NAME);
            if (list != null) {
                for (String str2 : list) {
                    if (!TextUtils.isEmpty(str2) && str2.startsWith(this.b)) {
                        c.a(this.a, str2, l());
                        str = "copyAssetFile end";
                        break;
                    }
                }
            }
            str = "copyAssetFile end";
        } catch (Exception e) {
            e.printStackTrace();
            str = "copyAssetFile end";
        }
        b.b(str);
    }

    private String k() {
        return b(this.a, this.b);
    }

    private String l() {
        return k() + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.b + "_asset.apk";
    }

    private String m() {
        return k() + "/lib/";
    }

    private String n() {
        return k() + "/asset_lib/";
    }

    private boolean o() {
        try {
            return p() != com.xiaomi.channel.commonutils.android.b.b(this.a, this.a.getPackageName());
        } catch (Exception e) {
            return true;
        }
    }

    private int p() {
        try {
            return this.h.getInt(this.b + "_asset_app_version", 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private int q() {
        try {
            return this.h.getInt(this.b + "_asset_version", 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private int r() {
        try {
            return this.h.getInt(this.b + "_local_version", 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private long s() {
        try {
            return this.h.getLong(this.b + "_asset_modified", 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private long t() {
        try {
            return this.h.getLong(this.b + "_local_modified", 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private String u() {
        try {
            return this.h.getString(this.b + "_asset_launcher", null);
        } catch (Exception e) {
            return null;
        }
    }

    private String v() {
        try {
            return this.h.getString(this.b + "_local_launcher", null);
        } catch (Exception e) {
            return null;
        }
    }

    public int a() {
        return this.d;
    }

    public String b() {
        return this.g;
    }

    public synchronized DexClassLoader c() {
        DexClassLoader dexClassLoader;
        Object obj = 1;
        synchronized (this) {
            try {
                String d;
                File file;
                b.b(new StringBuilder("load apk ").append(this.b).toString());
                String l = l();
                File file2 = new File(l);
                boolean i = i();
                b.b(new StringBuilder("assert app size changed : ").append(i).toString());
                if (!file2.exists() || i || o()) {
                    b.b("re-copy asset file");
                    j();
                }
                if (file2.exists()) {
                    b.b("check modify.");
                    if (g() || i || o()) {
                        b.b("modified.");
                        this.e = com.xiaomi.channel.commonutils.android.b.c(this.a, l);
                        b(this.e);
                        a(com.xiaomi.channel.commonutils.android.b.b(this.a, this.a.getPackageName()));
                        a(file2.lastModified());
                        int i2 = 1;
                        d = d();
                        file = new File(d);
                        if (file.exists()) {
                            if (h()) {
                                this.f = r();
                            } else {
                                this.f = com.xiaomi.channel.commonutils.android.b.c(this.a, d);
                                c(this.f);
                                b(file.lastModified());
                                b.b(new StringBuilder("asset version ").append(this.e).toString());
                                b.b(new StringBuilder("local version ").append(this.f).toString());
                                if (this.e < this.f) {
                                    if (this.e > this.d) {
                                        this.d = this.e;
                                        this.c = l();
                                        if (r2 == null) {
                                            this.g = a(this.c);
                                            b(this.g);
                                            com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                                        } else {
                                            this.g = u();
                                        }
                                        dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                                        b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                                        b.b("load apk done.");
                                    }
                                } else if (this.f > this.d) {
                                    this.d = this.f;
                                    this.c = d();
                                    if (obj == null) {
                                        this.g = a(this.c);
                                        c(this.g);
                                        com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                                    } else {
                                        this.g = v();
                                    }
                                    dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                                    b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                                    b.b("load apk done.");
                                }
                                b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                                b.b("load apk done.");
                                dexClassLoader = null;
                            }
                        }
                        obj = null;
                        b.b(new StringBuilder("asset version ").append(this.e).toString());
                        b.b(new StringBuilder("local version ").append(this.f).toString());
                        if (this.e < this.f) {
                            if (this.f > this.d) {
                                this.d = this.f;
                                this.c = d();
                                if (obj == null) {
                                    this.g = v();
                                } else {
                                    this.g = a(this.c);
                                    c(this.g);
                                    com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                                }
                                dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                                b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                                b.b("load apk done.");
                            }
                        } else if (this.e > this.d) {
                            this.d = this.e;
                            this.c = l();
                            if (r2 == null) {
                                this.g = u();
                            } else {
                                this.g = a(this.c);
                                b(this.g);
                                com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                            }
                            dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                            b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                            b.b("load apk done.");
                        }
                        b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                        b.b("load apk done.");
                        dexClassLoader = null;
                    } else {
                        b.b("not modified.");
                        this.e = q();
                    }
                }
                Object obj2 = null;
                d = d();
                file = new File(d);
                if (file.exists()) {
                    if (h()) {
                        this.f = r();
                    } else {
                        this.f = com.xiaomi.channel.commonutils.android.b.c(this.a, d);
                        c(this.f);
                        b(file.lastModified());
                        b.b(new StringBuilder("asset version ").append(this.e).toString());
                        b.b(new StringBuilder("local version ").append(this.f).toString());
                        if (this.e < this.f) {
                            if (this.e > this.d) {
                                this.d = this.e;
                                this.c = l();
                                if (obj2 == null) {
                                    this.g = a(this.c);
                                    b(this.g);
                                    com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                                } else {
                                    this.g = u();
                                }
                                dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                                b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                                b.b("load apk done.");
                            }
                        } else if (this.f > this.d) {
                            this.d = this.f;
                            this.c = d();
                            if (obj == null) {
                                this.g = a(this.c);
                                c(this.g);
                                com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                            } else {
                                this.g = v();
                            }
                            dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                            b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                            b.b("load apk done.");
                        }
                        b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                        b.b("load apk done.");
                        dexClassLoader = null;
                    }
                }
                obj = null;
                b.b(new StringBuilder("asset version ").append(this.e).toString());
                b.b(new StringBuilder("local version ").append(this.f).toString());
                if (this.e < this.f) {
                    if (this.f > this.d) {
                        this.d = this.f;
                        this.c = d();
                        if (obj == null) {
                            this.g = v();
                        } else {
                            this.g = a(this.c);
                            c(this.g);
                            com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                        }
                        dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                        b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                        b.b("load apk done.");
                    }
                } else if (this.e > this.d) {
                    this.d = this.e;
                    this.c = l();
                    if (obj2 == null) {
                        this.g = u();
                    } else {
                        this.g = a(this.c);
                        b(this.g);
                        com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                    }
                    dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                    b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                    b.b("load apk done.");
                }
                b.b(new StringBuilder("load apk : ").append(this.c).append(", version : ").append(this.d).toString());
                b.b("load apk done.");
                dexClassLoader = null;
            } catch (Throwable th) {
            }
        }
        return dexClassLoader;
    }

    public String d() {
        return a(this.a, this.b);
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }
}
