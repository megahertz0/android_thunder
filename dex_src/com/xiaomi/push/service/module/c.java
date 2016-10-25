package com.xiaomi.push.service.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.i;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.v;
import com.xunlei.xiazaibao.BuildConfig;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyRequest;

public class c {
    private static c a;
    private Map<String, b> b;
    private List<b> c;
    private Context d;
    private SharedPreferences e;
    private boolean f;

    private static class a implements Runnable {
        private String a;
        private String b;
        private String c;
        private boolean d;
        private Context e;
        private boolean f;

        public a(Context context, String str, String str2, String str3, boolean z) {
            this.a = null;
            this.b = null;
            this.f = false;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = context.getApplicationContext();
        }

        public void run() {
            Exception e;
            Throwable th;
            OutputStream outputStream = null;
            if (d.e(this.e)) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
                    httpURLConnection.setRequestMethod(SpdyRequest.GET_METHOD);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        byte[] b = com.xiaomi.channel.commonutils.file.a.b(httpURLConnection.getInputStream());
                        if (!TextUtils.isEmpty(this.b)) {
                            if (!this.b.equalsIgnoreCase(com.xiaomi.channel.commonutils.string.d.a(b))) {
                                b = null;
                            }
                        }
                        if (b != null) {
                            com.xiaomi.channel.commonutils.logger.b.b("download apk success.");
                            try {
                                File file = new File(this.c + ".tmp");
                                file.delete();
                                OutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    fileOutputStream.write(b);
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    if (com.xiaomi.channel.commonutils.android.d.a(com.xiaomi.channel.commonutils.android.b.e(this.e, file.getPath()))) {
                                        com.xiaomi.channel.commonutils.logger.b.b("verify signature success");
                                        file.renameTo(new File(this.c));
                                        this.f = true;
                                        if (this.d && !com.xiaomi.channel.commonutils.android.b.b(this.e)) {
                                            Process.killProcess(Process.myPid());
                                        }
                                    } else {
                                        com.xiaomi.channel.commonutils.logger.b.d("verify signature failed");
                                        file.delete();
                                    }
                                    com.xiaomi.channel.commonutils.file.a.a(null);
                                } catch (Exception e2) {
                                    e = e2;
                                    outputStream = fileOutputStream;
                                    e.printStackTrace();
                                    com.xiaomi.channel.commonutils.file.a.a(outputStream);
                                } catch (Throwable th2) {
                                    th = th2;
                                    outputStream = fileOutputStream;
                                    com.xiaomi.channel.commonutils.file.a.a(outputStream);
                                    throw th;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                try {
                                    e.printStackTrace();
                                    com.xiaomi.channel.commonutils.file.a.a(outputStream);
                                } catch (Throwable th3) {
                                    th = th3;
                                    com.xiaomi.channel.commonutils.file.a.a(outputStream);
                                    throw th;
                                }
                            }
                        }
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public static interface b {
        void a(e eVar);
    }

    static {
        a = null;
    }

    private c(Context context) {
        this.b = new HashMap();
        this.c = new ArrayList();
        this.d = context.getApplicationContext();
        this.e = this.d.getSharedPreferences("mipush_extra", 0);
    }

    private b a(a aVar, DexClassLoader dexClassLoader) {
        if (dexClassLoader == null) {
            return null;
        }
        return new b(aVar.e(), aVar.f(), dexClassLoader, aVar.b(), aVar.a());
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c(context);
            }
            cVar = a;
        }
        return cVar;
    }

    private void a(d dVar, int i) {
        this.e.edit().putInt(new StringBuilder("plugin_version_").append(dVar.b).toString(), i).commit();
    }

    private synchronized void a(e eVar) {
        for (b bVar : this.c) {
            bVar.a(eVar);
        }
    }

    private int b(d dVar) {
        return this.e.getInt(new StringBuilder("plugin_version_").append(dVar.b).toString(), 0);
    }

    private List<e> b() {
        List<e> arrayList = new ArrayList();
        v a = v.a(this.d);
        e eVar = new e();
        eVar.a = d.a;
        eVar.b = a.a(com.xiaomi.xmpush.thrift.b.B.a(), 0);
        eVar.c = a.a(com.xiaomi.xmpush.thrift.b.C.a(), BuildConfig.VERSION_NAME);
        eVar.d = a.a(com.xiaomi.xmpush.thrift.b.D.a(), BuildConfig.VERSION_NAME);
        eVar.e = a.a(com.xiaomi.xmpush.thrift.b.E.a(), false);
        arrayList.add(eVar);
        return arrayList;
    }

    public b a(d dVar) {
        i.a();
        if (dVar == null) {
            return null;
        }
        a();
        com.xiaomi.channel.commonutils.logger.b.b(new StringBuilder("loadModule ").append(dVar.b).toString());
        String str = dVar.b;
        if (this.b.containsKey(str)) {
            return (b) this.b.get(str);
        }
        a aVar = new a(this.d, str);
        DexClassLoader c = aVar.c();
        if (c == null) {
            return null;
        }
        b a = a(aVar, c);
        a.a(this.d);
        this.b.put(str, a);
        com.xiaomi.channel.commonutils.logger.b.b("module load success.");
        return a;
    }

    public synchronized void a() {
        if (!this.f) {
            this.f = true;
            for (e eVar : b()) {
                if (b(eVar.a) < eVar.b && !TextUtils.isEmpty(eVar.c)) {
                    a aVar = new a(this.d, eVar.c, eVar.d, a.a(this.d, eVar.a.b), eVar.e);
                    aVar.run();
                    if (aVar.f) {
                        a(eVar.a, eVar.b);
                        a(eVar);
                    }
                }
            }
            this.f = false;
        }
    }
}
