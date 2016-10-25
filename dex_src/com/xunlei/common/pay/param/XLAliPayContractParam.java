package com.xunlei.common.pay.param;

import com.umeng.a;

public class XLAliPayContractParam extends XLPayParam {
    public String mContractResultScheme;
    public int mOperateType;
    public boolean mQueryAllContract;

    public XLAliPayContractParam() {
        this.mContractResultScheme = a.d;
        this.mQueryAllContract = true;
        this.mOperateType = 0;
    }
}
