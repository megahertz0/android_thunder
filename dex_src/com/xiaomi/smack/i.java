package com.xiaomi.smack;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import com.xiaomi.push.service.ag;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.g;
import com.xiaomi.smack.util.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Writer;
import java.util.Locale;

class i {
    private Writer a;
    private l b;

    protected i(l lVar) {
        this.b = lVar;
        this.a = lVar.k;
    }

    private void b(d dVar) {
        synchronized (this.a) {
            try {
                String a = dVar.a();
                this.a.write(a + "\r\n");
                this.a.flush();
                Object o = dVar.o();
                if (!TextUtils.isEmpty(o)) {
                    k.a(this.b.p, o, (long) k.a(a), false, System.currentTimeMillis());
                }
            } catch (Throwable e) {
                throw new p(e);
            } catch (Throwable th) {
            }
        }
    }

    void a() {
        this.b.h.clear();
    }

    public void a(d dVar) {
        b(dVar);
        this.b.b(dVar);
    }

    public void b() {
        synchronized (this.a) {
            this.a.write("</stream:stream>");
            this.a.flush();
        }
    }

    void c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<stream:stream");
        stringBuilder.append(" xmlns=\"xm\"");
        stringBuilder.append(" xmlns:stream=\"xm\"");
        stringBuilder.append(" to=\"").append(this.b.b()).append(h.f);
        stringBuilder.append(" version=\"105\"");
        stringBuilder.append(" model=\"").append(g.a(Build.MODEL)).append(h.f);
        stringBuilder.append(" os=\"").append(g.a(VERSION.INCREMENTAL)).append(h.f);
        Object e = ag.e();
        if (!TextUtils.isEmpty(e)) {
            stringBuilder.append(" uid=\"").append(e).append(h.f);
        }
        stringBuilder.append(" sdk=\"19\"");
        stringBuilder.append(" connpt=\"").append(g.a(this.b.d())).append(h.f);
        stringBuilder.append(" host=\"").append(this.b.c()).append(h.f);
        stringBuilder.append(" locale=\"").append(g.a(Locale.getDefault().toString())).append(h.f);
        byte[] a = this.b.a().a();
        if (a != null) {
            stringBuilder.append(" ps=\"").append(Base64.encodeToString(a, XZBDevice.Stop)).append(h.f);
        }
        stringBuilder.append(">");
        this.a.write(stringBuilder.toString());
        this.a.flush();
    }

    public void d() {
        synchronized (this.a) {
            try {
                this.a.write(this.b.u() + "\r\n");
                this.a.flush();
                this.b.w();
            } catch (Throwable e) {
                throw new p(e);
            } catch (Throwable th) {
            }
        }
    }
}
