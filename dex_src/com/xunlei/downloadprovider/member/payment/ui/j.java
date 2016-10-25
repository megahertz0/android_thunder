package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.downloadprovider.member.payment.external.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BasePayActivity.java
final class j extends f {
    final /* synthetic */ BasePayActivity a;

    j(BasePayActivity basePayActivity) {
        this.a = basePayActivity;
    }

    public final void onWxPay(int i, String str, Object obj, int i2) {
        new StringBuilder("onWxPayComplete--errorCode=").append(i).append("|errorDesc=").append(str).append("|userData=").append(obj).append("| taskId=").append(i2);
        this.a.b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (obj != null && (obj instanceof XLPayParam)) {
            this.a.a(i, str, (XLPayParam) obj, 1);
        }
    }

    public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
        this.a.a(i, obj, i2, str2);
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        this.a.b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (this.a.d == i2) {
            if (obj != null) {
                this.a.e = (XLPayParam) obj;
                if (i == 102) {
                    BasePayActivity.b(this.a, this.a.e);
                } else if (i != 0) {
                    this.a.a(i, str, this.a.g, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
            }
        } else if (this.a.f == i2 && obj != null) {
            this.a.g = (XLPayParam) obj;
            if (i == 54) {
                this.a.a(i, str, this.a.g, 1);
            }
        }
    }

    public final void onAliPay(int i, String str, Object obj, int i2) {
        new StringBuilder("onAliPayComplete--errorCode=").append(i).append("|errorDesc=").append(str).append("|userData=").append(obj).append("| taskId=").append(i2);
        this.a.b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (obj != null && (obj instanceof XLPayParam)) {
            this.a.a(i, str, (XLPayParam) obj, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }
}
