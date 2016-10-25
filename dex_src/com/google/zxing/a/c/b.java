package com.google.zxing.a.c;

import com.google.zxing.common.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BinaryShiftToken.java
final class b extends h {
    private final short c;
    private final short d;

    b(h hVar, int i, int i2) {
        super(hVar);
        this.c = (short) i;
        this.d = (short) i2;
    }

    public final void a(a aVar, byte[] bArr) {
        int i = 0;
        while (r0 < this.d) {
            if (r0 == (short) 0 || (r0 == (short) 31 && this.d <= (short) 62)) {
                aVar.b(R.styleable.AppCompatTheme_actionModeCloseDrawable, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                if (this.d > (short) 62) {
                    aVar.b(this.d - 31, R.styleable.Toolbar_titleMarginBottom);
                } else if (r0 == (short) 0) {
                    aVar.b(Math.min(this.d, R.styleable.AppCompatTheme_actionModeCloseDrawable), XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                } else {
                    aVar.b(this.d - 31, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                }
            }
            aVar.b(bArr[this.c + r0], XZBDevice.Wait);
            i = r0 + 1;
        }
    }

    public final String toString() {
        return new StringBuilder("<").append(this.c).append("::").append((this.c + this.d) - 1).append('>').toString();
    }
}
