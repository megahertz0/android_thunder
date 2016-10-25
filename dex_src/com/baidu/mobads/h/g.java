package com.baidu.mobads.h;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

public class g {
    protected static UncaughtExceptionHandler a;
    protected static volatile a b;
    protected static volatile a c;
    protected static final Handler e;
    private static String h;
    protected String d;
    protected Handler f;
    @SuppressLint({"HandlerLeak"})
    protected final Handler g;
    private com.baidu.mobads.openad.e.a i;
    private e j;
    private final Context k;
    private IXAdLogger l;
    private c m;

    public static final class a extends Exception {
        public a(String str) {
            m.a().f().e(str);
        }
    }

    protected static final class b extends Exception {
        public b(String str) {
            m.a().f().e(str);
        }
    }

    public static interface c {
        void a(boolean z);
    }

    static {
        b = null;
        c = null;
        e = new h(Looper.getMainLooper());
    }

    public final String a() {
        return "8.27";
    }

    public g(Context context) {
        this.l = m.a().f();
        this.d = null;
        this.f = e;
        this.g = new i(this, Looper.getMainLooper());
        if (h == null) {
            h = m.a().i().replaceURLWithSupportProtocol("http://mobads.baidu.com/ads/pa/") + com.baidu.mobads.a.b.b() + "/__pasys_remote_banner.php";
        }
        this.k = context;
        this.d = context.getDir("baidu_ad_sdk", 0).getAbsolutePath() + "/";
        if (a == null) {
            a = q.a(context);
            q.a(context).a(new j(this));
        }
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof q)) {
            Thread.setDefaultUncaughtExceptionHandler(a);
        }
    }

    private SharedPreferences j() {
        return this.k.getSharedPreferences("com.baidu.mobads.loader", 0);
    }

    protected void b() {
        new File(e()).delete();
    }

    @TargetApi(9)
    protected void a(String str) {
        if (b != null) {
            Editor edit = j().edit();
            edit.putFloat("__badApkVersion__8.27", (float) b.a);
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    private boolean k() {
        String string = j().getString("previousProxyVersion", null);
        return string == null || !string.equals(a());
    }

    private void a(boolean z) {
        Message obtainMessage = this.f.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putBoolean(MsgConstant.KEY_SUCCESS, z);
        obtainMessage.setData(bundle);
        obtainMessage.what = 0;
        this.f.sendMessage(obtainMessage);
    }

    protected String c() {
        return this.d + "__xadsdk__remote__final__builtin__.jar";
    }

    protected void d() {
        String c = c();
        b bVar = new b(c, this.k);
        try {
            m.a().k().copyFileFromAssetsTo(this.k, "__xadsdk__remote__final__.jar", c);
            if (bVar.exists() && bVar.canRead()) {
                c(bVar);
                return;
            }
            throw new b(new StringBuilder("loadBuiltInApk failed: ").append(c).toString());
        } catch (IOException e) {
            throw new b(new StringBuilder("loadBuiltInApk failed: ").append(e.toString()).toString());
        }
    }

    private void a(b bVar) {
        Class b = bVar.b();
        synchronized (this) {
            c = new a(b, this.k, com.baidu.mobads.a.b.a(), com.baidu.mobads.a.b.a);
        }
    }

    private void b(b bVar) {
        new StringBuilder("len=").append(bVar.length()).append(", path=").append(bVar.getAbsolutePath());
        if (b == null) {
            b = new a(bVar.b(), this.k, com.baidu.mobads.a.b.a(), com.baidu.mobads.a.b.a);
            try {
                this.l.d("XAdApkLoader", new StringBuilder("preloaded apk.version=").append(b.a().getRemoteVersion()).toString());
                return;
            } catch (a e) {
                this.l.w("XAdApkLoader", new StringBuilder("preload local apk ").append(bVar.getAbsolutePath()).append(" failed, msg:").append(e.getMessage()).append(", v=").append(b.a).toString());
                a(e.getMessage());
                throw e;
            }
        }
        this.l.w("XAdApkLoader", new StringBuilder("mApkBuilder already initialized, version: ").append(b.a).toString());
    }

    private void c(b bVar) {
        synchronized (this) {
            b(bVar);
            this.l.d("XAdApkLoader", new StringBuilder("loaded: ").append(bVar.getPath()).toString());
            b(true);
        }
    }

    private void b(boolean z) {
        a(z, z ? "apk Successfully Loaded" : "apk Load Failed");
        new Handler(Looper.getMainLooper()).postDelayed(new k(this, z), 5000);
    }

    private synchronized void l() {
        try {
            if (this.i != null) {
                this.i.removeAllListeners();
                this.i.a();
            }
            this.i = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(boolean z, String str) {
        if (this.m != null) {
            this.m.a(z);
        }
    }

    protected String e() {
        return this.d + "__xadsdk__remote__final__downloaded__.jar";
    }

    protected void f() {
        b bVar = new b(e(), this.k);
        Boolean valueOf = Boolean.valueOf(bVar.exists());
        Boolean valueOf2 = Boolean.valueOf(bVar.canRead());
        long length = bVar.length();
        if (valueOf.booleanValue() && valueOf2.booleanValue() && length > 0) {
            try {
                if (Boolean.valueOf(k()).booleanValue()) {
                    throw new a("XAdApkLoader upgraded, drop stale downloaded file, use built-in instead");
                }
                synchronized (this) {
                    new StringBuilder("loadDownloadedOrBuiltInApk len=").append(bVar.length()).append(", path=").append(bVar.getAbsolutePath());
                    b(bVar);
                    double d = (double) j().getFloat("__badApkVersion__8.27", -1.0f);
                    this.l.d("XAdApkLoader", new StringBuilder("downloadedApkFile.getApkVersion(): ").append(bVar.c()).append(", badApkVersion: ").append(d).toString());
                    if (bVar.c() == d) {
                        throw new a("downloaded file marked bad, drop it and use built-in");
                    }
                    this.l.d("XAdApkLoader", new StringBuilder("loaded: ").append(bVar.getPath()).toString());
                    b(true);
                }
                return;
            } catch (a e) {
                this.l.e("XAdApkLoader", new StringBuilder("load downloaded apk failed: ").append(e.toString()).append(", fallback to built-in").toString());
                if (bVar.exists()) {
                    bVar.delete();
                }
                h();
                try {
                    d();
                } catch (b e2) {
                    throw new a(new StringBuilder("load built-in apk also failed").append(e2.toString()).toString());
                }
            }
        }
        this.l.d("XAdApkLoader", "no downloaded file yet, use built-in apk file");
        try {
            d();
        } catch (b e22) {
            this.l.e("XAdApkLoader", new StringBuilder("loadBuiltInApk failed: ").append(e22.toString()).toString());
            throw new a(new StringBuilder("load built-in apk failed").append(e22.toString()).toString());
        }
    }

    private void a(e eVar) {
        if (eVar.a().booleanValue()) {
            c a = c.a(this.k, eVar, this.d, this.g);
            if (a.isAlive()) {
                this.l.d("XAdApkLoader", "XApkDownloadThread already started");
                a.a(eVar.c());
                return;
            }
            this.l.d("XAdApkLoader", "XApkDownloadThread starting ...");
            a.start();
        }
    }

    private void b(c cVar, Handler handler) {
        this.m = cVar;
        this.f = handler;
        if (b == null) {
            f();
        } else {
            b(true);
        }
    }

    @TargetApi(9)
    public void a(c cVar, Handler handler) {
        new Thread(new n(this, cVar, handler)).start();
    }

    public void a(c cVar) {
        a(cVar, e);
    }

    public IXAdContainerFactory g() {
        return a(b);
    }

    private IXAdContainerFactory a(a aVar) {
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.a();
        } catch (Exception e) {
            return null;
        }
    }

    protected void h() {
        if (b != null) {
            b.b();
            b = null;
        }
    }
}
