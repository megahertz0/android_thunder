package com.xunlei.common.pay;

import com.xunlei.common.pay.param.XLPayParam;

public interface XLContractor {
    int userContract(XLPayParam xLPayParam, Object obj);

    int userDisContract(XLPayParam xLPayParam, Object obj);

    int userQuery(XLPayParam xLPayParam, Object obj);
}
