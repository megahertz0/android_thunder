package com.xunlei.common.member.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.a.n;
import com.xunlei.common.member.act.XLXmLoginActivity;
import com.xunlei.common.member.act.XLXmParam;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;

// compiled from: UserXmLoginTask.java
public final class u extends p {
    private int a;
    private int b;
    private int c;
    private String d;
    private int e;
    private XLXmParam f;
    private n g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;

    public final void a() {
        super.a();
    }

    public final void a(Object obj) {
        this.f = (XLXmParam) obj;
    }

    public u(m mVar) {
        super(mVar);
        this.e = 0;
        this.f = null;
        this.g = new n(this);
        this.h = 0;
        this.i = a.d;
        this.j = a.d;
        this.k = a.d;
        this.e = 4132;
    }

    public final boolean b() {
        if (this.e == 4132) {
            Intent intent = new Intent(m.a().h(), XLXmLoginActivity.class);
            intent.putExtra("xm_task", j());
            intent.putExtra("xm_client_from", this.f.mLoginReqFrom);
            intent.addFlags(268435456);
            g().h().startActivity(intent);
            XLLog.v("UserXmLoginTask", " step get xiaomi token.");
        } else if (this.e == 4133) {
            g().a("xm", this.i, this.j, this.k, this.g, (Object) "get-client-sessionid", true);
            XLLog.v("UserXmLoginTask", " step get client session.");
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserThirdLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), 1, this.h, i(), bundle.getString("errorDesc"), j());
    }

    public final void a(int i) {
        this.e = 4134;
        c(i);
    }

    public final void b(int i) {
        this.h = i;
    }

    public final void a(int i, String str, String str2, String str3) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100301;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(j());
        if (i == 0) {
            this.e = 4133;
            this.i = str;
            this.j = str2;
            this.k = str3;
            g().n().postDelayed(new AnonymousClass_1(this), 0);
        } else {
            c(i);
        }
        g().b(j());
    }

    private void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }
}
