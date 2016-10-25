package com.xunlei.common.pay.a;

import com.xunlei.common.pay.XLContractor;
import com.xunlei.common.pay.XLContractor$XLContractType;
import com.xunlei.common.pay.c.a.c;
import com.xunlei.common.pay.c.b.d;
import org.android.spdy.SpdyProtocol;

// compiled from: XLContractorFactory.java
public class a {
    public String a;
    public String b;
    public String c;

    public static XLContractor a(int i) {
        switch (i) {
            case SpdyProtocol.SLIGHTSSL_0_RTT_MODE:
                return new c(f.a());
            case XLContractor$XLContractType.XL_CONTRACT_WX:
                return new d(f.a());
            default:
                return null;
        }
    }

    public a(String str, String str2, String str3) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }
}
