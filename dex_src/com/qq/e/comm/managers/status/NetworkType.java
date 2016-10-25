package com.qq.e.comm.managers.status;

public enum NetworkType {
    UNKNOWN(0, 1),
    WIFI(1, 2),
    NET_2G(2, 4),
    NET_3G(3, 8),
    NET_4G(4, 16);
    private int a;
    private int b;

    static {
        UNKNOWN = new NetworkType("UNKNOWN", 0, 0, 1);
        WIFI = new NetworkType("WIFI", 1, 1, 2);
        NET_2G = new NetworkType("NET_2G", 2, 2, 4);
        NET_3G = new NetworkType("NET_3G", 3, 3, 8);
        NET_4G = new NetworkType("NET_4G", 4, 4, 16);
        c = new NetworkType[]{UNKNOWN, WIFI, NET_2G, NET_3G, NET_4G};
    }

    private NetworkType(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final int getConnValue() {
        return this.a;
    }

    public final int getPermValue() {
        return this.b;
    }
}
