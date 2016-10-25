package com.xunlei.common.pay;

public interface XLOnPayListener {
    void onAliPay(int i, String str, Object obj, int i2);

    void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp);

    void onGetPrice(int i, String str, Object obj, int i2, String str2);

    void onNbPay(int i, String str, Object obj, int i2);

    void onWxPay(int i, String str, Object obj, int i2);
}
