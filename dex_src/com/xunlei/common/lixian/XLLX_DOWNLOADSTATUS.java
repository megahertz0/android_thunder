package com.xunlei.common.lixian;

public enum XLLX_DOWNLOADSTATUS {
    waitting(0),
    downloading(1),
    finished(2),
    failed(3),
    paused(4),
    unknown(10000);
    private int value;

    private XLLX_DOWNLOADSTATUS(int i) {
        this.value = 0;
        this.value = i;
    }

    public static XLLX_DOWNLOADSTATUS get(int i) {
        XLLX_DOWNLOADSTATUS[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2].value == i) {
                return values[i2];
            }
        }
        return unknown;
    }

    public final int toInt() {
        return this.value;
    }
}
