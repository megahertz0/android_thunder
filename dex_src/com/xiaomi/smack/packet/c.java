package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.umeng.a;
import com.xiaomi.smack.util.g;

public class c extends d {
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private boolean o;

    public c() {
        this.c = null;
        this.d = null;
        this.i = false;
        this.k = a.d;
        this.l = a.d;
        this.m = a.d;
        this.n = a.d;
        this.o = false;
    }

    public c(Bundle bundle) {
        super(bundle);
        this.c = null;
        this.d = null;
        this.i = false;
        this.k = a.d;
        this.l = a.d;
        this.m = a.d;
        this.n = a.d;
        this.o = false;
        this.c = bundle.getString("ext_msg_type");
        this.e = bundle.getString("ext_msg_lang");
        this.d = bundle.getString("ext_msg_thread");
        this.f = bundle.getString("ext_msg_sub");
        this.g = bundle.getString("ext_msg_body");
        this.h = bundle.getString("ext_body_encode");
        this.j = bundle.getString("ext_msg_appid");
        this.i = bundle.getBoolean("ext_msg_trans", false);
        this.o = bundle.getBoolean("ext_msg_encrypt", false);
        this.k = bundle.getString("ext_msg_seq");
        this.l = bundle.getString("ext_msg_mseq");
        this.m = bundle.getString("ext_msg_fseq");
        this.n = bundle.getString("ext_msg_status");
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<message");
        if (t() != null) {
            stringBuilder.append(" xmlns=\"").append(t()).append(h.f);
        }
        if (this.e != null) {
            stringBuilder.append(" xml:lang=\"").append(i()).append(h.f);
        }
        if (k() != null) {
            stringBuilder.append(" id=\"").append(k()).append(h.f);
        }
        if (m() != null) {
            stringBuilder.append(" to=\"").append(g.a(m())).append(h.f);
        }
        if (!TextUtils.isEmpty(e())) {
            stringBuilder.append(" seq=\"").append(e()).append(h.f);
        }
        if (!TextUtils.isEmpty(f())) {
            stringBuilder.append(" mseq=\"").append(f()).append(h.f);
        }
        if (!TextUtils.isEmpty(g())) {
            stringBuilder.append(" fseq=\"").append(g()).append(h.f);
        }
        if (!TextUtils.isEmpty(h())) {
            stringBuilder.append(" status=\"").append(h()).append(h.f);
        }
        if (n() != null) {
            stringBuilder.append(" from=\"").append(g.a(n())).append(h.f);
        }
        if (l() != null) {
            stringBuilder.append(" chid=\"").append(g.a(l())).append(h.f);
        }
        if (this.i) {
            stringBuilder.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.j)) {
            stringBuilder.append(" appid=\"").append(d()).append(h.f);
        }
        if (!TextUtils.isEmpty(this.c)) {
            stringBuilder.append(" type=\"").append(this.c).append(h.f);
        }
        if (this.o) {
            stringBuilder.append(" s=\"1\"");
        }
        stringBuilder.append(">");
        if (this.f != null) {
            stringBuilder.append("<subject>").append(g.a(this.f));
            stringBuilder.append("</subject>");
        }
        if (this.g != null) {
            stringBuilder.append("<body");
            if (!TextUtils.isEmpty(this.h)) {
                stringBuilder.append(" encode=\"").append(this.h).append(h.f);
            }
            stringBuilder.append(">").append(g.a(this.g)).append("</body>");
        }
        if (this.d != null) {
            stringBuilder.append("<thread>").append(this.d).append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.c)) {
            h p = p();
            if (p != null) {
                stringBuilder.append(p.d());
            }
        }
        stringBuilder.append(s());
        stringBuilder.append("</message>");
        return stringBuilder.toString();
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(String str, String str2) {
        this.g = str;
        this.h = str2;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.k = str;
    }

    public void b(boolean z) {
        this.o = z;
    }

    public Bundle c() {
        Bundle c = super.c();
        if (!TextUtils.isEmpty(this.c)) {
            c.putString("ext_msg_type", this.c);
        }
        if (this.e != null) {
            c.putString("ext_msg_lang", this.e);
        }
        if (this.f != null) {
            c.putString("ext_msg_sub", this.f);
        }
        if (this.g != null) {
            c.putString("ext_msg_body", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            c.putString("ext_body_encode", this.h);
        }
        if (this.d != null) {
            c.putString("ext_msg_thread", this.d);
        }
        if (this.j != null) {
            c.putString("ext_msg_appid", this.j);
        }
        if (this.i) {
            c.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.k)) {
            c.putString("ext_msg_seq", this.k);
        }
        if (!TextUtils.isEmpty(this.l)) {
            c.putString("ext_msg_mseq", this.l);
        }
        if (!TextUtils.isEmpty(this.m)) {
            c.putString("ext_msg_fseq", this.m);
        }
        if (this.o) {
            c.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.n)) {
            c.putString("ext_msg_status", this.n);
        }
        return c;
    }

    public void c(String str) {
        this.l = str;
    }

    public String d() {
        return this.j;
    }

    public void d(String str) {
        this.m = str;
    }

    public String e() {
        return this.k;
    }

    public void e(String str) {
        this.n = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return !super.equals(cVar) ? false : (this.g == null ? cVar.g != null : !this.g.equals(cVar.g)) ? false : (this.e == null ? cVar.e != null : !this.e.equals(cVar.e)) ? false : (this.f == null ? cVar.f != null : !this.f.equals(cVar.f)) ? false : (this.d == null ? cVar.d != null : !this.d.equals(cVar.d)) ? false : this.c == cVar.c;
    }

    public String f() {
        return this.l;
    }

    public void f(String str) {
        this.c = str;
    }

    public String g() {
        return this.m;
    }

    public void g(String str) {
        this.f = str;
    }

    public String h() {
        return this.n;
    }

    public void h(String str) {
        this.g = str;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + ((this.c != null ? this.c.hashCode() : 0) * 31)) * 31)) * 31)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String i() {
        return this.e;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(String str) {
        this.e = str;
    }
}
