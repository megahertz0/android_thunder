package com.xunlei.downloadprovider.web.base.core;

import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import java.util.HashMap;
import java.util.Map;

// compiled from: DefaultJsInterface.java
final class o implements XLOnPayListener {
    final /* synthetic */ DefaultJsInterface a;

    o(DefaultJsInterface defaultJsInterface) {
        this.a = defaultJsInterface;
    }

    public final void onAliPay(int i, String str, Object obj, int i2) {
        XLPayUtil.getInstance().detachListener(this);
        if (obj != null && (obj instanceof String)) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i));
            hashMap.put("payment", "alipay");
            this.a.callback((String) obj, hashMap);
        }
    }

    public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
    }

    public final void onNbPay(int i, String str, Object obj, int i2) {
    }

    public final void onWxPay(int i, String str, Object obj, int i2) {
        XLPayUtil.getInstance().detachListener(this);
        if (obj != null && (obj instanceof String)) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i));
            hashMap.put("payment", "weixin");
            this.a.callback((String) obj, hashMap);
        }
    }
}
