package com.xunlei.downloadprovider.member.login;

import com.xunlei.downloadprovider.b.c.g.b;
import java.util.List;
import java.util.Map;

// compiled from: LoginHelper.java
final class d implements b {
    final /* synthetic */ long a;
    final /* synthetic */ LoginHelper b;

    d(LoginHelper loginHelper, long j) {
        this.b = loginHelper;
        this.a = j;
    }

    public final void a(int i, Map<String, List<String>> map, byte[] bArr) {
        LoginHelper.a(this.b, bArr, this.a);
    }
}
