package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import com.umeng.message.MsgConstant;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.channel.commonutils.string.c;
import com.xiaomi.push.service.ab;
import com.xiaomi.push.service.z;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.s;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;

public class j {
    private static j b;
    private static final ArrayList<a> e;
    private boolean a;
    private Context c;
    private String d;
    private Intent f;
    private Integer g;

    static {
        e = new ArrayList();
    }

    private j(Context context) {
        this.a = false;
        this.f = null;
        this.g = null;
        this.c = context.getApplicationContext();
        this.d = null;
        this.a = g();
    }

    public static j a(Context context) {
        if (b == null) {
            b = new j(context);
        }
        return b;
    }

    private void a(Intent intent) {
        try {
            this.c.startService(intent);
        } catch (Throwable e) {
            b.a(e);
        }
    }

    private boolean g() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", XZBDevice.DOWNLOAD_LIST_ALL);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Exception e) {
            return false;
        }
    }

    private Intent h() {
        Intent intent = new Intent();
        String packageName = this.c.getPackageName();
        if (!b() || "com.xiaomi.xmsf".equals(packageName)) {
            k();
            intent.setComponent(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"));
            intent.putExtra("mipush_app_package", packageName);
        } else {
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", i());
            intent.putExtra("mipush_app_package", packageName);
            j();
        }
        return intent;
    }

    private String i() {
        try {
            if (this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", XZBDevice.DOWNLOAD_LIST_ALL).versionCode >= 106) {
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception e) {
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    private void j() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), XZBDevice.DOWNLOAD_LIST_RECYCLE, 1);
        } catch (Throwable th) {
        }
    }

    private void k() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), 1, 1);
        } catch (Throwable th) {
        }
    }

    private boolean l() {
        String packageName = this.c.getPackageName();
        return packageName.contains("miui") || packageName.contains(MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI) || (this.c.getApplicationInfo().flags & 1) != 0;
    }

    public void a() {
        a(h());
    }

    public void a(int i) {
        Intent h = h();
        h.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        h.putExtra(z.y, this.c.getPackageName());
        h.putExtra(z.z, i);
        a(h);
    }

    public final void a(s sVar, boolean z) {
        this.f = null;
        Intent h = h();
        byte[] a = ad.a(h.a(this.c, sVar, a.a));
        if (a == null) {
            b.a("register fail, because msgBytes is null.");
            return;
        }
        h.setAction("com.xiaomi.mipush.REGISTER_APP");
        h.putExtra("mipush_app_id", a.a(this.c).c());
        h.putExtra("mipush_payload", a);
        h.putExtra("mipush_session", this.d);
        h.putExtra("mipush_env_chanage", z);
        h.putExtra("mipush_env_type", a.a(this.c).m());
        if (d.d(this.c) && f()) {
            a(h);
        } else {
            this.f = h;
        }
    }

    public final void a(com.xiaomi.xmpush.thrift.z zVar) {
        Intent h = h();
        byte[] a = ad.a(h.a(this.c, zVar, a.b));
        if (a == null) {
            b.a("unregister fail, because msgBytes is null.");
            return;
        }
        h.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        h.putExtra("mipush_app_id", a.a(this.c).c());
        h.putExtra("mipush_payload", a);
        a(h);
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, i iVar) {
        a(t, aVar, !aVar.equals(a.a), iVar);
    }

    public <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z) {
        a aVar2 = new a();
        aVar2.a = t;
        aVar2.b = aVar;
        aVar2.c = z;
        synchronized (e) {
            e.add(aVar2);
            if (e.size() > 10) {
                e.remove(0);
            }
        }
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z, i iVar) {
        a(t, aVar, z, true, iVar, true);
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z, i iVar, boolean z2) {
        a(t, aVar, z, true, iVar, z2);
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z, boolean z2, i iVar, boolean z3) {
        a(t, aVar, z, z2, iVar, z3, this.c.getPackageName(), a.a(this.c).c());
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z, boolean z2, i iVar, boolean z3, String str, String str2) {
        a(t, aVar, z, z2, iVar, z3, str, str2, false);
    }

    public final <T extends org.apache.thrift.b<T, ?>> void a(T t, a aVar, boolean z, boolean z2, i iVar, boolean z3, String str, String str2, boolean z4) {
        if (a.a(this.c).i()) {
            Intent h = h();
            org.apache.thrift.b a = h.a(this.c, t, aVar, z, str, str2, z4);
            if (iVar != null) {
                a.a(iVar);
            }
            byte[] a2 = ad.a(a);
            if (a2 == null) {
                b.a("send message fail, because msgBytes is null.");
                return;
            }
            h.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            h.putExtra("mipush_payload", a2);
            h.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
            a(h);
        } else if (z2) {
            a((org.apache.thrift.b) t, aVar, z);
        } else {
            b.a("drop the message before initialization.");
        }
    }

    public void b(int i) {
        Intent h = h();
        h.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        h.putExtra(z.y, this.c.getPackageName());
        h.putExtra(z.A, i);
        h.putExtra(z.C, c.b(this.c.getPackageName() + i));
        a(h);
    }

    public boolean b() {
        return this.a && 1 == a.a(this.c).m();
    }

    public void c() {
        if (this.f != null) {
            a(this.f);
            this.f = null;
        }
    }

    public void d() {
        synchronized (e) {
            Iterator it = e.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                a(aVar.a, aVar.b, aVar.c, false, null, true);
            }
            e.clear();
        }
    }

    public void e() {
        Intent h = h();
        h.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        h.putExtra(z.y, this.c.getPackageName());
        h.putExtra(z.C, c.b(this.c.getPackageName()));
        a(h);
    }

    public boolean f() {
        if (!b() || !l()) {
            return true;
        }
        if (this.g == null) {
            this.g = Integer.valueOf(ab.a(this.c).b());
            if (this.g.intValue() == 0) {
                this.c.getContentResolver().registerContentObserver(ab.a(this.c).c(), false, new k(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.g.intValue() != 0;
    }
}
