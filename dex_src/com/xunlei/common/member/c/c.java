package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserGetHighSpeedCapacityTask.java
public final class c extends p {
    private XLHspeedCapacity a;

    public c(m mVar) {
        super(mVar);
        this.a = null;
    }

    public final boolean b() {
        if (g().q()) {
            d(a.b);
            g().j().a(g().j().b((int) XZBDevice.Pause) + g().i().getStringValue(USERINFOKEY.UserID), new AnonymousClass_1(this));
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "user_get_high_speed_capacity");
        bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.OPERATION_INVALID);
        g().a((p) this, bundle);
        return false;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "user_get_high_speed_capacity") {
            return false;
        }
        return xLOnUserListener.onHighSpeedCatched(bundle.getInt(Constants.KEY_ERROR_CODE), h(), this.a, i(), j());
    }
}
