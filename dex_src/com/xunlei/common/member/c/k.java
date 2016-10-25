package com.xunlei.common.member.c;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.a.n;
import com.xunlei.common.member.act.XLQQLoginActivity;
import com.xunlei.common.member.act.XLQQParam;
import com.xunlei.common.member.b.h;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

// compiled from: UserQQLoginTask.java
public class k extends p {
    private String a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private XLQQParam g;
    private String h;
    private String i;
    private h j;
    private n k;
    private int l;
    private String m;

    public k(m mVar) {
        super(mVar);
        this.a = k.class.getSimpleName();
        this.f = 0;
        this.g = null;
        this.h = a.d;
        this.i = a.d;
        this.j = null;
        this.k = new n(this);
        this.l = 0;
        this.m = a.d;
    }

    public final boolean b() {
        if (this.f == 255) {
            Intent intent = new Intent(m.a().h(), XLQQLoginActivity.class);
            intent.putExtra("qq_app_id", this.g.mAppID);
            intent.putExtra("qq_task_id", j());
            intent.addFlags(268435456);
            g().h().startActivity(intent);
            XLLog.v(this.a, "start XLQQLoginActivity");
        } else if (this.f == 256) {
            Header[] headerArr = new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("appid=").append(this.g.mAppID).append("&openid=").append(this.j.a).append("&access_token=").append(this.j.b).append("&sign=").append(MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.j.b).append(this.g.mAppID).append(this.j.a).append("1.1").toString())).append("&version=1.1");
            m.a().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php?module=qq_app", headerArr, stringBuilder.toString().getBytes(), new AnonymousClass_2(this));
        } else if (this.f == 257) {
            g().a(Integer.valueOf(this.h).intValue(), this.i, g().d(), 0, this.k, (Object) "get-client-sessionid", true);
            XLLog.v("UserQQLoginTask", "get client session.");
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserThirdLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, this.l, i(), bundle.getString("errorDesc"), j());
    }

    public final void a(Object obj) {
        this.f = 255;
        this.g = (XLQQParam) obj;
    }

    public final void a(int i, h hVar) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100701;
        xLStatPack.mErrorCode = i;
        XLStatUtil.getInstance().report(j(), xLStatPack);
        XLStatUtil.getInstance().registerStatReq(j());
        g().b(j());
        if (i == 0 && hVar.a()) {
            this.j = hVar;
            b(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
            return;
        }
        c(i);
        b(258);
    }

    public final void a(int i) {
        super.a(i);
        c(i);
        b(258);
    }

    private void b(int i) {
        this.f = i;
        g().n().post(new AnonymousClass_1(this));
    }

    private void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private String c() {
        return MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.j.b).append(this.g.mAppID).append(this.j.a).append("1.1").toString());
    }

    private void d() {
        g().a(Integer.valueOf(this.h).intValue(), this.i, g().d(), 0, this.k, (Object) "get-client-sessionid", true);
        XLLog.v("UserQQLoginTask", "get client session.");
    }

    private void o() {
        Header[] headerArr = new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("appid=").append(this.g.mAppID).append("&openid=").append(this.j.a).append("&access_token=").append(this.j.b).append("&sign=").append(MD5.encrypt(new StringBuilder("nglei^*(352l{eltLNEGwwn").append(this.j.b).append(this.g.mAppID).append(this.j.a).append("1.1").toString())).append("&version=1.1");
        m.a().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php?module=qq_app", headerArr, stringBuilder.toString().getBytes(), new AnonymousClass_2(this));
    }

    private void g(int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100703;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = this.m;
        XLStatUtil.getInstance().report(j(), xLStatPack);
    }

    static /* synthetic */ void c(k kVar, int i) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100703;
        xLStatPack.mErrorCode = i;
        xLStatPack.mErrorMessage = kVar.m;
        XLStatUtil.getInstance().report(kVar.j(), xLStatPack);
    }
}
