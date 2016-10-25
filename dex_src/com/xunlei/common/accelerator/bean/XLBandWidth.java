package com.xunlei.common.accelerator.bean;

import com.umeng.message.proguard.j;

public class XLBandWidth {
    public long mDownStream;
    public long mUpStream;

    public XLBandWidth() {
        this.mUpStream = 0;
        this.mDownStream = 0;
    }

    public String toString() {
        return new StringBuilder("(mUpStream = ").append(this.mUpStream).append(", mDownStream = ").append(this.mDownStream).append(j.t).toString();
    }
}
