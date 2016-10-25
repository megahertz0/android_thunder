package com.baidu.mobads.interfaces.error;

import java.util.Map;

public interface IXAdErrorCode {
    String genCompleteErrorMessage(XAdErrorCode xAdErrorCode, String str);

    String getMessage(Map<String, Object> map);

    void printErrorMessage(XAdErrorCode xAdErrorCode, String str);

    void printErrorMessage(String str, String str2, String str3);
}
