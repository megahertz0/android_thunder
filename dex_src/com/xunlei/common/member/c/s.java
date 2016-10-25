package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.g;
import com.xunlei.common.member.a.m;

// compiled from: UserVerifyedCodeTask.java
public final class s extends p {
    private static final String a = "/verify/%s/%s?t=%s&header=true";
    private String b;
    private String c;
    private String d;

    public final void a(String str, String str2) {
        this.b = m.a().t();
        this.c = str;
        this.d = str2;
    }

    private void a(String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        g().a(j(), gVar);
    }

    public s(m mVar) {
        super(mVar);
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
    }

    public final boolean b() {
        d(a.b);
        g().j().a(String.format(r.c() + a, new Object[]{this.d, this.c, this.b}), new AnonymousClass_1(this));
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null) ? false : xLOnUserListener.onUserVerifyedCode(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    static /* synthetic */ void a(s sVar, String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        sVar.g().a(sVar.j(), gVar);
    }
}
