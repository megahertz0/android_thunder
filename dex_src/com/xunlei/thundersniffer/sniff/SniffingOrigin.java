package com.xunlei.thundersniffer.sniff;

import com.xunlei.xiazaibao.BuildConfig;

public class SniffingOrigin {
    public static final int ORIGIN_360 = 2;
    public static final int ORIGIN_BAIDU = 1;
    public static final int ORIGIN_DETAIL = 0;
    public static final int ORIGIN_NONE = -1;
    public static final int ORIGIN_SM = 3;
    public static final int ORIGIN_SOGOU = 4;
    public int origin;
    public int pageNo;
    public String pageUrl;

    public SniffingOrigin(String str, int i) {
        this.origin = -1;
        this.pageUrl = BuildConfig.VERSION_NAME;
        this.pageNo = -1;
        this.origin = i;
        this.pageUrl = str;
    }

    public SniffingOrigin(String str) {
        this.origin = -1;
        this.pageUrl = BuildConfig.VERSION_NAME;
        this.pageNo = -1;
        this.pageUrl = str;
    }

    public SniffingOrigin() {
        this.origin = -1;
        this.pageUrl = BuildConfig.VERSION_NAME;
        this.pageNo = -1;
    }
}
