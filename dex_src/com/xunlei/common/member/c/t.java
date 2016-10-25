package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.a.n;
import com.xunlei.common.member.act.XLWxParam;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;

// compiled from: UserWxLoginTask.java
public final class t extends p {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private XLWxParam f;
    private String g;
    private String h;
    private int i;
    private IWXAPI j;
    private n k;
    private int l;
    private String m;

    public t(m mVar) {
        super(mVar);
        this.e = 0;
        this.f = null;
        this.g = a.d;
        this.h = a.d;
        this.i = 0;
        this.j = null;
        this.k = new n(this);
        this.l = 0;
        this.m = a.d;
        this.e = 255;
    }

    public final void a() {
        super.a();
    }

    public final void a(Object obj) {
        this.f = (XLWxParam) obj;
        g();
    }

    public final void a(int i, String str) {
        XLLog.e("UserWxLoginTask", "recieve wx code.");
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100101;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(j());
        if (i == 0) {
            this.g = str;
            this.e = 256;
            m.a().n().postDelayed(new AnonymousClass_1(this), 0);
        } else {
            b(i);
        }
        g().b(j());
    }

    public final void a(int i) {
        XLLog.e("UserWxLoginTask", "recieve client session.");
        this.e = 258;
        b(i);
    }

    public final boolean b() {
        if (a.d == f()) {
            return false;
        }
        d(a.b);
        if (this.e == 255) {
            XLLog.e("UserWxLoginTask", "get wx code.");
            this.j = WXAPIFactory.createWXAPI(g().h(), this.f.mWxAppId, false);
            this.j.registerApp(this.f.mWxAppId);
            if (this.j.isWXAppInstalled()) {
                BaseReq req = new Req();
                req.scope = "snsapi_userinfo";
                req.transaction = new StringBuilder("xl_sdk_get_access_code#").append(j()).toString();
                req.state = String.valueOf(j());
                if (!this.j.sendReq(req)) {
                    b(XLErrorCode.WX_REQ_FAIL);
                }
            } else {
                b(XLErrorCode.WX_NOT_INSTALLED);
            }
        } else if (this.e == 256) {
            XLLog.e("UserWxLoginTask", "get web session.");
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer append = stringBuffer.append("code=").append(this.g).append("&appid=").append(this.f.mWxAppId).append("&version=1.1&sign=");
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f.mWxAppId).append("1.1");
            append.append(MD5.encrypt(stringBuffer2.toString()));
            XLHttpHeader xLHttpHeader = new XLHttpHeader("Content-Type", "application/x-www-form-urlencoded");
            g().j().a(new Header[]{xLHttpHeader}, "http://login.i.xunlei.com/tl/weixin_app", stringBuffer.toString().getBytes(), new AnonymousClass_2(this));
        } else if (this.e == 257) {
            XLLog.e("UserWxLoginTask", "get client session.");
            g().a(this.i, this.h, g().d(), 0, this.k, (Object) "get-client-sessionid", true);
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserThirdLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), XZBDevice.DOWNLOAD_LIST_FAILED, this.l, i(), bundle.getString("errorDesc"), j());
    }

    private String c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f.mWxAppId).append("1.1");
        return MD5.encrypt(stringBuffer.toString());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private void c(int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100103;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = this.m;
        XLStatUtil.getInstance().report(j(), xLStatPack);
    }

    static /* synthetic */ void d(t tVar, int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100103;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = tVar.m;
        XLStatUtil.getInstance().report(tVar.j(), xLStatPack);
    }
}
