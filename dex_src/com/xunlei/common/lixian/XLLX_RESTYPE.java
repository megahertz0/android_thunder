package com.xunlei.common.lixian;

public enum XLLX_RESTYPE {
    Other(0),
    Http(1),
    Ftp(2),
    Emule(4),
    Bt_All(5),
    Bt_File(6);
    private int value;

    private XLLX_RESTYPE(int i) {
        this.value = 0;
        this.value = i;
    }

    public static XLLX_RESTYPE get(int i) {
        XLLX_RESTYPE[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2].value == i) {
                return values[i2];
            }
        }
        return Other;
    }

    public final int toInt() {
        return this.value;
    }
}
