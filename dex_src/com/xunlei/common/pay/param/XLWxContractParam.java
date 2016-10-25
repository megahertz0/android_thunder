package com.xunlei.common.pay.param;

import com.umeng.a;

public class XLWxContractParam extends XLPayParam {
    public int mOperateType;
    public boolean mQueryAllContract;
    public String mWxAppId;

    public XLWxContractParam() {
        this.mWxAppId = a.d;
        this.mQueryAllContract = true;
        this.mOperateType = 0;
    }
}
