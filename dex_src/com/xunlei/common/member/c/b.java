package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: UserGetAuthQRCodeTask.java
public final class b extends p {
    private static final int a = 1024;
    private static final int b = 1025;
    private static final int c = 1026;
    private static final String d = "https://qrcode.xunlei.com/uuid?business=%d&term=%s";
    private static final String e = "https://qrcode.xunlei.com/qrlogin.png?tbusiness=%d&term=%s&ch=%s&tversion=%s";
    private int f;
    private String g;
    private byte[] h;
    private int i;

    public b(m mVar) {
        super(mVar);
        this.f = 0;
        this.g = a.d;
        this.h = null;
        this.i = 65670;
    }

    public final boolean b() {
        if (this.f == 1024) {
            g().k().get(g().h(), String.format(d, new Object[]{Integer.valueOf(g().d()), "client"}), null, new AnonymousClass_1(this));
        } else if (this.f == 1025) {
            g().k().get(g().h(), String.format(e, new Object[]{Integer.valueOf(g().d()), "client", this.g, g().c()}), null, new AnonymousClass_2(this));
        }
        return false;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "UserGetAuthQRCodeTask") {
            return false;
        }
        return xLOnUserListener.onUserGetQRCode(bundle.getInt(Constants.KEY_ERROR_CODE), this.g, this.h, i(), bundle.getString("errorDesc"), j());
    }

    public final void b(int i) {
        this.i = i;
        this.f = 1024;
    }

    private void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "UserGetAuthQRCodeTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private String c() {
        return String.format(d, new Object[]{Integer.valueOf(g().d()), "client"});
    }

    private String d() {
        return String.format(e, new Object[]{Integer.valueOf(g().d()), "client", this.g, g().c()});
    }

    private static String g(int i) {
        return "client";
    }

    private void o() {
        g().k().get(g().h(), String.format(d, new Object[]{Integer.valueOf(g().d()), "client"}), null, new AnonymousClass_1(this));
    }

    private void p() {
        g().k().get(g().h(), String.format(e, new Object[]{Integer.valueOf(g().d()), "client", this.g, g().c()}), null, new AnonymousClass_2(this));
    }

    static /* synthetic */ void b(b bVar, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "UserGetAuthQRCodeTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        bVar.g().a((p) bVar, bundle);
    }
}
