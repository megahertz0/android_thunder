package com.tencent.wxop.stat.common;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class g {
    static final /* synthetic */ boolean a;

    static {
        a = !g.class.desiredAssertionStatus();
    }

    private g() {
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        i iVar = new i(i3, new byte[((i2 * 3) / 4)]);
        if (!iVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (iVar.b == iVar.a.length) {
            return iVar.a;
        } else {
            Object obj = new Object[iVar.b];
            System.arraycopy(iVar.a, 0, obj, 0, iVar.b);
            return obj;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        return b(bArr, 0, bArr.length, i);
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) {
        j jVar = new j(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!jVar.d) {
            switch (i2 % 3) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    i4 += 2;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (jVar.e && i2 > 0) {
            i4 += (jVar.f ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 1) * (((i2 - 1) / 57) + 1);
        }
        jVar.a = new byte[i4];
        jVar.a(bArr, i, i2, true);
        if (a || jVar.b == i4) {
            return jVar.a;
        }
        throw new AssertionError();
    }
}
