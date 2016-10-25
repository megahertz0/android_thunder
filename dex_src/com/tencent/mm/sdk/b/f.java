package com.tencent.mm.sdk.b;

import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class f {
    public final String toString() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace == null || stackTrace.length < 4) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = XZBDevice.DOWNLOAD_LIST_FAILED;
        while (i < stackTrace.length) {
            if (stackTrace[i].getClassName().contains(WXApp.WXAPP_PACKAGE_NAME) && !stackTrace[i].getClassName().contains("sdk.platformtools.Log")) {
                stringBuilder.append("[");
                stringBuilder.append(stackTrace[i].getClassName().substring(XZBDevice.Delete));
                stringBuilder.append(":");
                stringBuilder.append(stackTrace[i].getMethodName());
                stringBuilder.append(new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(stackTrace[i].getLineNumber()).append(")]").toString());
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
