package com.google.zxing.datamatrix.encoder;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;

// compiled from: DefaultPlacement.java
public final class e {
    public final int a;
    public final byte[] b;
    private final CharSequence c;
    private final int d;

    public e(CharSequence charSequence, int i, int i2) {
        this.c = charSequence;
        this.a = i;
        this.d = i2;
        this.b = new byte[(i * i2)];
        Arrays.fill(this.b, (byte) -1);
    }

    private void a(int i, int i2, boolean z) {
        this.b[(this.a * i2) + i] = z ? (byte) 1 : (byte) 0;
    }

    private boolean a(int i, int i2) {
        return this.b[(this.a * i2) + i] >= null;
    }

    public final void a() {
        int i = 0;
        int i2 = 4;
        int i3 = 0;
        while (true) {
            int i4;
            if (i2 == this.d && r0 == 0) {
                i4 = i3 + 1;
                a(this.d - 1, 0, i3, 1);
                a(this.d - 1, 1, i3, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                a(this.d - 1, XZBDevice.DOWNLOAD_LIST_RECYCLE, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
                a(0, this.a - 2, i3, XZBDevice.DOWNLOAD_LIST_ALL);
                a(0, this.a - 1, i3, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                a(1, this.a - 1, i3, R.styleable.Toolbar_contentInsetEnd);
                a(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.a - 1, i3, R.styleable.Toolbar_contentInsetLeft);
                a(XZBDevice.DOWNLOAD_LIST_FAILED, this.a - 1, i3, XZBDevice.Wait);
                i3 = i4;
            }
            if (i2 == this.d - 2 && r0 == 0 && this.a % 4 != 0) {
                i4 = i3 + 1;
                a(this.d - 3, 0, i3, 1);
                a(this.d - 2, 0, i3, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                a(this.d - 1, 0, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
                a(0, this.a - 4, i3, XZBDevice.DOWNLOAD_LIST_ALL);
                a(0, this.a - 3, i3, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                a(0, this.a - 2, i3, R.styleable.Toolbar_contentInsetEnd);
                a(0, this.a - 1, i3, R.styleable.Toolbar_contentInsetLeft);
                a(1, this.a - 1, i3, XZBDevice.Wait);
                i3 = i4;
            }
            if (i2 == this.d - 2 && r0 == 0 && this.a % 8 == 4) {
                i4 = i3 + 1;
                a(this.d - 3, 0, i3, 1);
                a(this.d - 2, 0, i3, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                a(this.d - 1, 0, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
                a(0, this.a - 2, i3, XZBDevice.DOWNLOAD_LIST_ALL);
                a(0, this.a - 1, i3, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                a(1, this.a - 1, i3, R.styleable.Toolbar_contentInsetEnd);
                a(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.a - 1, i3, R.styleable.Toolbar_contentInsetLeft);
                a(XZBDevice.DOWNLOAD_LIST_FAILED, this.a - 1, i3, XZBDevice.Wait);
                i3 = i4;
            }
            if (i2 == this.d + 4 && r0 == 2 && this.a % 8 == 0) {
                i4 = i3 + 1;
                a(this.d - 1, 0, i3, 1);
                a(this.d - 1, this.a - 1, i3, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                a(0, this.a - 3, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
                a(0, this.a - 2, i3, XZBDevice.DOWNLOAD_LIST_ALL);
                a(0, this.a - 1, i3, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                a(1, this.a - 3, i3, R.styleable.Toolbar_contentInsetEnd);
                a(1, this.a - 2, i3, R.styleable.Toolbar_contentInsetLeft);
                a(1, this.a - 1, i3, XZBDevice.Wait);
                i3 = i4;
            }
            do {
                if (i2 < this.d && i >= 0 && !a(i, i2)) {
                    i4 = i3 + 1;
                    a(i2, i, i3);
                    i3 = i4;
                }
                i2 -= 2;
                i += 2;
                if (i2 < 0) {
                    break;
                }
            } while (i < this.a);
            i4 = i2 + 1;
            i2 = i + 3;
            i = i3;
            while (true) {
                if (i4 < 0 || i2 >= this.a || a(i2, i4)) {
                    i3 = i;
                } else {
                    i3 = i + 1;
                    a(i4, i2, i);
                }
                i4 += 2;
                i = i2 - 2;
                if (i4 >= this.d || i < 0) {
                    break;
                }
                i2 = i;
                i = i3;
            }
            i2 = i4 + 3;
            i++;
            if (i2 >= this.d && i >= this.a) {
                break;
            }
        }
        if (!a(this.a - 1, this.d - 1)) {
            a(this.a - 1, this.d - 1, true);
            a(this.a - 2, this.d - 2, true);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        boolean z = true;
        if (i < 0) {
            i5 = i + this.d;
            i6 = (4 - ((this.d + 4) % 8)) + i2;
        } else {
            i6 = i2;
            i5 = i;
        }
        if (i6 < 0) {
            i6 += this.a;
            i5 += 4 - ((this.a + 4) % 8);
        }
        if ((this.c.charAt(i3) & (1 << (8 - i4))) == 0) {
            z = false;
        }
        a(i6, i5, z);
    }

    private void a(int i, int i2, int i3) {
        a(i - 2, i2 - 2, i3, 1);
        a(i - 2, i2 - 1, i3, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        a(i - 1, i2 - 2, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
        a(i - 1, i2 - 1, i3, XZBDevice.DOWNLOAD_LIST_ALL);
        a(i - 1, i2, i3, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        a(i, i2 - 2, i3, R.styleable.Toolbar_contentInsetEnd);
        a(i, i2 - 1, i3, R.styleable.Toolbar_contentInsetLeft);
        a(i, i2, i3, XZBDevice.Wait);
    }
}
