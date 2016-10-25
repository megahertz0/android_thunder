package com.squareup.wire;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public enum FieldEncoding {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);
    final int a;

    static {
        VARINT = new FieldEncoding("VARINT", 0, 0);
        FIXED64 = new FieldEncoding("FIXED64", 1, 1);
        LENGTH_DELIMITED = new FieldEncoding("LENGTH_DELIMITED", 2, 2);
        FIXED32 = new FieldEncoding("FIXED32", 3, 5);
        b = new FieldEncoding[]{VARINT, FIXED64, LENGTH_DELIMITED, FIXED32};
    }

    private FieldEncoding(int i) {
        this.a = i;
    }

    public final b<?> rawProtoAdapter() {
        switch (a.a[ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return b.i;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return b.f;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return b.k;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return b.p;
            default:
                throw new AssertionError();
        }
    }
}
