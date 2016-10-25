package com.xunlei.downloadprovider.member.payment.bean;

public class VoucherStateResultBean {
    public static final int STATE_AVAIABLE = 0;
    public static final int STATE_LOCKED = 3;
    public static final int STATE_NO_EXIST = 5;
    public static final int STATE_NO_OWN = 6;
    public static final int STATE_OVERDUE = 2;
    public static final int STATE_PARAM_ERROR = 1;
    public static final int STATE_USED = 4;
    private int cashMinus;
    private String limitMonth;
    private String min;
    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getCashMinus() {
        return this.cashMinus;
    }

    public void setCashMinus(int i) {
        this.cashMinus = i;
    }

    public String getMin() {
        return this.min;
    }

    public void setMin(String str) {
        this.min = str;
    }

    public String getLimitMonth() {
        return this.limitMonth;
    }

    public void setLimitMonth(String str) {
        this.limitMonth = str;
    }
}
