package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;

public class XLYB_REQPLAYINFO {
    public String cid;
    public String clientid;
    public long filesize;
    public String gcid;
    public int index;
    public int operationid;
    public String rsa_exponent;
    public String rsa_module;
    public String url;
    public String verify;
    public int vod_type;

    public XLYB_REQPLAYINFO() {
        this.clientid = BuildConfig.VERSION_NAME;
        this.rsa_module = BuildConfig.VERSION_NAME;
        this.rsa_exponent = BuildConfig.VERSION_NAME;
        this.verify = BuildConfig.VERSION_NAME;
        this.operationid = 0;
        this.url = BuildConfig.VERSION_NAME;
        this.index = 0;
        this.gcid = BuildConfig.VERSION_NAME;
        this.cid = BuildConfig.VERSION_NAME;
        this.filesize = 0;
        this.vod_type = 0;
    }

    public void copyfrom(XLYB_REQPLAYINFO xlyb_reqplayinfo) {
        this.cid = xlyb_reqplayinfo.cid;
        this.gcid = xlyb_reqplayinfo.gcid;
        this.filesize = xlyb_reqplayinfo.filesize;
        this.verify = xlyb_reqplayinfo.verify;
        this.vod_type = xlyb_reqplayinfo.vod_type;
        this.clientid = xlyb_reqplayinfo.clientid;
        this.index = xlyb_reqplayinfo.index;
        this.operationid = xlyb_reqplayinfo.operationid;
        this.url = xlyb_reqplayinfo.url;
        this.rsa_exponent = xlyb_reqplayinfo.rsa_exponent;
        this.rsa_module = xlyb_reqplayinfo.rsa_module;
    }
}
