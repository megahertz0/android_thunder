package com.baidu.mobads.e;

import android.text.TextUtils;
import com.baidu.mobads.interfaces.error.IXAdErrorCode;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.tencent.open.SocialConstants;
import java.util.Map;

public class a implements IXAdErrorCode {
    protected final IXAdLogger a;

    public a(IXAdLogger iXAdLogger) {
        this.a = iXAdLogger;
    }

    public void printErrorMessage(XAdErrorCode xAdErrorCode, String str) {
        this.a.e(genCompleteErrorMessage(xAdErrorCode, str));
    }

    public void printErrorMessage(String str, String str2, String str3) {
        this.a.e(a(str, str2, str3));
    }

    public String genCompleteErrorMessage(XAdErrorCode xAdErrorCode, String str) {
        return xAdErrorCode == null ? com.umeng.a.d : a(xAdErrorCode.getCode(), xAdErrorCode.getMessage(), str);
    }

    private String a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ErrorCode: [");
        stringBuilder.append(str);
        stringBuilder.append("]; ErrorDesc: [");
        stringBuilder.append(str2);
        stringBuilder.append("];");
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(" Extra: [");
            stringBuilder.append(str3);
            stringBuilder.append("];");
        }
        return stringBuilder.toString();
    }

    public String getMessage(Map<String, Object> map) {
        if (map == null) {
            return com.umeng.a.d;
        }
        return m.a().q().genCompleteErrorMessage((XAdErrorCode) map.get(SocialConstants.PARAM_SEND_MSG), com.umeng.a.d);
    }
}
