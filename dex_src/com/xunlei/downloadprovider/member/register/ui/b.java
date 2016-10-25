package com.xunlei.downloadprovider.member.register.ui;

import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.member.login.j.a;

// compiled from: BindMobileActivity.java
final class b extends a {
    final /* synthetic */ BindMobileActivity a;

    b(BindMobileActivity bindMobileActivity) {
        this.a = bindMobileActivity;
    }

    public final boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
        new StringBuilder("onUserAdSendMessage() errorCode=").append(i).append(" errorDesc=").append(str);
        this.a.f();
        if (i == 0) {
            com.xunlei.downloadprovider.member.register.a.a(MsgConstant.KEY_SUCCESS, i);
            this.a.h();
            this.a.a(2131230882);
        } else {
            com.xunlei.downloadprovider.member.register.a.a(MsgConstant.KEY_FAIL, i);
            switch (i) {
                case XLErrorCode.AQ_BINDED_MOBILE_ERROR:
                    this.a.b(2131230883);
                    break;
                default:
                    this.a.b(2131230884);
                    break;
            }
        }
        return false;
    }

    public final boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
        new StringBuilder("onUserAqBindMobile() errorCode=").append(i).append(" errorDesc=").append(str);
        this.a.f();
        if (i == 0) {
            com.xunlei.downloadprovider.member.register.a.b(MsgConstant.KEY_SUCCESS, i);
            this.a.finish();
        } else {
            com.xunlei.downloadprovider.member.register.a.b(MsgConstant.KEY_FAIL, i);
            switch (i) {
                case XLErrorCode.AQ_HAD_MOBILE_ERROR:
                    this.a.b(2131230879);
                    break;
                case XLErrorCode.AQ_BINDED_MOBILE_ERROR:
                    this.a.b(2131230883);
                    break;
                default:
                    this.a.b(2131230881);
                    break;
            }
        }
        return false;
    }
}
