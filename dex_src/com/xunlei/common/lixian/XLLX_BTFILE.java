package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;

public class XLLX_BTFILE {
    public int fileindex;
    public String filename;
    public long filesize;
    public int pitchon;

    public XLLX_BTFILE() {
        this.fileindex = 0;
        this.filename = BuildConfig.VERSION_NAME;
        this.filesize = 0;
        this.pitchon = 0;
    }
}
