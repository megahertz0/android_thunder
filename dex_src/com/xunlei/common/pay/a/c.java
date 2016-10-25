package com.xunlei.common.pay.a;

import com.xunlei.common.pay.XLPayType;
import com.xunlei.common.pay.param.XLPayParam;

// compiled from: XLPayRequestFactory.java
public final class c {
    private c() {
    }

    public static b a(XLPayParam xLPayParam) {
        switch (xLPayParam.mPayBusinessType) {
            case XLPayType.XL_GET_PRICE:
                return new b(xLPayParam);
            default:
                return null;
        }
    }
}
