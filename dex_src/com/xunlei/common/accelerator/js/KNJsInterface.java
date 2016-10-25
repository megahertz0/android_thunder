package com.xunlei.common.accelerator.js;

public interface KNJsInterface {
    String getBandInfo();

    int getRemainTime();

    String getTryInfo();

    String getUserInfo();

    void queryStatus(String str);

    void recoverQuery(String str);

    void startAccel(String str);

    void startLogin();

    void startPay();

    void stopAccel(String str);
}
