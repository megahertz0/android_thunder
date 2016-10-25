package com.xunlei.common.pay.param;

import android.app.Activity;

public class XLAlipayParam extends XLPayParam {
    public Activity mActivity;
    public int mNeedLoading;

    public XLAlipayParam() {
        this.mActivity = null;
        this.mNeedLoading = 1;
    }
}
