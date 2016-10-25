package com.xunlei.downloadprovider.frame.user;

import android.text.TextUtils;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: UserLocalInfo.java
public final class bx {
    public boolean a;
    public int b;
    int c;
    public long d;
    public String e;
    public String f;
    int g;
    public int h;
    public String i;
    int j;
    private int k;
    private String l;
    private String m;
    private int n;
    private String o;

    public bx() {
        LoginHelper.a();
        XLUserInfo q = LoginHelper.q();
        if (q != null) {
            this.a = true;
            Object stringValue = q.getStringValue(USERINFOKEY.UserID);
            if (TextUtils.isEmpty(stringValue)) {
                this.a = false;
                return;
            }
            this.d = Long.valueOf(stringValue).longValue();
            this.b = q.getIntValue(USERINFOKEY.vip_level);
            this.c = q.getIntValue(USERINFOKEY.VasType);
            this.n = q.getIntValue(USERINFOKEY.other_IsVip);
            this.o = q.getStringValue(USERINFOKEY.other_ExpireDate);
            this.g = q.getIntValue(USERINFOKEY.other_PayId);
            this.k = q.getIntValue(USERINFOKEY.IsVip);
            this.l = q.getStringValue(USERINFOKEY.SessionID);
            this.e = q.getStringValue(USERINFOKEY.ImgURL);
            this.f = q.getStringValue(USERINFOKEY.NickName);
            this.m = q.getStringValue(USERINFOKEY.ExpireDate);
            this.h = q.getIntValue(USERINFOKEY.Account);
            this.i = q.getStringValue(USERINFOKEY.Sex);
            this.j = q.getIntValue(USERINFOKEY.PayId);
        }
    }

    public final boolean a() {
        return 2 == this.c;
    }

    public final boolean b() {
        return 3 == this.c;
    }

    public final boolean c() {
        return 4 == this.c;
    }

    public final boolean d() {
        return 5 == this.c;
    }

    public final boolean e() {
        return this.k != 0 && this.k == 1;
    }

    public final String f() {
        return g() ? this.o : this.m;
    }

    public final boolean g() {
        if (!e()) {
            boolean z;
            if (this.n == 0 || this.n != 1) {
                Object obj = null;
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }
}
