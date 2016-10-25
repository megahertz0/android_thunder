package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.b.a.a.a.a.b;
import com.alipay.b.a.a.d.c;
import com.umeng.a;
import java.util.UUID;
import org.android.agoo.message.MessageService;

public final class h {
    private static String a;

    static {
        a = a.d;
    }

    public static String a(Context context) {
        try {
            String a = c.a(context, "vkeyid_settings", "last_apdid_env", a.d);
            if (com.alipay.b.a.a.a.a.a(a)) {
                return a.d;
            }
            a = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
            return com.alipay.b.a.a.a.a.a(a) ? a.d : a;
        } catch (Throwable th) {
            return a.d;
        }
    }

    public static void a(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("last_apdid_env", com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString(new StringBuilder("vkey_valid").append(str).toString(), com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), String.valueOf(j)));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, boolean z) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("log_switch", z ? com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), MessageService.MSG_DB_NOTIFY_REACHED) : com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), MessageService.MSG_DB_READY_REPORT));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void b(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("agent_switch", com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static boolean b(Context context) {
        try {
            String a = c.a(context, "vkeyid_settings", "log_switch", a.d);
            if (com.alipay.b.a.a.a.a.a(a)) {
                return false;
            }
            a = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
            return !com.alipay.b.a.a.a.a.a(a) ? a.equals(MessageService.MSG_DB_NOTIFY_REACHED) : false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static long c(Context context, String str) {
        try {
            String string = context.getSharedPreferences("vkeyid_settings", 0).getString(new StringBuilder("vkey_valid").append(str).toString(), a.d);
            if (com.alipay.b.a.a.a.a.a(string)) {
                return 0;
            }
            string = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), string);
            return !com.alipay.b.a.a.a.a.a(string) ? Long.parseLong(string) : 0;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static synchronized String c(Context context) {
        String a;
        synchronized (h.class) {
            if (com.alipay.b.a.a.a.a.a(a)) {
                a = c.a(context, "alipay_vkey_random", "random", a.d);
                a = a;
                if (com.alipay.b.a.a.a.a.a(a)) {
                    a = b.a(UUID.randomUUID().toString());
                    a = "alipay_vkey_random";
                    String str = "random";
                    String str2 = a;
                    if (str2 != null) {
                        Editor edit = context.getSharedPreferences(a, 0).edit();
                        if (edit != null) {
                            edit.clear();
                            edit.putString(str, str2);
                            edit.commit();
                        }
                    }
                }
            }
            a = a;
        }
        return a;
    }
}
