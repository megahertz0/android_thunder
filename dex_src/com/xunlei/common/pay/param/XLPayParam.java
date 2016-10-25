package com.xunlei.common.pay.param;

import com.umeng.a;

public class XLPayParam {
    public String mAccessToken;
    public int mMonth;
    public String mOrderExtraParam;
    public int mOrderType;
    public String mOrderVoucher;
    public String mParamExt1;
    public String mParamExt2;
    public String mParamOther1;
    public String mParamOther2;
    public String mParamOther4;
    public int mPayBusinessType;
    public int mPayType;
    public String mReferFrom;
    public String mSessionId;
    public String mSource;
    public int mUserId;
    public int mVasType;

    public XLPayParam() {
        this.mPayType = 268435457;
        this.mPayBusinessType = 536870913;
        this.mUserId = 0;
        this.mSessionId = a.d;
        this.mAccessToken = a.d;
        this.mVasType = 0;
        this.mMonth = 1;
        this.mSource = a.d;
        this.mReferFrom = a.d;
        this.mOrderType = 0;
        this.mOrderExtraParam = a.d;
        this.mOrderVoucher = a.d;
        this.mParamOther1 = a.d;
        this.mParamOther2 = a.d;
        this.mParamOther4 = a.d;
        this.mParamExt1 = a.d;
        this.mParamExt2 = a.d;
    }
}
