package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.fb.model.Constants;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;

// compiled from: MemoCache.java
public final class de {
    List<dc> a;
    ap b;
    Context c;
    private ar d;
    private au e;
    private bh f;

    public de(Context context) {
        this.a = new ArrayList();
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.c = null;
        this.c = context;
    }

    public final synchronized int a() {
        int size;
        size = this.a.size();
        if (this.b != null) {
            size++;
        }
        return size;
    }

    public final synchronized void a(dc dcVar) {
        this.a.add(dcVar);
    }

    public final synchronized void a(ap apVar) {
        this.b = apVar;
    }

    public final synchronized ar b() {
        if (this.d == null) {
            this.d = new ar();
            Context context = this.c;
            try {
                this.d.a = AnalyticsConfig.getAppkey(context);
                this.d.g = AnalyticsConfig.getChannel(context);
                if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                    this.d.h = AnalyticsConfig.mWrapperType;
                    this.d.i = AnalyticsConfig.mWrapperVersion;
                }
                this.d.d = t.o(context);
                this.d.e = bm.a;
                this.d.f = "5.6.4";
                this.d.b = t.b(context);
                ar arVar = this.d;
                arVar.c = Integer.parseInt(t.a(context));
                arVar.c();
                this.d.k = t.p(context);
                ar arVar2 = this.d;
                arVar2.j = AnalyticsConfig.mVerticalType;
                arVar2.h();
                this.d.f = AnalyticsConfig.getSDKVersion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.d;
    }

    public final synchronized au c() {
        if (this.e == null) {
            this.e = new au();
            Context context = this.c;
            try {
                this.e.f = t.a();
                this.e.a = t.c(context);
                this.e.b = t.d(context);
                this.e.c = t.k(context);
                this.e.e = Build.MODEL;
                this.e.g = Constants.SDK_TYPE;
                this.e.h = VERSION.RELEASE;
                int[] l = t.l(context);
                if (l != null) {
                    this.e.i = new bk(l[1], l[0]);
                }
                if (AnalyticsConfig.GPU_RENDERER != null) {
                    String str = AnalyticsConfig.GPU_VENDER;
                }
                this.e.l = Build.BOARD;
                this.e.m = Build.BRAND;
                au auVar = this.e;
                auVar.n = Build.TIME;
                auVar.q();
                this.e.o = Build.MANUFACTURER;
                this.e.p = Build.ID;
                this.e.q = Build.DEVICE;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.e;
    }

    public final synchronized bh d() {
        if (this.f == null) {
            this.f = new bh();
            Context context = this.c;
            try {
                String[] e = t.e(context);
                if ("Wi-Fi".equals(e[0])) {
                    this.f.i = ao.c;
                } else if ("2G/3G".equals(e[0])) {
                    this.f.i = ao.b;
                } else {
                    this.f.i = ao.a;
                }
                if (!BuildConfig.VERSION_NAME.equals(e[1])) {
                    this.f.j = e[1];
                }
                this.f.f = t.m(context);
                e = t.i(context);
                this.f.h = t.r(context);
                this.f.c = e[0];
                this.f.b = e[1];
                bh bhVar = this.f;
                bhVar.a = t.h(context);
                bhVar.b();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.f;
    }

    public final bc e() {
        try {
            return cm.a(this.c).a();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final bb f() {
        try {
            return ck.a(this.c).a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final as g() {
        try {
            SharedPreferences sharedPreferences = this.c.getSharedPreferences("umeng_general_config", 0);
            as asVar = new as();
            asVar.b = sharedPreferences.getInt("failed_requests ", 0);
            asVar.b();
            asVar.c = sharedPreferences.getInt("last_request_spent_ms", 0);
            asVar.d();
            asVar.a = sharedPreferences.getInt("successful_request", 0);
            asVar.a();
            return asVar;
        } catch (Exception e) {
            e.printStackTrace();
            return new as();
        }
    }
}
