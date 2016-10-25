package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;

public class h {
    private static h a;
    private Context b;
    private List<String> c;

    static {
        a = null;
    }

    private h(Context context) {
        int i = 0;
        this.c = new ArrayList();
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
        String[] split = this.b.getSharedPreferences("mipush_app_info", 0).getString("unregistered_pkg_names", a.d).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        int length = split.length;
        while (i < length) {
            CharSequence charSequence = split[i];
            if (TextUtils.isEmpty(charSequence)) {
                this.c.add(charSequence);
            }
            i++;
        }
    }

    public static h a(Context context) {
        if (a == null) {
            a = new h(context);
        }
        return a;
    }

    public boolean a(String str) {
        boolean contains;
        synchronized (this.c) {
            contains = this.c.contains(str);
        }
        return contains;
    }

    public void b(String str) {
        synchronized (this.c) {
            if (!this.c.contains(str)) {
                this.c.add(str);
                this.b.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", d.a(this.c, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }

    public void c(String str) {
        synchronized (this.c) {
            if (this.c.contains(str)) {
                this.c.remove(str);
                this.b.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", d.a(this.c, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }
}
