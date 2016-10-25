package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.h;
import com.alipay.sdk.app.statistic.c;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.common.Constants;

public class e {
    public static final String b = "failed";
    public Activity a;
    private IAlixPay c;
    private final Object d;
    private boolean e;
    private a f;
    private ServiceConnection g;
    private IRemoteServiceCallback h;

    public static interface a {
        void a();
    }

    public e(Activity activity, a aVar) {
        this.d = IAlixPay.class;
        this.g = new f(this);
        this.h = new g(this);
        this.a = activity;
        this.f = aVar;
    }

    public final String a(String str) {
        try {
            com.alipay.sdk.util.k.a a = k.a(this.a, k.b);
            if (a.a()) {
                return b;
            }
            if (a != null && a.b > 78) {
                Intent intent = new Intent();
                intent.setClassName(k.b, "com.alipay.android.app.TransProcessPayActivity");
                this.a.startActivity(intent);
                Thread.sleep(Constants.ST_UPLOAD_MAX_COUNT);
            }
            return b(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.B, th);
        }
    }

    private void a(com.alipay.sdk.util.k.a aVar) throws InterruptedException {
        if (aVar != null && aVar.b > 78) {
            Intent intent = new Intent();
            intent.setClassName(k.b, "com.alipay.android.app.TransProcessPayActivity");
            this.a.startActivity(intent);
            Thread.sleep(Constants.ST_UPLOAD_MAX_COUNT);
        }
    }

    private String b(String str) {
        String g;
        Intent intent = new Intent();
        intent.setPackage(k.b);
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        String g2 = k.g(this.a);
        try {
            this.a.getApplicationContext().bindService(intent, this.g, 1);
            synchronized (this.d) {
                if (this.c == null) {
                    try {
                        this.d.wait((long) com.alipay.sdk.data.a.b().a());
                    } catch (Throwable e) {
                        com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.z, e);
                    }
                }
            }
            try {
                if (this.c == null) {
                    g = k.g(this.a);
                    com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.t, g2 + "|" + g + "|" + k.h(this.a));
                    g = b;
                    try {
                        this.c.unregisterCallback(this.h);
                    } catch (Throwable th) {
                    }
                    try {
                        this.a.unbindService(this.g);
                    } catch (Throwable th2) {
                    }
                    this.h = null;
                    this.g = null;
                    this.c = null;
                    if (!this.e) {
                        return g;
                    }
                    this.a.setRequestedOrientation(0);
                    this.e = false;
                    return g;
                }
                if (this.f != null) {
                    this.f.a();
                }
                if (this.a.getRequestedOrientation() == 0) {
                    this.a.setRequestedOrientation(1);
                    this.e = true;
                }
                this.c.registerCallback(this.h);
                g = this.c.Pay(str);
                try {
                    this.c.unregisterCallback(this.h);
                } catch (Throwable th3) {
                }
                try {
                    this.a.unbindService(this.g);
                } catch (Throwable th4) {
                }
                this.h = null;
                this.g = null;
                this.c = null;
                if (!this.e) {
                    return g;
                }
                this.a.setRequestedOrientation(0);
                this.e = false;
                return g;
            } catch (Throwable e2) {
                try {
                    com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.w, e2);
                    g = h.a();
                    try {
                        this.c.unregisterCallback(this.h);
                    } catch (Throwable th5) {
                    }
                    try {
                        this.a.unbindService(this.g);
                    } catch (Throwable th6) {
                    }
                    this.h = null;
                    this.g = null;
                    this.c = null;
                    if (!this.e) {
                        return g;
                    }
                    this.a.setRequestedOrientation(0);
                    this.e = false;
                    return g;
                } catch (Throwable th7) {
                    try {
                        this.c.unregisterCallback(this.h);
                    } catch (Throwable th8) {
                    }
                    try {
                        this.a.unbindService(this.g);
                    } catch (Throwable th9) {
                    }
                    this.h = null;
                    this.g = null;
                    this.c = null;
                    if (this.e) {
                        this.a.setRequestedOrientation(0);
                        this.e = false;
                    }
                }
            }
        } catch (Throwable e22) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.y, e22);
            return b;
        }
    }

    private void a() {
        this.a = null;
    }
}
