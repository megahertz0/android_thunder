package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.g;
import com.xunlei.common.member.a.m;

// compiled from: UserPreVerifyCodeTask.java
public final class j extends p {
    private static String a;
    private String b;
    private String c;
    private String d;

    static {
        a = "/verify/%s/%s?t=%s&p=true&header=true";
    }

    public final void a(String str, String str2) {
        this.b = m.a().t();
        this.c = str;
        this.d = str2;
    }

    public j(m mVar) {
        super(mVar);
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
    }

    private void a(String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        g().a(j(), gVar);
    }

    public final boolean b() {
        d(a.b);
        g().j().a(String.format(r.c() + a, new Object[]{this.d, this.c, this.b}), new AnonymousClass_1(this));
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null) ? false : xLOnUserListener.onUserPreVerifyedCode(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    static /* synthetic */ void a(j jVar, String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        jVar.g().a(jVar.j(), gVar);
    }
}
