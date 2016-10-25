package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;

public class XLYB_BTSUBFILE {
    public String cid;
    public int duration;
    public int fileindex;
    public String filename;
    public long filesize;
    public String gcid;
    public String urlhash;

    public XLYB_BTSUBFILE() {
        this.filename = BuildConfig.VERSION_NAME;
        this.urlhash = BuildConfig.VERSION_NAME;
        this.fileindex = 0;
        this.cid = BuildConfig.VERSION_NAME;
        this.gcid = BuildConfig.VERSION_NAME;
        this.filesize = 0;
        this.duration = 0;
    }
}
