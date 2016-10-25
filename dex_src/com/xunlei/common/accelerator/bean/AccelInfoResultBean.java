package com.xunlei.common.accelerator.bean;

import com.xunlei.xiazaibao.BuildConfig;

public class AccelInfoResultBean extends BaseResultBean {
    private String dialAccount;
    private XLAccelBandInfo xlAccelBandInfo;

    public AccelInfoResultBean() {
        this.dialAccount = BuildConfig.VERSION_NAME;
    }

    public String getDialAccount() {
        return this.dialAccount;
    }

    public void setDialAccount(String str) {
        this.dialAccount = str;
    }

    public XLAccelBandInfo getXlAccelBandInfo() {
        return this.xlAccelBandInfo;
    }

    public void setXlAccelBandInfo(XLAccelBandInfo xLAccelBandInfo) {
        this.xlAccelBandInfo = xLAccelBandInfo;
    }
}
