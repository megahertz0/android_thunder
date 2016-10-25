package com.bumptech.glide.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.widget.AutoScrollHelper;
import android.util.Log;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: AnimatedGifEncoder.java
public final class a {
    public int a;
    private int b;
    private int c;
    private Integer d;
    private int e;
    private int f;
    private boolean g;
    private OutputStream h;
    private Bitmap i;
    private byte[] j;
    private byte[] k;
    private int l;
    private byte[] m;
    private boolean[] n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;

    public a() {
        this.d = null;
        this.f = -1;
        this.a = 0;
        this.g = false;
        this.n = new boolean[256];
        this.o = 7;
        this.p = -1;
        this.q = false;
        this.r = true;
        this.s = false;
        this.t = 10;
    }

    public final boolean a(Bitmap bitmap) {
        if (bitmap == null || !this.g) {
            return false;
        }
        try {
            int width;
            int height;
            if (!this.s) {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (!this.g || this.r) {
                    this.b = width;
                    this.c = height;
                    if (this.b <= 0) {
                        this.b = 320;
                    }
                    if (this.c <= 0) {
                        this.c = 240;
                    }
                    this.s = true;
                }
            }
            this.i = bitmap;
            int width2 = this.i.getWidth();
            int height2 = this.i.getHeight();
            if (!(width2 == this.b && height2 == this.c)) {
                Bitmap createBitmap = Bitmap.createBitmap(this.b, this.c, Config.ARGB_8888);
                new Canvas(createBitmap).drawBitmap(createBitmap, AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, null);
                this.i = createBitmap;
            }
            int[] iArr = new int[(width2 * height2)];
            this.i.getPixels(iArr, 0, width2, 0, 0, width2, height2);
            this.j = new byte[(iArr.length * 3)];
            this.u = false;
            int length = iArr.length;
            int i = 0;
            width = 0;
            for (width2 = 0; width2 < length; width2++) {
                int i2 = iArr[width2];
                if (i2 == 0) {
                    width++;
                }
                height2 = i + 1;
                this.j[i] = (byte) (i2 & 255);
                int i3 = height2 + 1;
                this.j[height2] = (byte) ((i2 >> 8) & 255);
                i = i3 + 1;
                this.j[i3] = (byte) ((i2 >> 16) & 255);
            }
            double d = ((double) (width * 100)) / ((double) length);
            this.u = d > 4.0d;
            if (Log.isLoggable("AnimatedGifEncoder", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                new StringBuilder("got pixels for frame with ").append(d).append("% transparent pixels");
            }
            b();
            if (this.r) {
                b(this.b);
                b(this.c);
                this.h.write(this.o | 240);
                this.h.write(0);
                this.h.write(0);
                c();
                if (this.f >= 0) {
                    this.h.write(R.styleable.AppCompatTheme_actionModeCopyDrawable);
                    this.h.write(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
                    this.h.write(XZBDevice.Success);
                    a("NETSCAPE2.0");
                    this.h.write(XZBDevice.DOWNLOAD_LIST_FAILED);
                    this.h.write(1);
                    b(this.f);
                    this.h.write(0);
                }
            }
            this.h.write(R.styleable.AppCompatTheme_actionModeCopyDrawable);
            this.h.write(249);
            this.h.write(XZBDevice.DOWNLOAD_LIST_ALL);
            if (this.d != null || this.u) {
                width = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                height = 1;
            } else {
                width = 0;
                height = 0;
            }
            if (this.p >= 0) {
                width = this.p & 7;
            }
            this.h.write((((width << 2) | 0) | 0) | height);
            b(this.a);
            this.h.write(this.e);
            this.h.write(0);
            this.h.write(R.styleable.AppCompatTheme_listDividerAlertDialog);
            b(0);
            b(0);
            b(this.b);
            b(this.c);
            if (this.r) {
                this.h.write(0);
            } else {
                this.h.write(this.o | 128);
            }
            if (!this.r) {
                c();
            }
            b bVar = new b(this.b, this.c, this.k, this.l);
            OutputStream outputStream = this.h;
            outputStream.write(bVar.c);
            bVar.d = bVar.a * bVar.b;
            bVar.e = 0;
            bVar.a(bVar.c + 1, outputStream);
            outputStream.write(0);
            this.r = false;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public final boolean a() {
        if (!this.g) {
            return false;
        }
        boolean z;
        this.g = false;
        try {
            this.h.write(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle);
            this.h.flush();
            if (this.q) {
                this.h.close();
            }
            z = true;
        } catch (IOException e) {
            z = false;
        }
        this.e = 0;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.q = false;
        this.r = true;
        return z;
    }

    public final boolean a(OutputStream outputStream) {
        if (outputStream == null) {
            return false;
        }
        boolean z = true;
        this.q = false;
        this.h = outputStream;
        try {
            a("GIF89a");
        } catch (IOException e) {
            z = false;
        }
        this.g = z;
        return z;
    }

    private void b() {
        int length = this.j.length;
        int i = length / 3;
        this.k = new byte[i];
        c cVar = new c(this.j, length, this.t);
        cVar.c();
        cVar.d();
        cVar.b();
        this.m = cVar.a();
        for (length = 0; length < this.m.length; length += 3) {
            byte b = this.m[length];
            this.m[length] = this.m[length + 2];
            this.m[length + 2] = b;
            this.n[length / 3] = false;
        }
        int i2 = 0;
        for (length = 0; length < i; length++) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            i2 = i4 + 1;
            int a = cVar.a(this.j[i2] & 255, this.j[i3] & 255, this.j[i4] & 255);
            this.n[a] = true;
            this.k[length] = (byte) a;
        }
        this.j = null;
        this.l = 8;
        this.o = 7;
        if (this.d != null) {
            this.e = a(this.d.intValue());
        } else if (this.u) {
            this.e = a(0);
        }
    }

    private int a(int i) {
        int i2 = 0;
        if (this.m == null) {
            return -1;
        }
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        Object obj = XLErrorCode.DEVICE_INVALID;
        int length = this.m.length;
        int i3 = 0;
        while (i2 < length) {
            int i4;
            int i5 = i2 + 1;
            i2 = red - (this.m[i2] & 255);
            int i6 = i5 + 1;
            int i7 = green - (this.m[i5] & 255);
            i5 = blue - (this.m[i6] & 255);
            i2 = ((i2 * i2) + (i7 * i7)) + (i5 * i5);
            i7 = i6 / 3;
            if (!this.n[i7] || i2 >= i4) {
                i2 = i4;
                i4 = i3;
            } else {
                i4 = i7;
            }
            i3 = i4;
            i4 = i2;
            i2 = i6 + 1;
        }
        return i3;
    }

    private void c() throws IOException {
        this.h.write(this.m, 0, this.m.length);
        int length = 768 - this.m.length;
        for (int i = 0; i < length; i++) {
            this.h.write(0);
        }
    }

    private void b(int i) throws IOException {
        this.h.write(i & 255);
        this.h.write((i >> 8) & 255);
    }

    private void a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.h.write((byte) str.charAt(i));
        }
    }
}
