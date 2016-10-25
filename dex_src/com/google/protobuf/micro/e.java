package com.google.protobuf.micro;

import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class e {
    static final int a;
    static final int b;
    static final int c;
    static final int d;

    static {
        a = a(1, XZBDevice.DOWNLOAD_LIST_FAILED);
        b = a(1, XZBDevice.DOWNLOAD_LIST_ALL);
        c = a(XZBDevice.DOWNLOAD_LIST_RECYCLE, 0);
        d = a(XZBDevice.DOWNLOAD_LIST_FAILED, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    static int a(int i) {
        return i & 7;
    }

    static int a(int i, int i2) {
        return (i << 3) | i2;
    }

    public static int b(int i) {
        return i >>> 3;
    }
}
