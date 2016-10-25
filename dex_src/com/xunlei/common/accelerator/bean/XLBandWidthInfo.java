package com.xunlei.common.accelerator.bean;

import com.umeng.message.proguard.j;
import com.xunlei.xiazaibao.BuildConfig;

public class XLBandWidthInfo {
    public String mDialAccount;
    public String mProvince;
    public String mProvinceName;
    public String mServiceProvider;
    public String mServiceProviderName;

    public XLBandWidthInfo() {
        this.mServiceProvider = BuildConfig.VERSION_NAME;
        this.mServiceProviderName = BuildConfig.VERSION_NAME;
        this.mDialAccount = BuildConfig.VERSION_NAME;
        this.mProvince = BuildConfig.VERSION_NAME;
        this.mProvinceName = BuildConfig.VERSION_NAME;
    }

    public String toString() {
        return new StringBuilder("(mServiceProvider = ").append(this.mServiceProvider).append("\nmServiceProviderName = ").append(this.mServiceProviderName).append("\nmDialAccount = ").append(this.mDialAccount).append("\n mProvinceName = ").append(this.mProvinceName).append(j.t).toString();
    }
}
