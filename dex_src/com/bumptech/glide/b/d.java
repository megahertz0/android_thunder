package com.bumptech.glide.b;

import android.util.Log;
import com.umeng.a;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.android.spdy.SpdyAgent;

// compiled from: GifHeaderParser.java
public final class d {
    public ByteBuffer a;
    public c b;
    private final byte[] c;
    private int d;

    public d() {
        this.c = new byte[256];
        this.d = 0;
    }

    public final d a(byte[] bArr) {
        this.a = null;
        Arrays.fill(this.c, (byte) 0);
        this.b = new c();
        this.d = 0;
        if (bArr != null) {
            this.a = ByteBuffer.wrap(bArr);
            this.a.rewind();
            this.a.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.a = null;
            this.b.b = 2;
        }
        return this;
    }

    public final c a() {
        if (this.a == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (h()) {
            return this.b;
        } else {
            d();
            if (!h()) {
                b();
                if (this.b.c < 0) {
                    this.b.b = 1;
                }
            }
            return this.b;
        }
    }

    private void b() {
        Object obj = null;
        int i;
        while (i == 0 && !h()) {
            int g;
            switch (g()) {
                case R.styleable.AppCompatTheme_actionModeCopyDrawable:
                    switch (g()) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            e();
                            break;
                        case 249:
                            boolean z;
                            this.b.d = new b();
                            g();
                            g = g();
                            this.b.d.g = (g & 28) >> 2;
                            if (this.b.d.g == 0) {
                                this.b.d.g = 1;
                            }
                            b bVar = this.b.d;
                            if ((g & 1) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            bVar.f = z;
                            g = this.a.getShort();
                            if (g < 3) {
                                g = XZBDevice.Stop;
                            }
                            this.b.d.i = g * 10;
                            this.b.d.h = g();
                            g();
                            break;
                        case 254:
                            e();
                            break;
                        case VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX:
                            f();
                            String str = a.d;
                            for (g = 0; g < 11; g++) {
                                str = str + ((char) this.c[g]);
                            }
                            if (str.equals("NETSCAPE2.0")) {
                                c();
                            } else {
                                e();
                            }
                            break;
                        default:
                            e();
                            break;
                    }
                    break;
                case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    boolean z2;
                    if (this.b.d == null) {
                        this.b.d = new b();
                    }
                    this.b.d.a = this.a.getShort();
                    this.b.d.b = this.a.getShort();
                    this.b.d.c = this.a.getShort();
                    this.b.d.d = this.a.getShort();
                    int g2 = g();
                    if ((g2 & 128) != 0) {
                        g = 1;
                    } else {
                        Object obj2 = null;
                    }
                    int pow = (int) Math.pow(2.0d, (double) ((g2 & 7) + 1));
                    b bVar2 = this.b.d;
                    if ((g2 & 64) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    bVar2.e = z2;
                    if (g != 0) {
                        this.b.d.k = a(pow);
                    } else {
                        this.b.d.k = null;
                    }
                    this.b.d.j = this.a.position();
                    g();
                    e();
                    if (!h()) {
                        c cVar = this.b;
                        cVar.c++;
                        this.b.e.add(this.b.d);
                    }
                case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                    i = 1;
                    break;
                default:
                    this.b.b = 1;
                    break;
            }
        }
    }

    private void c() {
        do {
            f();
            if (this.c[0] == (byte) 1) {
                this.b.m = (this.c[1] & 255) | ((this.c[2] & 255) << 8);
            }
            if (this.d <= 0) {
                return;
            }
        } while (!h());
    }

    private void d() {
        int i;
        boolean z = true;
        String str = a.d;
        for (i = 0; i < 6; i++) {
            str = str + ((char) g());
        }
        if (str.startsWith("GIF")) {
            this.b.f = this.a.getShort();
            this.b.g = this.a.getShort();
            i = g();
            c cVar = this.b;
            if ((i & 128) == 0) {
                z = false;
            }
            cVar.h = z;
            this.b.i = 2 << (i & 7);
            this.b.j = g();
            this.b.k = g();
            if (this.b.h && !h()) {
                this.b.a = a(this.b.i);
                this.b.l = this.b.a[this.b.j];
                return;
            }
            return;
        }
        this.b.b = 1;
    }

    private int[] a(int i) {
        int i2 = 0;
        int[] iArr = null;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.a.get(bArr);
            iArr = new int[256];
            int i3 = 0;
            while (i3 < i) {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = i4 + 1;
                int i7 = bArr[i4] & 255;
                i2 = i6 + 1;
                i4 = i3 + 1;
                iArr[i3] = (((i5 << 16) | -16777216) | (i7 << 8)) | (bArr[i6] & 255);
                i3 = i4;
            }
        } catch (BufferUnderflowException e) {
            this.b.b = 1;
        }
        return iArr;
    }

    private void e() {
        int g;
        do {
            g = g();
            this.a.position(this.a.position() + g);
        } while (g > 0);
    }

    private int f() {
        int i = 0;
        this.d = g();
        if (this.d > 0) {
            int i2 = 0;
            while (i < this.d) {
                try {
                    i2 = this.d - i;
                    this.a.get(this.c, i, i2);
                    i += i2;
                } catch (Exception e) {
                    if (Log.isLoggable("GifHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                        new StringBuilder("Error Reading Block n: ").append(i).append(" count: ").append(i2).append(" blockSize: ").append(this.d);
                    }
                    this.b.b = 1;
                }
            }
        }
        return i;
    }

    private int g() {
        int i = 0;
        try {
            i = this.a.get() & 255;
            return i;
        } catch (Exception e) {
            this.b.b = 1;
            return i;
        }
    }

    private boolean h() {
        return this.b.b != 0;
    }
}
