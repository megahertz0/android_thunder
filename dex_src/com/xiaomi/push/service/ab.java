package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.umeng.message.MsgConstant;

public class ab {
    private static ab a;
    private Context b;
    private int c;

    private ab(Context context) {
        this.c = 0;
        this.b = context.getApplicationContext();
    }

    public static ab a(Context context) {
        if (a == null) {
            a = new ab(context);
        }
        return a;
    }

    public boolean a() {
        return "@SHIP.TO.2A2FE0D7@".contains("xmsf") || "@SHIP.TO.2A2FE0D7@".contains(MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI) || "@SHIP.TO.2A2FE0D7@".contains("miui");
    }

    @SuppressLint({"NewApi"})
    public int b() {
        if (this.c != 0) {
            return this.c;
        }
        if (VERSION.SDK_INT >= 17) {
            this.c = Global.getInt(this.b.getContentResolver(), "device_provisioned", 0);
            return this.c;
        }
        this.c = Secure.getInt(this.b.getContentResolver(), "device_provisioned", 0);
        return this.c;
    }

    @SuppressLint({"NewApi"})
    public Uri c() {
        return VERSION.SDK_INT >= 17 ? Global.getUriFor("device_provisioned") : Secure.getUriFor("device_provisioned");
    }
}
