package com.qq.e.comm.managers.status;

public enum Carrier {
    UNKNOWN(0),
    CMCC(1),
    UNICOM(2),
    TELECOM(3);
    private int a;

    static {
        UNKNOWN = new Carrier("UNKNOWN", 0, 0);
        CMCC = new Carrier("CMCC", 1, 1);
        UNICOM = new Carrier("UNICOM", 2, 2);
        TELECOM = new Carrier("TELECOM", 3, 3);
        b = new Carrier[]{UNKNOWN, CMCC, UNICOM, TELECOM};
    }

    private Carrier(int i) {
        this.a = i;
    }

    public final int getValue() {
        return this.a;
    }
}
