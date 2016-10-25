package com.xunlei.downloadprovider.member.payment.a;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.n;

// compiled from: UserInfoManager.java
public class j {
    private static j c;
    public LoginHelper a;
    public boolean b;

    private j() {
        this.a = LoginHelper.a();
        this.b = false;
    }

    public static j a() {
        if (c == null) {
            synchronized (j.class) {
                if (c == null) {
                    c = new j();
                }
            }
        }
        return c;
    }

    private n h() {
        if (this.a.B != null) {
            return this.a.B;
        }
        LoginHelper loginHelper = this.a;
        loginHelper.getClass();
        return new n(loginHelper);
    }

    public final boolean b() {
        if (this.a.h == 5) {
            int i = 1;
        } else {
            boolean z = false;
        }
        boolean f = this.a.f();
        if (z && f) {
            return false;
        }
        i = h().a;
        if (i == 0 || i != 1) {
            z = false;
        } else {
            i = 1;
        }
        if (this.b) {
            return z;
        }
        return !f && z;
    }

    public final boolean c() {
        return b() ? true : this.a.f();
    }

    public final int d() {
        return b() ? 204 : this.a.h;
    }

    public final boolean e() {
        if (!b()) {
            return this.a.m();
        }
        int i = h().k;
        return i != 0 && i == 1;
    }

    public final String f() {
        return b() ? h().c : this.a.n();
    }

    public final int g() {
        return b() ? h().a : this.a.z;
    }
}
