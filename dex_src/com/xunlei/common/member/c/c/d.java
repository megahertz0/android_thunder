package com.xunlei.common.member.c.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;

// compiled from: UserUnBindOtherAccountTask.java
public final class d extends p {
    private int a;

    public d(m mVar) {
        super(mVar);
        this.a = -1;
    }

    public final boolean b() {
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        if (g().q()) {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer append = stringBuffer.append("module=unbind&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d()).append("&third_type=");
            if (this.a == 3) {
                i = R.styleable.Toolbar_navigationContentDescription;
            } else if (this.a == 2) {
                i = 1;
            } else if (this.a == 5) {
                i = XZBDevice.Delete;
            } else if (this.a != 4) {
                if (this.a == 1) {
                    i = -1;
                } else {
                    i = -1;
                }
            }
            append.append(i);
            XLHttpHeader xLHttpHeader = new XLHttpHeader("Content-Type", "application/x-www-form-urlencoded");
            g().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php", new Header[]{xLHttpHeader}, stringBuffer.toString().getBytes(), new AnonymousClass_1(this));
            return false;
        }
        XLLog.v(getClass().getSimpleName(), "user is not login!");
        c(XLErrorCode.OPERATION_INVALID);
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserUnBindeOtherAccount(bundle.getInt(Constants.KEY_ERROR_CODE), this.a, i(), bundle.getString("errorDesc"), j());
    }

    public final void b(int i) {
        this.a = i;
    }

    private void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private int c() {
        if (this.a == 3) {
            return R.styleable.Toolbar_navigationContentDescription;
        }
        if (this.a == 2) {
            return 1;
        }
        if (this.a == 5) {
            return XZBDevice.Delete;
        }
        if (this.a == 4) {
            return 4;
        }
        return this.a == 1 ? -1 : -1;
    }

    private static int g(int i) {
        int i2 = XLErrorCode.UNBIND_ERROR;
        if (i == 200) {
            return 0;
        }
        if (i == 407) {
            return R.styleable.Toolbar_collapseIcon;
        }
        if (i == 501) {
            i2 = R.styleable.Toolbar_collapseContentDescription;
        }
        return i == 600 ? XZBDevice.Upload : i2;
    }

    static /* synthetic */ int a(d dVar, int i) {
        int i2 = XLErrorCode.UNBIND_ERROR;
        if (i == 200) {
            return 0;
        }
        if (i == 407) {
            return R.styleable.Toolbar_collapseIcon;
        }
        if (i == 501) {
            i2 = R.styleable.Toolbar_collapseContentDescription;
        }
        return i == 600 ? XZBDevice.Upload : i2;
    }
}
