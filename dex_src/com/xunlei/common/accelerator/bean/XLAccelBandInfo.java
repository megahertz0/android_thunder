package com.xunlei.common.accelerator.bean;

public class XLAccelBandInfo {
    public XLBandWidthInfo mBandWidthInfo;
    public int mCanUpgrade;
    public XLBandWidth mCurrentBandWidth;
    public XLBandWidth mMaxBandWidth;

    public XLAccelBandInfo() {
        this.mCanUpgrade = 0;
        this.mCurrentBandWidth = null;
        this.mMaxBandWidth = null;
        this.mBandWidthInfo = null;
        this.mCurrentBandWidth = new XLBandWidth();
        this.mMaxBandWidth = new XLBandWidth();
        this.mBandWidthInfo = new XLBandWidthInfo();
    }

    public String toString() {
        return new StringBuilder("mCanUpgrade = ").append(this.mCanUpgrade).append("\nmCurrentBandWidth = ").append(this.mCurrentBandWidth).append("\nmMaxBandWidth = ").append(this.mMaxBandWidth).append("\nmBandwidthInfo = ").append(this.mBandWidthInfo).toString();
    }
}
