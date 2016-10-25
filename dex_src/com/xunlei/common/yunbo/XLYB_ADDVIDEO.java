package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;

public class XLYB_ADDVIDEO {
    public String gcid;
    public int index;
    public String name;
    public int result;
    public String url;
    public String url_hash;

    public XLYB_ADDVIDEO() {
        this.result = 0;
        this.index = 0;
        this.url = BuildConfig.VERSION_NAME;
        this.name = BuildConfig.VERSION_NAME;
        this.url_hash = BuildConfig.VERSION_NAME;
        this.gcid = BuildConfig.VERSION_NAME;
    }
}
