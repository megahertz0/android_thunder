package com.google.zxing.pdf417.a;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: DetectionResultRowIndicatorColumn.java
final class i extends h {
    final boolean c;

    i(c cVar, boolean z) {
        super(cVar);
        this.c = z;
    }

    final a a() {
        d[] dVarArr = this.b;
        b bVar = new b();
        b bVar2 = new b();
        b bVar3 = new b();
        b bVar4 = new b();
        int length = dVarArr.length;
        for (int i = 0; i < length; i++) {
            d dVar = dVarArr[i];
            if (dVar != null) {
                dVar.b();
                int i2 = dVar.d % 30;
                int i3 = dVar.e;
                if (!this.c) {
                    i3 += 2;
                }
                switch (i3 % 3) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        bVar2.a((i2 * 3) + 1);
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        bVar4.a(i2 / 3);
                        bVar3.a(i2 % 3);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        bVar.a(i2 + 1);
                        break;
                    default:
                        break;
                }
            }
        }
        if (bVar.a().length == 0 || bVar2.a().length == 0 || bVar3.a().length == 0 || bVar4.a().length == 0 || bVar.a()[0] <= 0 || bVar2.a()[0] + bVar3.a()[0] < 3 || bVar2.a()[0] + bVar3.a()[0] > 90) {
            return null;
        }
        a aVar = new a(bVar.a()[0], bVar2.a()[0], bVar3.a()[0], bVar4.a()[0]);
        a(dVarArr, aVar);
        return aVar;
    }

    final void a(d[] dVarArr, a aVar) {
        for (int i = 0; i < dVarArr.length; i++) {
            d dVar = dVarArr[i];
            if (dVarArr[i] != null) {
                int i2 = dVar.d % 30;
                int i3 = dVar.e;
                if (i3 > aVar.e) {
                    dVarArr[i] = null;
                } else {
                    if (!this.c) {
                        i3 += 2;
                    }
                    switch (i3 % 3) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            if ((i2 * 3) + 1 != aVar.c) {
                                dVarArr[i] = null;
                            }
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            if (i2 / 3 != aVar.b || i2 % 3 != aVar.d) {
                                dVarArr[i] = null;
                            }
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (i2 + 1 != aVar.a) {
                                dVarArr[i] = null;
                            }
                        default:
                            break;
                    }
                }
            }
        }
    }

    public final String toString() {
        return new StringBuilder("IsLeft: ").append(this.c).append('\n').append(super.toString()).toString();
    }
}
