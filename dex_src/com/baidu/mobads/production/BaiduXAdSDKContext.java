package com.baidu.mobads.production;

import com.baidu.mobads.h.g;

public class BaiduXAdSDKContext {
    protected static Boolean isRemoteLoadSuccess;
    protected static g mApkLoader;

    static {
        isRemoteLoadSuccess = Boolean.valueOf(false);
    }

    public static void exit() {
        mApkLoader = null;
        isRemoteLoadSuccess = Boolean.valueOf(false);
    }
}
