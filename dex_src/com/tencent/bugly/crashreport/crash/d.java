package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.h5.b;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.af;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import java.lang.reflect.Field;
import java.util.Map;

// compiled from: BUGLY
public final class d {
    private static af a;
    private static ae b;
    private static b c;

    // compiled from: BUGLY
    final class AnonymousClass_2 implements Runnable {
        private /* synthetic */ Thread a;
        private /* synthetic */ String b;
        private /* synthetic */ String c;
        private /* synthetic */ String d;

        AnonymousClass_2(Thread thread, String str, String str2, String str3) {
            this.a = thread;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        public final void run() {
            try {
                a.a(this.a, this.b, this.c, this.d);
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
                w.e("u3d crash error %s %s %s", this.b, this.c, this.d);
            }
        }
    }

    // compiled from: BUGLY
    final class AnonymousClass_3 implements Runnable {
        private /* synthetic */ Thread a;
        private /* synthetic */ int b;
        private /* synthetic */ String c;
        private /* synthetic */ String d;
        private /* synthetic */ String e;

        AnonymousClass_3(Thread thread, int i, String str, String str2, String str3) {
            this.a = thread;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = str3;
        }

        public final void run() {
            try {
                b.a(this.a, this.b, this.c, this.d, this.e);
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
                w.e("cocos2d-x crash error %s %s %s", this.c, this.d, this.e);
            }
        }
    }

    // compiled from: BUGLY
    final class AnonymousClass_4 implements Runnable {
        private /* synthetic */ Thread a;
        private /* synthetic */ String b;
        private /* synthetic */ String c;
        private /* synthetic */ String d;
        private /* synthetic */ Map e;

        AnonymousClass_4(Thread thread, String str, String str2, String str3, Map map) {
            this.a = thread;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = map;
        }

        public final void run() {
            try {
                c.a(this.a, this.b, this.c, this.d, this.e);
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
                w.e("H5 crash error %s %s %s", this.b, this.c, this.d);
            }
        }
    }

    static /* synthetic */ void a() {
        a a = a.a();
        try {
            Class forName = Class.forName("com.tencent.bugly.unity.UnityAgent");
            Object obj = "com.tencent.bugly";
            a.getClass();
            String str = com.umeng.a.d;
            if (!com.umeng.a.d.equals(str)) {
                obj = obj + "." + str;
            }
            try {
                Field declaredField = forName.getDeclaredField("sdkPackageName");
                declaredField.setAccessible(true);
                declaredField.set(null, obj);
            } catch (Exception e) {
            }
        } catch (Throwable th) {
            w.a("no unity agent", new Object[0]);
        }
        try {
            forName = Class.forName("com.tencent.bugly.cocos.Cocos2dxAgent");
            obj = "com.tencent.bugly";
            a.getClass();
            String str2 = com.umeng.a.d;
            if (!com.umeng.a.d.equals(str2)) {
                obj = obj + "." + str2;
            }
            try {
                Field declaredField2 = forName.getDeclaredField("sdkPackageName");
                declaredField2.setAccessible(true);
                declaredField2.set(null, obj);
            } catch (Exception e2) {
            }
        } catch (Throwable th2) {
            w.a("no cocos agent", new Object[0]);
        }
    }

    public static void a(Context context) {
        c a = c.a();
        if (a != null) {
            a = new af(context, a.k, com.tencent.bugly.crashreport.common.strategy.a.a(), a.a(), a.l);
            b = new ae(context, a.k, com.tencent.bugly.crashreport.common.strategy.a.a(), a.a(), a.l);
            c = new b(context, a.k, com.tencent.bugly.crashreport.common.strategy.a.a(), a.a(), a.l);
            v.a().b(new Runnable() {
                public final void run() {
                    d.a();
                }
            });
        }
    }

    public static void a(StrategyBean strategyBean) {
        if (b != null) {
            boolean z = strategyBean.i;
        }
    }

    public static void a(Thread thread, String str, String str2, String str3) {
        if (a != null) {
            v.a().b(new AnonymousClass_2(thread, str, str2, str3));
        }
    }

    public static void a(Thread thread, int i, String str, String str2, String str3) {
        if (b != null) {
            v.a().b(new AnonymousClass_3(thread, i, str, str2, str3));
        }
    }

    public static void a(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (c != null) {
            v.a().b(new AnonymousClass_4(thread, str, str2, str3, map));
        }
    }
}
