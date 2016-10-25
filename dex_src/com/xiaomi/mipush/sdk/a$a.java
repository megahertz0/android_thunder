package com.xiaomi.mipush.sdk;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.push.service.d;

private class a$a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public boolean h;
    public boolean i;
    public int j;
    final /* synthetic */ a k;

    private a$a(a aVar) {
        this.k = aVar;
        this.h = true;
        this.i = false;
        this.j = 1;
    }

    private String d() {
        return b.a(a.a(this.k), a.a(this.k).getPackageName());
    }

    public void a(int i) {
        this.j = i;
    }

    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
        this.f = d.e(a.a(this.k));
        this.e = d();
        this.h = true;
        Editor edit = this.k.j().edit();
        edit.putString("regId", str);
        edit.putString("regSec", str2);
        edit.putString("devId", this.f);
        edit.putString("vName", d());
        edit.putBoolean("valid", true);
        edit.commit();
    }

    public void a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.g = str3;
        Editor edit = this.k.j().edit();
        edit.putString("appId", this.a);
        edit.putString("appToken", str2);
        edit.putString("regResource", str3);
        edit.commit();
    }

    public void a(boolean z) {
        this.i = z;
    }

    public boolean a() {
        return b(this.a, this.b);
    }

    public void b() {
        this.k.j().edit().clear().commit();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.e = null;
        this.h = false;
        this.i = false;
        this.j = 1;
    }

    public boolean b(String str, String str2) {
        return TextUtils.equals(this.a, str) && TextUtils.equals(this.b, str2) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d) && TextUtils.equals(this.f, d.e(a.a(this.k)));
    }

    public void c() {
        this.h = false;
        this.k.j().edit().putBoolean("valid", this.h).commit();
    }
}
