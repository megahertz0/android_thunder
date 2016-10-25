package com.baidu.mobads.h;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mobads.h.o.a;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class c extends Thread {
    private static volatile c f;
    a a;
    private volatile String b;
    private String c;
    private double d;
    private Handler e;
    private final Context g;
    private o h;
    private final e i;
    private IXAdLogger j;

    public static c a(Context context, e eVar, String str, Handler handler) {
        if (f == null) {
            f = new c(context, eVar, str, handler);
        }
        return f;
    }

    private c(Context context, e eVar, String str, Handler handler) {
        this.c = null;
        this.h = null;
        this.j = m.a().f();
        this.a = new d(this);
        this.g = context;
        this.i = eVar;
        a(eVar.c());
        this.e = handler;
        this.c = str;
    }

    public void a(String str) {
        this.b = str;
        interrupt();
    }

    public void run() {
        try {
            if (b()) {
                try {
                    a();
                    this.j.d("XAdApkDownloadThread", "download apk successfully, downloader exit");
                    f = null;
                } catch (IOException e) {
                    this.j.e("XAdApkDownloadThread", new StringBuilder("create File or HTTP Get failed, exception: ").append(e.getMessage()).toString());
                }
                this.j.d("XAdApkDownloadThread", "no newer apk, downloader exit");
                f = null;
            }
        } catch (Throwable th) {
        }
    }

    private void a(String str, e eVar, String str2) {
        if (str.equals("OK") || str.equals("ERROR")) {
            Message obtainMessage = this.e.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putParcelable("APK_INFO", eVar);
            bundle.putString("CODE", str);
            obtainMessage.setData(bundle);
            this.e.sendMessage(obtainMessage);
        }
    }

    private String a() {
        String toString = new StringBuilder("__xadsdk__remote__final__").append(UUID.randomUUID().toString()).append(".jar").toString();
        String str = this.c + toString;
        File file = new File(str);
        try {
            file.createNewFile();
            this.h.a(this.c, toString);
            return str;
        } catch (IOException e) {
            file.delete();
            throw e;
        }
    }

    private boolean b() {
        double d;
        try {
            this.h = new o(this.g, new URL(this.b), this.i, this.a);
        } catch (MalformedURLException e) {
            try {
                this.h = new o(this.g, this.b, this.i, this.a);
            } catch (Exception e2) {
                String toString = new StringBuilder("parse apk failed, error:").append(e2.toString()).toString();
                this.j.e("XAdApkDownloadThread", toString);
                throw new g.a(toString);
            }
        }
        if (g.c != null) {
            d = g.c.a;
        } else if (g.b != null) {
            a aVar = g.b;
            d = g.b.a;
        } else {
            d = 0.0d;
        }
        this.j.d("XAdApkDownloadThread", new StringBuilder("isNewApkAvailable: local apk version is: ").append(d).append(", remote apk version: ").append(this.i.b()).toString());
        if (d > 0.0d) {
            if (this.i.b() > 0.0d) {
                this.j.e("XAdApkDownloadThread", "remote not null, local apk version is null, force upgrade");
                this.d = this.i.b();
                return true;
            }
            this.j.e("XAdApkDownloadThread", "remote is null, local apk version is null, do not upgrade");
            return false;
        } else if (this.i.b() <= 0.0d) {
            this.j.e("XAdApkDownloadThread", new StringBuilder("remote apk version is: null, local apk version is: ").append(d).append(", do not upgrade").toString());
            return false;
        } else if (this.i.b() <= d) {
            return false;
        } else {
            this.d = this.i.b();
            return true;
        }
    }
}
