package com.tencent.mm.sdk.a.a;

import com.tencent.mm.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class b {
    public static byte[] a(String str, int i, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        return a.c(stringBuffer.toString().substring(1, XZBDevice.Pause).getBytes()).getBytes();
    }
}
