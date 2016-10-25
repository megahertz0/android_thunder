package com.xunlei.android.xlstat.param;

import com.xunlei.android.xlstat.XLLog;

public class XLStatParam {
    private static final String TAG = "XLStatParam";
    public String mAppKey;
    public String mAppName;
    public String mAppVersion;
    public String mConfigBuffer;
    public String mConfigFileName;
    public String mConfigPath;
    public String mGuid;
    public String mProductKey;
    public String mProductName;
    public String mProductVersion;
    public String mStoragePath;

    public boolean checkParam() {
        if (this.mAppKey != null && this.mAppName != null && this.mAppVersion != null && this.mGuid != null && this.mProductKey != null && this.mProductName != null && this.mProductVersion != null && this.mStoragePath != null && this.mConfigPath != null && this.mConfigFileName != null && this.mConfigBuffer != null) {
            return true;
        }
        XLLog.b(TAG, new StringBuilder("checkParam, mAppKey=").append(this.mAppKey).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mAppName= ").append(this.mAppName).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mAppVersion= ").append(this.mAppVersion).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mGuid= ").append(this.mGuid).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mProductKey= ").append(this.mProductKey).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mProductName= ").append(this.mProductName).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mProductVersion= ").append(this.mProductVersion).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mStoragePath= ").append(this.mStoragePath).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mConfigPath= ").append(this.mConfigPath).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mConfigFileName= ").append(this.mConfigFileName).toString());
        XLLog.b(TAG, new StringBuilder("checkParam, mConfigBuffer= ").append(this.mConfigBuffer).toString());
        return false;
    }
}
