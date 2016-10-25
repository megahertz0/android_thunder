package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import u.aly.b;
import u.aly.ct;
import u.aly.cz;
import u.aly.da;
import u.aly.dh;
import u.aly.f;
import u.aly.g;
import u.aly.h;
import u.aly.i;
import u.aly.j;
import u.aly.l;
import u.aly.v;

// compiled from: InternalAgent.java
public class d implements dh {
    private Context a;
    private c b;
    private cz c;
    private f d;
    private b e;
    private da f;
    private ct g;
    private boolean h;

    // compiled from: InternalAgent.java
    class AnonymousClass_1 extends g {
        final /* synthetic */ Context a;

        AnonymousClass_1(Context context) {
            this.a = context;
        }

        public void a() {
            d.this.f(this.a.getApplicationContext());
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_2 extends g {
        final /* synthetic */ Context a;

        AnonymousClass_2(Context context) {
            this.a = context;
        }

        public void a() {
            d.this.g(this.a.getApplicationContext());
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_3 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_3(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a() {
            da a = d.this.f;
            String str = this.a;
            String str2 = this.b;
            if (da.a(str) && da.b(str2)) {
                a.a.a(h.a(str, str2), h.a(str, str2, null));
            }
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_4 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_4(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a() {
            da a = d.this.f;
            String str = this.a;
            String str2 = this.b;
            if (da.a(str) && da.b(str2)) {
                g a2 = a.a.a(h.a(str, str2));
                if (a2 != null) {
                    a.a(str, str2, (long) ((int) (System.currentTimeMillis() - a2.a)), 0);
                }
            }
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_5 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ HashMap b;
        final /* synthetic */ String c;

        AnonymousClass_5(String str, HashMap hashMap, String str2) {
            this.a = str;
            this.b = hashMap;
            this.c = str2;
        }

        public void a() {
            da a = d.this.f;
            String str = this.a;
            Map map = this.b;
            String str2 = this.c;
            if (da.a(str) && da.a(map)) {
                a.a.a(h.a(str, str2), h.a(str, str2, map));
            }
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_6 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_6(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a() {
            da a = d.this.f;
            String str = this.a;
            String str2 = this.b;
            if (da.a(str)) {
                g a2 = a.a.a(h.a(str, str2));
                if (a2 != null) {
                    a.a(str, a2.d, (long) ((int) (System.currentTimeMillis() - a2.a)));
                }
            }
        }
    }

    // compiled from: InternalAgent.java
    class AnonymousClass_7 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_7(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a() {
            String[] a = e.a(d.this.a);
            if (a == null || !this.a.equals(a[0]) || !this.b.equals(a[1])) {
                d.this.a();
                boolean d = b.d(d.this.a);
                ct.a(d.this.a).c();
                if (d) {
                    d.this.a();
                    b.e(d.this.a);
                }
                e.a(d.this.a, this.a, this.b);
            }
        }
    }

    d() {
        this.a = null;
        this.c = new cz();
        this.d = new f();
        this.e = new b();
        this.h = false;
        this.c.a = this;
    }

    private void e(Context context) {
        if (!this.h) {
            this.a = context.getApplicationContext();
            this.f = new da(this.a);
            this.g = ct.a(this.a);
            this.h = true;
        }
    }

    void a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.b(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(int i) {
        AnalyticsConfig.mVerticalType = i;
    }

    public void a(String str, String str2) {
        AnalyticsConfig.mWrapperType = str;
        AnalyticsConfig.mWrapperVersion = str2;
    }

    void a(Context context) {
        if (context == null) {
            v.c("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.a(context.getClass().getName());
        }
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new AnonymousClass_1(context));
        } catch (Throwable e) {
            v.b("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    void b(Context context) {
        if (context == null) {
            v.c("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.b(context.getClass().getName());
        }
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new AnonymousClass_2(context));
        } catch (Throwable e) {
            v.b("Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    public b a() {
        return this.e;
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!this.h) {
                e(context);
            }
            da daVar = this.f;
            if (da.a(str)) {
                daVar.b.a(new j(str, hashMap));
            }
        } catch (Throwable e) {
            v.b("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                v.c("unexpected null context in reportError");
                return;
            }
            try {
                if (!this.h) {
                    e(context);
                }
                this.g.a(new i(str).a());
            } catch (Throwable e) {
                v.a(e);
            }
        }
    }

    void a(Context context, Throwable th) {
        if (context != null && th != null) {
            try {
                if (!this.h) {
                    e(context);
                }
                this.g.a(new i(th).a());
            } catch (Throwable e) {
                v.a(e);
            }
        }
    }

    private void f(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        if (sharedPreferences != null) {
            Editor edit;
            if (b.a(sharedPreferences)) {
                ct a = ct.a(context);
                String b = b.b(context);
                l a2 = b.a(context);
                edit = sharedPreferences.edit();
                edit.putString("session_id", b);
                edit.putLong("session_start_time", System.currentTimeMillis());
                edit.putLong("session_end_time", 0);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                if (a2 != null) {
                    a.a(a2);
                } else {
                    a.a(null);
                }
                v.a(new StringBuilder("Start new session: ").append(b).toString());
            } else {
                String string = sharedPreferences.getString("session_id", null);
                edit = sharedPreferences.edit();
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                v.a(new StringBuilder("Extend current session: ").append(string).toString());
            }
        }
        if (this.b != null) {
            this.b.a();
        }
    }

    private void g(Context context) {
        b.c(context);
        f fVar = this.d;
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        Editor edit = sharedPreferences.edit();
        if (fVar.a.size() > 0) {
            Object string = sharedPreferences.getString("activities", d);
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(string);
                stringBuilder.append(com.alipay.sdk.util.h.b);
            }
            synchronized (fVar.a) {
                Iterator it = fVar.a.iterator();
                while (it.hasNext()) {
                    u.aly.d dVar = (u.aly.d) it.next();
                    stringBuilder.append(String.format("[\"%s\",%d]", new Object[]{dVar.a, Long.valueOf(dVar.b)}));
                    stringBuilder.append(com.alipay.sdk.util.h.b);
                }
                fVar.a.clear();
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            edit.remove("activities");
            edit.putString("activities", stringBuilder.toString());
        }
        edit.commit();
        if (this.b != null) {
            this.b.b();
        }
        this.g.b();
    }

    void c(Context context) {
        try {
            if (!this.h) {
                e(context);
            }
            this.g.a();
        } catch (Throwable e) {
            v.a(e);
        }
    }

    public void a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.h) {
                e(context);
            }
            this.f.a(str, str2, j, i);
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!this.h) {
                e(context);
            }
            this.f.a(str, map, j);
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void a(Context context, String str, String str2) {
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new AnonymousClass_3(str, str2));
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void b(Context context, String str, String str2) {
        try {
            f.a(new AnonymousClass_4(str, str2));
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void a(Context context, String str, HashMap<String, Object> hashMap, String str2) {
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new AnonymousClass_5(str, hashMap, str2));
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void c(Context context, String str, String str2) {
        try {
            f.a(new AnonymousClass_6(str, str2));
        } catch (Throwable e) {
            v.a(e);
        }
    }

    void d(Context context) {
        try {
            this.d.a();
            g(context);
            context.getSharedPreferences("umeng_general_config", 0).edit().commit();
            f.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th) {
        try {
            this.d.a();
            if (this.a != null) {
                if (!(th == null || this.g == null)) {
                    this.g.b(new i(th));
                }
                g(this.a);
                this.a.getSharedPreferences("umeng_general_config", 0).edit().commit();
            }
            f.a();
        } catch (Throwable e) {
            v.b("Exception in onAppCrash", e);
        }
    }

    void b(String str, String str2) {
        try {
            f.a(new AnonymousClass_7(str, str2));
        } catch (Throwable e) {
            v.b(" Excepthon  in  onProfileSignIn", e);
        }
    }

    void b() {
        try {
            f.a(new g() {
                public void a() {
                    String[] a = e.a(d.this);
                    if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                        d.this.a();
                        boolean d = b.d(d.this);
                        ct.a(d.this).c();
                        if (d) {
                            d.this.a();
                            b.e(d.this);
                        }
                        e.b(d.this);
                    }
                }
            });
        } catch (Throwable e) {
            v.b(" Excepthon  in  onProfileSignOff", e);
        }
    }
}
