package com.xunlei.xiazaibao.sdk.synctasks;

import com.xunlei.xiazaibao.sdk.tools.XZBLog;

public class XZBDownloadLogin extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private String mPid;

    static {
        TAG = XZBDownloadLogin.class.getSimpleName();
    }

    public XZBDownloadLogin(String str) {
        this.mPid = str;
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl()).append("/login");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }
}
