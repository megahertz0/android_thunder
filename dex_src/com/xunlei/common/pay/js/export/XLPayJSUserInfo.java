package com.xunlei.common.pay.js.export;

import com.xunlei.xiazaibao.BuildConfig;

public class XLPayJSUserInfo {
    public String accessToken;
    public String sessionId;
    public int userId;
    public String wxAppId;

    public XLPayJSUserInfo() {
        this.userId = 0;
        this.accessToken = BuildConfig.VERSION_NAME;
        this.sessionId = BuildConfig.VERSION_NAME;
        this.wxAppId = BuildConfig.VERSION_NAME;
    }
}
