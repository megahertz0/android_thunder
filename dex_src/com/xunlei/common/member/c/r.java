package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.g;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: UserVerifyCodeTask.java
public final class r extends p {
    private static int e;
    private static final String[] g;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private String f;
    private String h;

    static {
        e = 0;
        g = new String[]{"http://verify2.xunlei.com", "http://verify.xunlei.com", "http://verify3.xunlei.com", "http://verify1.xunlei.com"};
    }

    private static String p() {
        if (e >= 4) {
            return null;
        }
        String[] strArr = g;
        int i = e;
        e = i + 1;
        return strArr[i];
    }

    public r(m mVar) {
        super(mVar);
        this.f = a.d;
        this.h = "MEA";
    }

    private static void q() {
    }

    private void a(String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        g().a(j(), gVar);
    }

    public final boolean b() {
        if (a.d == f()) {
            return false;
        }
        String str;
        d(a.b);
        StringBuilder stringBuilder = new StringBuilder();
        if (e < 4) {
            String[] strArr = g;
            int i = e;
            e = i + 1;
            str = strArr[i];
        } else {
            str = null;
        }
        this.f = stringBuilder.append(str).append("/image?t=%s").toString();
        if (this.f == null) {
            return false;
        }
        str = String.format(this.f, new Object[]{this.h});
        g().j().a(str, new AnonymousClass_1(this, str));
        d(a.c);
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "UserVerifyCodeTask") {
            return false;
        }
        return xLOnUserListener.onUserVerifyCodeUpdated(bundle.getInt(Constants.KEY_ERROR_CODE), bundle.getString("verifyKey"), bundle.getInt("imageType"), bundle.getByteArray("imageContent"), i(), j());
    }

    public final void a() {
        super.a();
        this.h = g().t();
        XLLog.v("UserVerifyCodeTask", new StringBuilder("get verify code verify type = ").append(this.h).toString());
        e = 0;
    }

    public static String c() {
        int i = e - 1;
        if (i < 0) {
            i = 0;
        }
        return g[i];
    }

    static /* synthetic */ void a(r rVar, String str) {
        g gVar = new g();
        gVar.a = XLUtilTools.getServerDomain(str);
        gVar.b = 0;
        rVar.g().a(rVar.j(), gVar);
    }
}
