package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.push.service.d;

public class a {
    private static a a;
    private Context b;
    private a c;

    private a(Context context) {
        this.b = context;
        o();
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    private void o() {
        this.c = new a(this, null);
        SharedPreferences j = j();
        this.c.a = j.getString("appId", null);
        this.c.b = j.getString("appToken", null);
        this.c.c = j.getString("regId", null);
        this.c.d = j.getString("regSec", null);
        this.c.f = j.getString("devId", null);
        if (!TextUtils.isEmpty(this.c.f) && this.c.f.startsWith("a-")) {
            this.c.f = d.e(this.b);
            j.edit().putString("devId", this.c.f).commit();
        }
        this.c.e = j.getString("vName", null);
        this.c.h = j.getBoolean("valid", true);
        this.c.i = j.getBoolean("paused", false);
        this.c.j = j.getInt("envType", 1);
        this.c.g = j.getString("regResource", null);
    }

    public void a(int i) {
        this.c.a(i);
        j().edit().putInt("envType", i).commit();
    }

    public void a(String str) {
        Editor edit = j().edit();
        edit.putString("vName", str);
        edit.commit();
        this.c.e = str;
    }

    public void a(String str, String str2, String str3) {
        this.c.a(str, str2, str3);
    }

    public void a(boolean z) {
        this.c.a(z);
        j().edit().putBoolean("paused", z).commit();
    }

    public boolean a() {
        return !TextUtils.equals(b.a(this.b, this.b.getPackageName()), this.c.e);
    }

    public boolean a(String str, String str2) {
        return this.c.b(str, str2);
    }

    public void b(String str, String str2) {
        this.c.a(str, str2);
    }

    public boolean b() {
        if (this.c.a()) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.a("Don't send message before initialization succeeded!");
        return false;
    }

    public String c() {
        return this.c.a;
    }

    public String d() {
        return this.c.b;
    }

    public String e() {
        return this.c.c;
    }

    public String f() {
        return this.c.d;
    }

    public String g() {
        return this.c.g;
    }

    public void h() {
        this.c.b();
    }

    public boolean i() {
        return this.c.a();
    }

    public SharedPreferences j() {
        return this.b.getSharedPreferences("mipush", 0);
    }

    public void k() {
        this.c.c();
    }

    public boolean l() {
        return this.c.i;
    }

    public int m() {
        return this.c.j;
    }

    public boolean n() {
        return !this.c.h;
    }
}
