package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public enum a {
    None(IXAdSystemUtils.NT_NONE),
    WapPay("js://wappay"),
    Update("js://update");
    private String d;

    static {
        String str = IXAdSystemUtils.NT_NONE;
        a = new a("None", 0, IXAdSystemUtils.NT_NONE);
        str = "js://wappay";
        b = new a("WapPay", 1, "js://wappay");
        str = "js://update";
        c = new a("Update", 2, "js://update");
        e = new a[]{a, b, c};
    }

    private a(String str) {
        this.d = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return a;
        }
        a aVar = a;
        a[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            a aVar2 = values[i];
            if (str.startsWith(aVar2.d)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
