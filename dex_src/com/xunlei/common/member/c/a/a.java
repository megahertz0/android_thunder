package com.xunlei.common.member.c.a;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import org.apache.http.Header;

// compiled from: UserAqBindMobileTask.java
public final class a extends p {
    private String a;
    private String b;

    public a(m mVar) {
        super(mVar);
        this.a = com.umeng.a.d;
        this.b = com.umeng.a.d;
    }

    public final void a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean b() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://dynamic.aq.xunlei.com/interface/script?m=third_set_mobile_check&mobile=").append(this.a).append("&code=").append(this.b).append("&bussnessid=").append(g().d());
        new StringBuffer().append("userid=").append(h().getLongValue(USERINFOKEY.UserID)).append(";sessionid=").append(h().getStringValue(USERINFOKEY.SessionID));
        g().k().get(g().h(), stringBuffer.toString(), new Header[]{new XLHttpHeader("Cookie", r1.toString())}, new AnonymousClass_1(this));
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserAqBindMobile(bundle.getInt(Constants.KEY_ERROR_CODE), bundle.getString("errorDesc"), com.umeng.a.d, i(), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    static /* synthetic */ void a(a aVar, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        aVar.g().a((p) aVar, bundle);
    }
}
