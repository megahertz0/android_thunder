package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserGetLixianCapacityTask.java
public final class d extends p {
    private XLLixianCapacity a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.c.d.a(java.lang.String):boolean");
        /*
        this = this;
        r8 = -1;
        r0 = 0;
        if (r10 == 0) goto L_0x0013;
    L_0x0004:
        r1 = new com.xunlei.common.member.XLLixianCapacity;	 Catch:{ NumberFormatException -> 0x0097 }
        r1.<init>();	 Catch:{ NumberFormatException -> 0x0097 }
        r9.a = r1;	 Catch:{ NumberFormatException -> 0x0097 }
        r1 = 44;
        r1 = r10.indexOf(r1);	 Catch:{ NumberFormatException -> 0x0097 }
        if (r1 != r8) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = 0;
        r2 = r10.substring(r2, r1);	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = r9.a;	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = java.lang.Double.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r4 = r2.doubleValue();	 Catch:{ NumberFormatException -> 0x0097 }
        r3.total_capacity = r4;	 Catch:{ NumberFormatException -> 0x0097 }
        r1 = r1 + 1;
        r1 = r10.substring(r1);	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = 44;
        r2 = r1.indexOf(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        if (r2 == r8) goto L_0x0013;
    L_0x0033:
        r3 = 0;
        r3 = r1.substring(r3, r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r4 = r9.a;	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = java.lang.Double.valueOf(r3);	 Catch:{ NumberFormatException -> 0x0097 }
        r6 = r3.doubleValue();	 Catch:{ NumberFormatException -> 0x0097 }
        r4.used_capacity = r6;	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = r2 + 1;
        r1 = r1.substring(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = 44;
        r2 = r1.indexOf(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        if (r2 == r8) goto L_0x0013;
    L_0x0052:
        r3 = 0;
        r3 = r1.substring(r3, r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r4 = r9.a;	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = r3.intValue();	 Catch:{ NumberFormatException -> 0x0097 }
        r4.finished_task = r3;	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = r2 + 1;
        r1 = r1.substring(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = 44;
        r2 = r1.indexOf(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        if (r2 == r8) goto L_0x0013;
    L_0x0071:
        r3 = 0;
        r3 = r1.substring(r3, r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r4 = r9.a;	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ NumberFormatException -> 0x0097 }
        r3 = r3.intValue();	 Catch:{ NumberFormatException -> 0x0097 }
        r4.expired_task = r3;	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = r2 + 1;
        r1 = r1.substring(r2);	 Catch:{ NumberFormatException -> 0x0097 }
        r2 = r9.a;	 Catch:{ NumberFormatException -> 0x0097 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ NumberFormatException -> 0x0097 }
        r1 = r1.intValue();	 Catch:{ NumberFormatException -> 0x0097 }
        r2.once_task = r1;	 Catch:{ NumberFormatException -> 0x0097 }
        r0 = 1;
        goto L_0x0013;
    L_0x0097:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
        */
    }

    public d(m mVar) {
        super(mVar);
        this.a = null;
    }

    public final boolean b() {
        if (g().q()) {
            d(a.b);
            g().j().a(g().j().b((int) XZBDevice.Stop) + g().i().getStringValue(USERINFOKEY.UserID), new AnonymousClass_1(this));
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "user_get_lixian_capacity");
        bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.OPERATION_INVALID);
        g().a((p) this, bundle);
        return false;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "user_get_lixian_capacity") {
            return false;
        }
        return xLOnUserListener.onLixianCatched(bundle.getInt(Constants.KEY_ERROR_CODE), h(), this.a, i(), j());
    }
}
