package com.xunlei.common.pay.js;

import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.js.export.XLPayJSInterface;

// compiled from: XLPayJSListener.java
public final class a implements XLOnPayListener {
    private XLPayJSInterface a;

    public a(XLPayJSInterface xLPayJSInterface) {
        this.a = null;
        this.a = xLPayJSInterface;
    }

    public final void onWxPay(int i, String str, Object obj, int i2) {
        this.a.receiveMessage(i, str, obj, i2);
    }

    public final void onAliPay(int i, String str, Object obj, int i2) {
        this.a.receiveMessage(i, str, obj, i2);
    }

    public final void onNbPay(int i, String str, Object obj, int i2) {
        this.a.receiveMessage(i, str, obj, i2);
    }

    public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
    }
}
