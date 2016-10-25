package com.xunlei.common.accelerator.bean;

public class XLAccelTryInfo {
    public int mNumOfTry;
    public int mRemainTime;
    public int mTryDuration;

    public XLAccelTryInfo() {
        this.mNumOfTry = 0;
        this.mTryDuration = 0;
        this.mRemainTime = -1;
    }

    public String toString() {
        return new StringBuilder("mNumOfTry = ").append(this.mNumOfTry).append("\n mTryDuration = ").append(this.mTryDuration).toString();
    }
}
