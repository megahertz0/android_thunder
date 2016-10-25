package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

public class PM {
    private static final Map<Class<?>, String> i;
    private final Context a;
    private String b;
    private File c;
    private int d;
    private DexClassLoader e;
    private RandomAccessFile f;
    private FileLock g;
    private boolean h;

    static {
        i = new HashMap<Class<?>, String>() {
            {
                put(POFactory.class, "com.qq.e.comm.plugin.POFactoryImpl");
            }
        };
    }

    public PM(Context context) {
        this.a = context.getApplicationContext();
        this.h = b();
        if (a()) {
            GDTLogger.d(new StringBuilder("PluginFile:\t").append(this.c == null ? "null" : this.c.getAbsolutePath()).toString());
            if (this.b != null) {
                try {
                    this.e = new DexClassLoader(this.c.getAbsolutePath(), this.a.getDir("e_qq_com_dex", 0).getAbsolutePath(), null, getClass().getClassLoader());
                    return;
                } catch (Throwable th) {
                    GDTLogger.e("exception while init plugin class loader", th);
                }
            }
            this.e = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a() {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.PM.a():boolean");
        /*
        this = this;
        r6 = 532; // 0x214 float:7.45E-43 double:2.63E-321;
        r1 = 1;
        r0 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0113 }
        r3 = "TimeStap_BEFORE_PLUGIN_INIT:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0113 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0113 }
        com.qq.e.comm.util.GDTLogger.d(r2);	 Catch:{ Throwable -> 0x0113 }
        r2 = r7.h;	 Catch:{ Throwable -> 0x0113 }
        if (r2 == 0) goto L_0x0076;
    L_0x001f:
        r2 = new com.qq.e.comm.managers.plugin.c;	 Catch:{ Throwable -> 0x0113 }
        r3 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = com.qq.e.comm.managers.plugin.c.b(r3);	 Catch:{ Throwable -> 0x0113 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r4 = com.qq.e.comm.managers.plugin.c.d(r4);	 Catch:{ Throwable -> 0x0113 }
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x0113 }
        r3 = r2.a();	 Catch:{ Throwable -> 0x0113 }
        if (r3 == 0) goto L_0x0076;
    L_0x0036:
        r3 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = com.qq.e.comm.managers.plugin.c.a(r3);	 Catch:{ Throwable -> 0x0113 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r4 = com.qq.e.comm.managers.plugin.c.c(r4);	 Catch:{ Throwable -> 0x0113 }
        r5 = r2.a;	 Catch:{ Throwable -> 0x0113 }
        r5 = r3.equals(r5);	 Catch:{ Throwable -> 0x0113 }
        if (r5 != 0) goto L_0x0052;
    L_0x004a:
        r5 = r2.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = com.qq.e.comm.util.FileUtil.renameTo(r5, r3);	 Catch:{ Throwable -> 0x0113 }
        if (r3 == 0) goto L_0x00d0;
    L_0x0052:
        r3 = r2.b;	 Catch:{ Throwable -> 0x0113 }
        r3 = r4.equals(r3);	 Catch:{ Throwable -> 0x0113 }
        if (r3 != 0) goto L_0x0062;
    L_0x005a:
        r2 = r2.b;	 Catch:{ Throwable -> 0x0113 }
        r2 = com.qq.e.comm.util.FileUtil.renameTo(r2, r4);	 Catch:{ Throwable -> 0x0113 }
        if (r2 == 0) goto L_0x00d0;
    L_0x0062:
        r2 = r1;
    L_0x0063:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0113 }
        r4 = "NextExist,Updated=";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x0113 }
        r2 = r3.append(r2);	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0113 }
        com.qq.e.comm.util.GDTLogger.d(r2);	 Catch:{ Throwable -> 0x0113 }
    L_0x0076:
        r2 = new com.qq.e.comm.managers.plugin.c;	 Catch:{ Throwable -> 0x0113 }
        r3 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = com.qq.e.comm.managers.plugin.c.a(r3);	 Catch:{ Throwable -> 0x0113 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r4 = com.qq.e.comm.managers.plugin.c.c(r4);	 Catch:{ Throwable -> 0x0113 }
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x0113 }
        r3 = r2.a();	 Catch:{ Throwable -> 0x0113 }
        if (r3 == 0) goto L_0x00ad;
    L_0x008d:
        r3 = r2.d;	 Catch:{ Throwable -> 0x0113 }
        if (r3 >= r6) goto L_0x00d2;
    L_0x0091:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0113 }
        r3 = "last updated plugin version =";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0113 }
        r3 = r7.d;	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0113 }
        r3 = ";asset plugin version=532";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0113 }
        com.qq.e.comm.util.GDTLogger.d(r2);	 Catch:{ Throwable -> 0x0113 }
    L_0x00ad:
        r2 = r0;
    L_0x00ae:
        if (r2 != 0) goto L_0x00b7;
    L_0x00b0:
        r2 = r7.h;	 Catch:{ Throwable -> 0x0113 }
        if (r2 != 0) goto L_0x00e4;
    L_0x00b4:
        r2 = r0;
    L_0x00b5:
        if (r2 == 0) goto L_0x00b8;
    L_0x00b7:
        r0 = r1;
    L_0x00b8:
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
    L_0x00cf:
        return r0;
    L_0x00d0:
        r2 = r0;
        goto L_0x0063;
    L_0x00d2:
        r3 = r2.c;	 Catch:{ Throwable -> 0x0113 }
        r7.b = r3;	 Catch:{ Throwable -> 0x0113 }
        r2 = r2.d;	 Catch:{ Throwable -> 0x0113 }
        r7.d = r2;	 Catch:{ Throwable -> 0x0113 }
        r2 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r2 = com.qq.e.comm.managers.plugin.c.a(r2);	 Catch:{ Throwable -> 0x0113 }
        r7.c = r2;	 Catch:{ Throwable -> 0x0113 }
        r2 = r1;
        goto L_0x00ae;
    L_0x00e4:
        r2 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r3 = com.qq.e.comm.managers.plugin.c.a(r3);	 Catch:{ Throwable -> 0x0113 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r4 = com.qq.e.comm.managers.plugin.c.c(r4);	 Catch:{ Throwable -> 0x0113 }
        r2 = com.qq.e.comm.a.a(r2, r3, r4);	 Catch:{ Throwable -> 0x0113 }
        if (r2 == 0) goto L_0x010b;
    L_0x00f8:
        r2 = "D3yVv2uVyVy1cVHumPP1HGuKmyCaVaO+hCZBEqH1AaTQkHJJmZaYVloA44jjsqT8v7p8FcLKzwVwfyspRhX3asewNyYZ+/X2PfU539OZw0rhiQpjHehflWTkOlAAJhb5NJjZz9vbJ1y6Ydo9th4JPvTTdvxr/miBd8PnWG8iVn4=";
        r7.b = r2;	 Catch:{ Throwable -> 0x0113 }
        r2 = r7.a;	 Catch:{ Throwable -> 0x0113 }
        r2 = com.qq.e.comm.managers.plugin.c.a(r2);	 Catch:{ Throwable -> 0x0113 }
        r7.c = r2;	 Catch:{ Throwable -> 0x0113 }
        r2 = 532; // 0x214 float:7.45E-43 double:2.63E-321;
        r7.d = r2;	 Catch:{ Throwable -> 0x0113 }
        r2 = r1;
        goto L_0x00b5;
    L_0x010b:
        r2 = "Fail to prepair Defult plugin ";
        com.qq.e.comm.util.GDTLogger.e(r2);	 Catch:{ Throwable -> 0x0113 }
        r2 = r0;
        goto L_0x00b5;
    L_0x0113:
        r1 = move-exception;
        r2 = "Exception while init plugin manager";
        com.qq.e.comm.util.GDTLogger.report(r2, r1);	 Catch:{ all -> 0x0132 }
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        goto L_0x00cf;
    L_0x0132:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        throw r0;
        */
    }

    private boolean b() {
        try {
            File file = new File(this.a.getDir("e_qq_com_plugin", 0), "update_lc");
            if (!file.exists()) {
                file.createNewFile();
                StringUtil.writeTo("lock", file);
            }
            if (!file.exists()) {
                return false;
            }
            this.f = new RandomAccessFile(file, "rw");
            this.g = this.f.getChannel().tryLock();
            if (this.g == null) {
                return false;
            }
            this.f.writeByte(XZBDevice.WaitInServer);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public <T> T getFactory(Class<T> cls) throws b {
        GDTLogger.d(new StringBuilder("GetFactoryInstaceforInterface:").append(cls).toString());
        ClassLoader classLoader = this.e;
        if (classLoader == null) {
            throw new b(new StringBuilder("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:").append(cls).toString());
        }
        String str = (String) i.get(cls);
        if (StringUtil.isEmpty(str)) {
            throw new b(new StringBuilder("factory  implemention name is not specified for interface:").append(cls.getName()).toString());
        }
        Class loadClass = classLoader.loadClass(str);
        return cls.cast(loadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(loadClass, new Object[0]));
    }

    public String getLocalSig() {
        return this.b;
    }

    public POFactory getPOFactory() throws b {
        return (POFactory) getFactory(POFactory.class);
    }

    public int getPluginVersion() {
        return this.d;
    }

    public void update(String str, String str2) {
        if (this.h) {
            new a(this.a).a(str, str2);
        }
    }
}
