package com.xunlei.common.member.c.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;

// compiled from: UserCheckBindOtherAccountTask.java
public final class b extends p {
    private XLBindedOtherAccountItem[] a;

    public b(m mVar) {
        super(mVar);
        this.a = new XLBindedOtherAccountItem[0];
    }

    public final boolean b() {
        if (g().q()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("module=bind_list&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d());
            XLHttpHeader xLHttpHeader = new XLHttpHeader("Content-Type", "application/x-www-form-urlencoded");
            g().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php", new Header[]{xLHttpHeader}, stringBuffer.toString().getBytes(), new AnonymousClass_1(this));
            return false;
        }
        XLLog.v(getClass().getSimpleName(), "user is not login!");
        b((int) XLErrorCode.OPERATION_INVALID);
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserGetBindedOtherAccount(bundle.getInt(Constants.KEY_ERROR_CODE), this.a, i(), bundle.getString("errorDesc"), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private static int a(String str) {
        if (Token.WX_TOKEN_PLATFORMID_VALUE.equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_FAILED;
        }
        if ("sina".equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        if ("qq".equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        }
        if (Alipay.Name.equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_ALL;
        }
        return "xm".equals(str) ? 1 : -1;
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        return i == 502 ? R.styleable.Toolbar_maxButtonHeight : XLErrorCode.UNKNOWN_ERROR;
    }

    static /* synthetic */ int a(b bVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        return i == 502 ? R.styleable.Toolbar_maxButtonHeight : XLErrorCode.UNKNOWN_ERROR;
    }

    static /* synthetic */ int a(b bVar, String str) {
        if (Token.WX_TOKEN_PLATFORMID_VALUE.equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_FAILED;
        }
        if ("sina".equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        if ("qq".equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        }
        if (Alipay.Name.equals(str)) {
            return XZBDevice.DOWNLOAD_LIST_ALL;
        }
        return "xm".equals(str) ? 1 : -1;
    }
}
