package com.tencent.stat;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.System;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.d;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class a {
    private static a b;
    private StatLogger a;
    private boolean c;
    private boolean d;
    private boolean e;
    private Context f;

    static {
        b = null;
    }

    private a(Context context) {
        this.a = k.b();
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = null;
        this.f = context.getApplicationContext();
        this.c = b(context);
        this.d = d(context);
        this.e = c(context);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    private boolean b(Context context) {
        if (k.a(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            return true;
        }
        this.a.e((Object) "Check permission failed: android.permission.WRITE_EXTERNAL_STORAGE");
        return false;
    }

    private boolean c(Context context) {
        if (k.a(context, "android.permission.WRITE_SETTINGS")) {
            return true;
        }
        this.a.e((Object) "Check permission failed: android.permission.WRITE_SETTINGS");
        return false;
    }

    private boolean d(Context context) {
        return k.d() < 14 ? b(context) : true;
    }

    public boolean a(String str, String str2) {
        p.b(this.f, str, str2);
        return true;
    }

    public String b(String str, String str2) {
        return p.a(this.f, str, str2);
    }

    public boolean c(String str, String str2) {
        boolean z = false;
        if (!this.c) {
            return false;
        }
        try {
            d.a(Environment.getExternalStorageDirectory() + "/Tencent/mta");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt")));
            bufferedWriter.write(str + MiPushClient.ACCEPT_TIME_SEPARATOR + str2);
            bufferedWriter.write("\n");
            bufferedWriter.close();
            z = true;
            return true;
        } catch (Throwable th) {
            this.a.w(th);
            return z;
        }
    }

    public String d(String str, String str2) {
        if (!this.c) {
            return null;
        }
        try {
            for (String str3 : d.a(new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt"))) {
                String[] split = str3.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                if (split.length == 2 && split[0].equals(str)) {
                    return split[1];
                }
            }
        } catch (FileNotFoundException e) {
            this.a.w("Tencent/mta/.mid.txt not found.");
        } catch (Throwable th) {
            this.a.w(th);
        }
        return null;
    }

    public boolean e(String str, String str2) {
        if (!this.e) {
            return false;
        }
        System.putString(this.f.getContentResolver(), str, str2);
        return true;
    }

    public String f(String str, String str2) {
        return !this.e ? str2 : System.getString(this.f.getContentResolver(), str);
    }
}
