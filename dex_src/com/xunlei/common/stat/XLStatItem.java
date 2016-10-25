package com.xunlei.common.stat;

public class XLStatItem {
    public int mRequestCommandID;
    public long mRequestTime;
    public long mRespTime;
    public int mTaskCookie;

    public XLStatItem() {
        this.mTaskCookie = 0;
        this.mRequestTime = 0;
        this.mRespTime = 0;
        this.mRequestCommandID = 0;
    }
}
