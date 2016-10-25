package com.xunlei.common.member.c.b;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserGetRecommendAvatarsTask.java
public final class a extends p {
    private final String a;
    private XLAvatarItem[] b;

    static /* synthetic */ int a(a aVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }

    public a(m mVar) {
        super(mVar);
    }

    public final boolean b() {
        String format = String.format("http://img.user.kanimg.com/back/36063/avatar/get_recommend_avatars?sessionid=%s&userid=%d", new Object[]{h().getStringValue(USERINFOKEY.SessionID), Integer.valueOf(h().getIntValue(USERINFOKEY.UserID))});
        XLLog.v(getClass().getSimpleName(), new StringBuilder("UserGetRecommendAvatarsTask execute url = ").append(format).toString());
        g().k().get(g().h(), format, null, new AnonymousClass_1(this));
        return false;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserGetRecommendAvatars(bundle.getInt(Constants.KEY_ERROR_CODE), this.b, i(), bundle.getString("errorDesc"), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }

    static /* synthetic */ void b(a aVar, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        aVar.g().a((p) aVar, bundle);
    }
}
