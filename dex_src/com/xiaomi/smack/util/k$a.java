package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;

class k$a {
    public String a;
    public long b;
    public int c;
    public int d;
    public String e;
    public long f;

    public k$a(String str, long j, int i, int i2, String str2, long j2) {
        this.a = BuildConfig.VERSION_NAME;
        this.b = 0;
        this.c = -1;
        this.d = -1;
        this.e = BuildConfig.VERSION_NAME;
        this.f = 0;
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = i2;
        this.e = str2;
        this.f = j2;
    }

    public boolean a(k$a com_xiaomi_smack_util_k_a) {
        return TextUtils.equals(com_xiaomi_smack_util_k_a.a, this.a) && TextUtils.equals(com_xiaomi_smack_util_k_a.e, this.e) && com_xiaomi_smack_util_k_a.c == this.c && com_xiaomi_smack_util_k_a.d == this.d && Math.abs(com_xiaomi_smack_util_k_a.b - this.b) <= 5000;
    }
}
