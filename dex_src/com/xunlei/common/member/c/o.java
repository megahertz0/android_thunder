package com.xunlei.common.member.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.a.n;
import com.xunlei.common.member.act.XLSinaLoginActivity;
import com.xunlei.common.member.act.XLSinaParam;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

// compiled from: UserSinaLoginTask.java
public final class o extends p {
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private n k;
    private int l;
    private XLSinaParam m;
    private String n;

    public final void a() {
        super.a();
    }

    public final void a(Object obj) {
        this.m = (XLSinaParam) obj;
    }

    public o(m mVar) {
        super(mVar);
        this.d = 0;
        this.e = a.d;
        this.f = a.d;
        this.g = a.d;
        this.h = a.d;
        this.i = a.d;
        this.k = new n(this);
        this.l = 0;
        this.m = null;
        this.n = a.d;
        this.d = 4132;
    }

    public final boolean b() {
        if (this.d == 4132) {
            Intent intent = new Intent(m.a().h(), XLSinaLoginActivity.class);
            intent.putExtra("sina_task", j());
            intent.putExtra("sina_app_id", this.m.mSinaAppId);
            intent.putExtra("sina_app_redirect", this.m.mRedirectUrl);
            intent.addFlags(268435456);
            g().h().startActivity(intent);
            XLLog.v("UserSinaLoginTask", "get web session start.");
        } else if (this.d == 4133) {
            g().a(Integer.valueOf(this.f).intValue(), this.e, g().d(), 0, this.k, (Object) "get-client-sessionid", true);
            XLLog.v("UserSinaLoginTask", "get client session.");
        }
        return false;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserThirdLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), XZBDevice.DOWNLOAD_LIST_RECYCLE, this.l, i(), bundle.getString("errorDesc"), j());
    }

    public final void a(int i) {
        this.d = 4134;
        b(i);
    }

    private void b(int i) {
        g().n().post(new AnonymousClass_1(this, i));
    }

    private String c() {
        return MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.h).append(this.m.mSinaAppId).append("1.1").toString());
    }

    private void c(int i) {
        XLLog.v("UserSinaLoginTask", new StringBuilder("accept web session error = ").append(i).toString());
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100201;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(j());
        if (i == 0) {
            this.d = 4133;
            g().n().postDelayed(new AnonymousClass_2(this), 0);
        } else {
            b(XLErrorCode.GET_WEB_SESSIONID_ERROR);
        }
        g().b(j());
    }

    private void g(int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100203;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = this.n;
        XLStatUtil.getInstance().report(j(), xLStatPack);
    }

    public final void a(int i, String str, String str2, String str3, String str4) {
        XLLog.v(getClass().getSimpleName(), new StringBuilder("acceptSianOauthToken error = ").append(i).toString());
        if (i == 0) {
            this.g = str;
            this.h = str2;
            this.i = str3;
            Header[] headerArr = new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("uid=").append(this.g).append("&appid=").append(this.m.mSinaAppId).append("&sign=").append(MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.h).append(this.m.mSinaAppId).append("1.1").toString())).append("&accessToken=").append(this.h).append("&version=1.1&refreshToken=").append(this.i);
            XLLog.v(getClass().getSimpleName(), new StringBuilder("acceptSianOauthToken param = ").append(stringBuilder.toString()).toString());
            m.a().k().post(m.a().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php?module=weibo_app", headerArr, stringBuilder.toString().getBytes(), new AnonymousClass_3(this));
            return;
        }
        b(i);
    }

    static /* synthetic */ void b(o oVar, int i) {
        XLLog.v("UserSinaLoginTask", new StringBuilder("accept web session error = ").append(i).toString());
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100201;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(oVar.j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(oVar.j());
        if (i == 0) {
            oVar.d = 4133;
            oVar.g().n().postDelayed(new AnonymousClass_2(oVar), 0);
        } else {
            oVar.b(XLErrorCode.GET_WEB_SESSIONID_ERROR);
        }
        oVar.g().b(oVar.j());
    }

    static /* synthetic */ void c(o oVar, int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100203;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = oVar.n;
        XLStatUtil.getInstance().report(oVar.j(), xLStatPack);
    }
}
