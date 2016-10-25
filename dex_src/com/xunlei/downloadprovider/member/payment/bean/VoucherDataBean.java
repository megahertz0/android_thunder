package com.xunlei.downloadprovider.member.payment.bean;

import java.util.Map;

public class VoucherDataBean {
    private int defaultVoucherNum;
    private Map<Integer, String> voucherList;

    public Map<Integer, String> getVoucherList() {
        return this.voucherList;
    }

    public void setVoucherList(Map<Integer, String> map) {
        this.voucherList = map;
    }

    public int getDefaultVoucherNum() {
        return this.defaultVoucherNum;
    }

    public void setDefaultVoucherNum(int i) {
        this.defaultVoucherNum = i;
    }
}
