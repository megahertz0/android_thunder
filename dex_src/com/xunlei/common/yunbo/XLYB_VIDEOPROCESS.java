package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;

public class XLYB_VIDEOPROCESS {
    public static final int URLTYPE_URL = 1;
    public static final int URLTYPE_URLHASH = 2;
    public int process;
    public int status;
    public String url;
    public int urltype;

    public XLYB_VIDEOPROCESS() {
        this.urltype = 0;
        this.url = BuildConfig.VERSION_NAME;
        this.status = 0;
        this.process = 0;
    }
}
