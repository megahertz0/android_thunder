package com.xunlei.common.member.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.a.n;
import com.xunlei.common.member.act.XLAlipayLoginActivity;
import com.xunlei.common.member.act.XLAlipayParam;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

// compiled from: UserAlipayLoginTask.java
public final class a extends p {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private XLAlipayParam k;
    private n l;
    private int m;
    private String n;

    public a(m mVar) {
        super(mVar);
        this.e = 0;
        this.g = null;
        this.h = null;
        this.i = com.umeng.a.d;
        this.j = 0;
        this.k = null;
        this.l = new n(this);
        this.m = 0;
        this.n = com.umeng.a.d;
        this.e = 255;
    }

    public final void a() {
        super.a();
    }

    public final boolean b() {
        if (255 == this.e) {
            Intent intent = new Intent(m.a().h(), XLAlipayLoginActivity.class);
            intent.putExtra("ali_task", j());
            intent.putExtra("ali_auth_param", this.k);
            intent.addFlags(268435456);
            g().h().startActivity(intent);
            XLLog.v("UserAlipayLoginTask ", "get alipay auth code.");
        } else if (256 == this.e) {
            Header[] headerArr = new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("appid=").append(this.h).append("&code=").append(this.g).append("&version=1.1&sign=").append(MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.g).append("1.1").toString()));
            XLLog.v("XLSinaLoginActivity", new StringBuilder("acceptSianOauthToken param = ").append(stringBuilder.toString()).toString());
            m.a().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php?module=alipay_app", headerArr, stringBuilder.toString().getBytes(), new AnonymousClass_1(this));
        } else if (257 == this.e) {
            g().a(this.j, this.i, g().d(), 0, this.l, (Object) "ali-get-client-sessionid", true);
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserThirdLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), XZBDevice.DOWNLOAD_LIST_ALL, this.m, i(), bundle.getString("errorDesc"), j());
    }

    public final void a(Object obj) {
        this.k = (XLAlipayParam) obj;
    }

    public final void a(int i, String str, String str2, String str3) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100601;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(j());
        if (i == 0) {
            this.g = str2;
            this.h = str3;
            this.e = 256;
            b();
            return;
        }
        this.e = 258;
        b(i);
    }

    public final void a(int i) {
        XLLog.e("UserAlipayLoginTask", "recieve client session.");
        this.e = 258;
        b(i);
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private String c() {
        return MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.g).append("1.1").toString());
    }

    private void c(int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100603;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = this.n;
        XLStatUtil.getInstance().report(j(), xLStatPack);
    }

    static /* synthetic */ void d(a aVar, int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100603;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = aVar.n;
        XLStatUtil.getInstance().report(aVar.j(), xLStatPack);
    }
}
