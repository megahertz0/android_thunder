package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.ReportPolicy;
import com.umeng.analytics.ReportPolicy.b;
import com.umeng.analytics.ReportPolicy.c;
import com.umeng.analytics.ReportPolicy.d;
import com.umeng.analytics.ReportPolicy.e;
import com.umeng.analytics.ReportPolicy.g;
import com.umeng.analytics.ReportPolicy.i;
import com.umeng.analytics.ReportPolicy.j;
import com.umeng.analytics.ReportPolicy.k;
import com.umeng.analytics.f;
import com.umeng.analytics.h;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: CacheImpl.java
public final class cr implements db, di {
    c a;
    n b;
    m c;
    o d;
    u.aly.cm.a e;
    Context f;
    private final long g;
    private final int h;
    private de i;
    private h j;
    private a k;
    private int l;
    private long m;
    private int n;
    private int o;

    // compiled from: CacheImpl.java
    public class a {
        i a;
        int b;
        int c;
        int d;
        int e;

        public a() {
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            int[] b = cr.this.e.b();
            this.b = b[0];
            this.c = b[1];
        }

        final i a(int i, int i2) {
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    return this.a instanceof ReportPolicy.h ? this.a : new ReportPolicy.h();
                case SimpleLog.LOG_LEVEL_TRACE:
                    return this.a instanceof d ? this.a : new d();
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return this.a instanceof g ? this.a : new g(cr.this.a);
                case SimpleLog.LOG_LEVEL_ERROR:
                    return this.a instanceof j ? this.a : new j(cr.this);
                case SimpleLog.LOG_LEVEL_FATAL:
                    if (!(this.a instanceof e)) {
                        return new e(cr.this.a, (long) i2);
                    }
                    i iVar = this.a;
                    ((e) iVar).a((long) i2);
                    return iVar;
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    return this.a instanceof k ? this.a : new k(cr.this.a);
                default:
                    return this.a instanceof d ? this.a : new d();
            }
        }
    }

    public cr(Context context) {
        this.g = 28800000;
        this.h = 5000;
        this.i = null;
        this.j = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.k = null;
        this.e = null;
        this.l = 10;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.f = context;
        this.i = new de(context);
        this.a = new c(context);
        this.j = h.a(context);
        this.e = cm.a(context).c;
        this.k = new a();
        this.c = m.a(this.f);
        this.b = n.a(this.f);
        this.d = o.a(this.f, this.a);
        SharedPreferences sharedPreferences = this.f.getSharedPreferences("umeng_general_config", 0);
        this.m = sharedPreferences.getLong("thtstart", 0);
        this.n = sharedPreferences.getInt("gkvc", 0);
        this.o = sharedPreferences.getInt("ekvc", 0);
    }

    public final void a() {
        if (t.g(this.f)) {
            d();
        }
    }

    public final void a(dc dcVar) {
        boolean z;
        int i = 0;
        if (dcVar != null) {
            this.i.a(dcVar);
        }
        boolean z2 = dcVar instanceof bn;
        boolean a = this.a.a();
        if (a) {
            long currentTimeMillis;
            de deVar = this.i;
            c cVar = this.a;
            if (cVar.d()) {
                currentTimeMillis = System.currentTimeMillis();
            } else {
                currentTimeMillis = cVar.e;
            }
            deVar.a(new ap(currentTimeMillis));
        }
        if (!t.g(this.f)) {
            z = false;
        } else if (this.a.a()) {
            z = true;
        } else {
            a aVar = this.k;
            int i2;
            if (aVar.f.b.a()) {
                i iVar;
                i2 = ((aVar.a instanceof b) && aVar.a.a()) ? 1 : 0;
                if (i2 != 0) {
                    iVar = aVar.a;
                } else {
                    iVar = new b(aVar.f.a, aVar.f.b);
                }
                aVar.a = iVar;
            } else {
                i2 = ((aVar.a instanceof c) && aVar.a.a()) ? 1 : 0;
                if (i2 == 0) {
                    int i3;
                    if (z2) {
                        cr crVar;
                        int currentTimeMillis2;
                        o oVar = aVar.f.d;
                        if (!(oVar.a.g() || oVar.b.a())) {
                            long currentTimeMillis3 = System.currentTimeMillis() - oVar.b.d;
                            if (currentTimeMillis3 > oVar.c) {
                                oVar.e = (long) com.umeng.analytics.b.a(oVar.d, z.a(oVar.g));
                                oVar.f = currentTimeMillis3;
                                i2 = 1;
                            } else if (currentTimeMillis3 > 129600000) {
                                oVar.e = 0;
                                oVar.f = currentTimeMillis3;
                                i2 = 1;
                            }
                            if (i2 != 0) {
                                aVar.a = new c((int) aVar.f.d.e);
                                crVar = aVar.f;
                                currentTimeMillis2 = (int) (System.currentTimeMillis() - crVar.a.d);
                                crVar.a(crVar.a((int) aVar.f.d.e, currentTimeMillis2));
                                f.a(new cs(crVar), (long) i3);
                            }
                        }
                        i2 = 0;
                        if (i2 != 0) {
                            aVar.a = new c((int) aVar.f.d.e);
                            crVar = aVar.f;
                            currentTimeMillis2 = (int) (System.currentTimeMillis() - crVar.a.d);
                            crVar.a(crVar.a((int) aVar.f.d.e, currentTimeMillis2));
                            f.a(new cs(crVar), (long) i3);
                        }
                    }
                    if (v.a) {
                        if (aVar.f.e.e == 1) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        if (i2 != 0) {
                            aVar.a = new com.umeng.analytics.ReportPolicy.a(aVar.f.a);
                        }
                    }
                    if (aVar.f.c.a) {
                        if (aVar.f.c.b == 6) {
                            if (aVar.f.e.c != -1) {
                                i2 = 1;
                            } else {
                                i2 = 0;
                            }
                            if (i2 != 0) {
                                i2 = aVar.f.e.a(90000);
                            } else if (aVar.c > 0) {
                                i2 = aVar.c;
                            } else {
                                i2 = aVar.e;
                            }
                        } else {
                            i2 = 0;
                        }
                        aVar.a = aVar.a(aVar.f.c.b, i2);
                    } else {
                        i3 = aVar.d;
                        i2 = aVar.e;
                        if (aVar.b != -1) {
                            i3 = aVar.b;
                            i2 = aVar.c;
                        }
                        aVar.a = aVar.a(i3, i2);
                    }
                }
            }
            new StringBuilder("Report policy : ").append(aVar.a.getClass().getSimpleName());
            z = aVar.a.a(z2);
        }
        if (z) {
            d();
            return;
        }
        if (!a) {
            if (this.i.a() > this.l) {
                i = 1;
            }
            if (i == 0) {
                return;
            }
        }
        b();
    }

    public final void b(dc dcVar) {
        this.i.a(dcVar);
    }

    public final void b() {
        if (this.i.a() > 0) {
            try {
                byte[] b = b(a(new int[0]));
                if (b != null) {
                    this.j.a(b);
                }
            } catch (Throwable th) {
                if (th instanceof OutOfMemoryError) {
                    this.j.f();
                }
                th.printStackTrace();
            }
        }
        this.f.getSharedPreferences("umeng_general_config", 0).edit().putLong("thtstart", this.m).putInt("gkvc", this.n).putInt("ekvc", this.o).commit();
    }

    public final void c() {
        a(a(new int[0]));
    }

    private void a(bp bpVar) {
        if (bpVar != null) {
            ck a = ck.a(this.f);
            a.a();
            bpVar.i = a.a;
            byte[] b = b(c(bpVar));
            if (b != null) {
                z b2;
                if (e()) {
                    b2 = z.b(this.f, AnalyticsConfig.getAppkey(this.f), b);
                } else {
                    b2 = z.a(this.f, AnalyticsConfig.getAppkey(this.f), b);
                }
                b = b2.a();
                h a2 = h.a(this.f);
                a2.f();
                a2.b(b);
                a.b();
            }
        }
    }

    private bp a(int... iArr) {
        try {
            if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(this.f))) {
                v.c("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            }
            byte[] e = h.a(this.f).e();
            bp a = e == null ? null : a(e);
            if (a == null && this.i.a() == 0) {
                return null;
            }
            bp bpVar;
            if (a == null) {
                a = new bp();
            }
            de deVar = this.i;
            String f = b.f(deVar.c);
            if (f != null) {
                aq aqVar;
                Map hashMap;
                synchronized (deVar) {
                    if (deVar.b != null && new c(deVar.c).a()) {
                        a.e = deVar.b;
                        deVar.b = null;
                    }
                    for (dc dcVar : deVar.a) {
                        dcVar.a(a, f);
                    }
                    deVar.a.clear();
                }
                a.b = deVar.b();
                a.c = deVar.c();
                a.d = deVar.d();
                a.a = deVar.g();
                a.h = deVar.e();
                a.i = deVar.f();
                String[] a2 = com.umeng.analytics.e.a(deVar.c);
                if (a2 == null || TextUtils.isEmpty(a2[0]) || TextUtils.isEmpty(a2[1])) {
                    aqVar = null;
                } else {
                    aqVar = new aq(a2[0], a2[1]);
                }
                a.j = aqVar;
                m a3 = m.a(deVar.c);
                if (a3.a) {
                    hashMap = new HashMap();
                    hashMap.put("client_test", Integer.valueOf(a3.c));
                } else {
                    hashMap = null;
                }
                a.l = hashMap;
            }
            if (v.a && a.d()) {
                Object obj = null;
                for (bn bnVar : a.g) {
                    int i;
                    Object obj2;
                    if (bnVar.e == null) {
                        i = 0;
                    } else {
                        i = bnVar.e.size();
                    }
                    if (i > 0) {
                        i = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj == null) {
                    v.b("missing Activities or PageViews");
                }
            }
            n nVar = this.b;
            Context context = this.f;
            if (a == null) {
                bpVar = null;
            } else {
                if (nVar.a == 1) {
                    a.f = null;
                } else if (nVar.a == 2) {
                    bn[] bnVarArr = new bn[1];
                    long currentTimeMillis = System.currentTimeMillis();
                    bn bnVar2 = new bn();
                    bnVar2.a = b.f(context);
                    bnVar2.a(currentTimeMillis);
                    bnVar2.b(currentTimeMillis + 60000);
                    bnVar2.c(60000);
                    bnVarArr[0] = bnVar2;
                    a.g = Arrays.asList(bnVarArr);
                    a.f = null;
                } else if (nVar.a == 3) {
                    a.g = null;
                    a.f = null;
                }
                bpVar = a;
            }
            if (iArr.length != 2) {
                return bpVar;
            }
            at atVar = new at();
            atVar.a = new bf(iArr[0] / 1000, (long) iArr[1]);
            bpVar.k = atVar;
            return bpVar;
        } catch (Throwable e2) {
            v.b("Fail to construct message ...", e2);
            h.a(this.f).f();
            return null;
        }
    }

    private static bp a(byte[] bArr) {
        bp bpVar = null;
        if (bArr == null) {
            return null;
        }
        try {
            y bpVar2 = new bp();
            new ab().a(bpVar2, bArr);
            y yVar = bpVar2;
            return yVar;
        } catch (Exception e) {
            e.printStackTrace();
            return bpVar;
        }
    }

    private static byte[] b(bp bpVar) {
        byte[] bArr = null;
        if (bpVar == null) {
            return null;
        }
        try {
            byte[] a = new ae().a(bpVar);
            bpVar.toString();
            bArr = a;
            return bArr;
        } catch (Throwable e) {
            v.b("Fail to serialize log ...", e);
            return bArr;
        }
    }

    private void d() {
        Object obj = 1;
        try {
            dj djVar;
            if (this.j.g()) {
                djVar = new dj(this.f, this.a);
                djVar.a((di) this);
                if (this.b.a()) {
                    djVar.e = true;
                }
                djVar.a();
                return;
            }
            bp a = a(new int[0]);
            if (a == null) {
                v.c("No data to report");
            } else if (!(a.a == null || a.b == null || a.c == null || a.d == null)) {
                if (obj != null) {
                    v.c(" not legitimate!");
                }
                djVar = new dj(this.f, this.a);
                djVar.a((di) this);
                if (this.b.a()) {
                    djVar.e = true;
                }
                djVar.c = c(a);
                djVar.d = e();
                djVar.a();
                return;
            }
            obj = null;
            if (obj != null) {
                djVar = new dj(this.f, this.a);
                djVar.a((di) this);
                if (this.b.a()) {
                    djVar.e = true;
                }
                djVar.c = c(a);
                djVar.d = e();
                djVar.a();
                return;
            }
            v.c(" not legitimate!");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private bp c(bp bpVar) {
        int size;
        int i;
        int i2;
        int i3;
        int i4;
        long currentTimeMillis;
        int i5 = 0;
        int i6 = 5000;
        List list = bpVar.f;
        if (list != null) {
            size = list.size();
            if (size > 0) {
                i = 0;
                i2 = 0;
                for (i3 = 0; i3 < size; i3++) {
                    be beVar = (be) list.get(i3);
                    if (beVar.d == null) {
                        i4 = 0;
                    } else {
                        i4 = beVar.d.size();
                    }
                    i2 += i4;
                    beVar = (be) list.get(i3);
                    if (beVar.c == null) {
                        i4 = 0;
                    } else {
                        i4 = beVar.c.size();
                    }
                    i += i4;
                }
                i5 = i;
                i4 = i2;
                currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.m <= 28800000) {
                    i3 = i4 - 5000;
                    size = i5 - 5000;
                    if (i3 > 0 || size > 0) {
                        a(i3, size, list);
                    }
                    if (i3 > 0) {
                        i4 = 5000;
                    }
                    this.n = i4;
                    if (size <= 0) {
                        i6 = i5;
                    }
                    this.o = i6;
                    this.m = currentTimeMillis;
                } else {
                    i = this.n <= 5000 ? i4 : (this.n + i4) - 5000;
                    i2 = this.o <= 5000 ? i5 : (this.o + i5) - 5000;
                    if (i > 0 || i2 > 0) {
                        a(i, i2, list);
                    }
                    this.n = i <= 0 ? 5000 : i4 + this.n;
                    if (i2 <= 0) {
                        i6 = this.o + i5;
                    }
                    this.o = i6;
                }
                return bpVar;
            }
        }
        i4 = 0;
        currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.m <= 28800000) {
            if (this.n <= 5000) {
            }
            if (this.o <= 5000) {
            }
            a(i, i2, list);
            if (i <= 0) {
            }
            this.n = i <= 0 ? 5000 : i4 + this.n;
            if (i2 <= 0) {
                i6 = this.o + i5;
            }
            this.o = i6;
        } else {
            i3 = i4 - 5000;
            size = i5 - 5000;
            a(i3, size, list);
            if (i3 > 0) {
                i4 = 5000;
            }
            this.n = i4;
            if (size <= 0) {
                i6 = i5;
            }
            this.o = i6;
            this.m = currentTimeMillis;
        }
        return bpVar;
    }

    private static void a(int i, int i2, List<be> list) {
        int i3;
        int size;
        int size2 = list.size();
        if (i > 0) {
            i3 = size2 - 1;
            while (i3 >= 0) {
                List list2 = ((be) list.get(i3)).d;
                if (list2.size() >= i) {
                    i3 = list2.size() - i;
                    for (size = list2.size() - 1; size >= i3; size--) {
                        list2.remove(size);
                    }
                } else {
                    i -= list2.size();
                    list2.clear();
                    i3--;
                }
            }
        }
        if (i2 > 0) {
            for (i3 = size2 - 1; i3 >= 0; i3--) {
                List list3 = ((be) list.get(i3)).c;
                if (list3.size() >= i2) {
                    i3 = list3.size() - i2;
                    for (size = list3.size() - 1; size >= i3; size--) {
                        list3.remove(size);
                    }
                    return;
                }
                i2 -= list3.size();
                list3.clear();
            }
        }
    }

    private boolean e() {
        int i = -1;
        u.aly.cm.a aVar = this.e;
        if (aVar.b == 0 || aVar.b == 1 || aVar.b == -1) {
            i = aVar.b;
        }
        switch (i) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                return AnalyticsConfig.sEncrypt;
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return false;
            case SimpleLog.LOG_LEVEL_TRACE:
                return true;
            default:
                return false;
        }
    }

    public final void a(u.aly.cm.a aVar) {
        this.c.a(aVar);
        this.b.a(aVar);
        this.d.a(aVar);
        a aVar2 = this.k;
        int[] b = aVar.b();
        aVar2.b = b[0];
        aVar2.c = b[1];
    }
}
