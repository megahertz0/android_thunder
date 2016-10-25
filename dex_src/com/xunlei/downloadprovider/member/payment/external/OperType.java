package com.xunlei.downloadprovider.member.payment.external;

public enum OperType {
    NORMAL,
    UPGRADE;

    static {
        NORMAL = new OperType("NORMAL", 0);
        UPGRADE = new OperType("UPGRADE", 1);
        a = new OperType[]{NORMAL, UPGRADE};
    }
}
