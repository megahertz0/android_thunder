package com.xunlei.common.stat;

import com.umeng.a;

public class XLStatPack {
    public int mCommandID;
    public int mErrorCode;
    public String mErrorMessage;
    public String mReportDate;
    public String mReqUrl;
    public double mRespTime;
    public int mRetryNum;
    public String mSvrDomain;
    public String mSvrIp;
    public int mUserId;

    public XLStatPack() {
        this.mReqUrl = a.d;
        this.mErrorCode = -1;
        this.mRespTime = 0.0d;
        this.mRetryNum = 0;
        this.mSvrIp = a.d;
        this.mSvrDomain = a.d;
        this.mCommandID = 0;
        this.mReportDate = a.d;
        this.mUserId = 0;
        this.mErrorMessage = a.d;
    }
}
