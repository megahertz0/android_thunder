package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: DataMatrixSymbolInfo144.java
final class d extends k {
    d() {
        super(false, 1558, 620, 22, 22, 36, -1, 62);
    }

    public final int a() {
        return XZBDevice.Stop;
    }

    public final int a(int i) {
        return i <= 8 ? 156 : 155;
    }
}
