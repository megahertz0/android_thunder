package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.h;

// compiled from: StatTracer.java
public final class c implements dd {
    public int a;
    public int b;
    public long c;
    long d;
    long e;
    private final int f;
    private int g;
    private Context h;

    public c(Context context) {
        this.f = 3600000;
        this.d = 0;
        this.e = 0;
        this.h = context.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        this.a = sharedPreferences.getInt("successful_request", 0);
        this.b = sharedPreferences.getInt("failed_requests ", 0);
        this.g = sharedPreferences.getInt("last_request_spent_ms", 0);
        this.c = sharedPreferences.getLong("last_request_time", 0);
        this.d = sharedPreferences.getLong("last_req", 0);
    }

    public final boolean a() {
        boolean z;
        boolean z2;
        if (this.c == 0) {
            z = true;
        } else {
            Object obj = null;
        }
        if (h.a(this.h).g()) {
            Object obj2 = null;
        } else {
            z2 = true;
        }
        return z && z2;
    }

    public final void b() {
        this.h.getSharedPreferences("umeng_general_config", 0).edit().putInt("successful_request", this.a).putInt("failed_requests ", this.b).putInt("last_request_spent_ms", this.g).putLong("last_request_time", this.c).putLong("last_req", this.d).commit();
    }

    public final void c() {
        this.h.getSharedPreferences("umeng_general_config", 0).edit().putLong("first_activate_time", System.currentTimeMillis()).commit();
    }

    public final boolean d() {
        if (this.e == 0) {
            this.e = this.h.getSharedPreferences("umeng_general_config", 0).getLong("first_activate_time", 0);
        }
        return this.e == 0;
    }

    public final void e() {
        this.d = System.currentTimeMillis();
    }

    public final void f() {
        this.g = (int) (System.currentTimeMillis() - this.d);
    }

    public final void g() {
        this.a++;
        this.c = this.d;
    }

    public final void h() {
        this.b++;
    }
}
