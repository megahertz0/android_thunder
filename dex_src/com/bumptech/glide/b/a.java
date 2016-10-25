package com.bumptech.glide.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

// compiled from: GifDecoder.java
public class a {
    private static final String h;
    private static final Config i;
    public byte[] a;
    public int[] b;
    public int c;
    public byte[] d;
    public c e;
    public a f;
    public Bitmap g;
    private int[] j;
    private ByteBuffer k;
    private final byte[] l;
    private short[] m;
    private byte[] n;
    private byte[] o;
    private boolean p;
    private int q;

    // compiled from: GifDecoder.java
    public static interface a {
        Bitmap a(int i, int i2, Config config);

        void a(Bitmap bitmap);
    }

    static {
        h = a.class.getSimpleName();
        i = Config.ARGB_8888;
    }

    public a(a aVar) {
        this.l = new byte[256];
        this.f = aVar;
        this.e = new c();
    }

    public final void a() {
        this.c = (this.c + 1) % this.e.c;
    }

    public final int a(int i) {
        return (i < 0 || i >= this.e.c) ? -1 : ((b) this.e.e.get(i)).i;
    }

    public final synchronized Bitmap b() {
        Bitmap bitmap;
        Object obj = null;
        synchronized (this) {
            if (this.e.c <= 0 || this.c < 0) {
                if (Log.isLoggable(h, XZBDevice.DOWNLOAD_LIST_FAILED)) {
                    new StringBuilder("unable to decode frame, frameCount=").append(this.e.c).append(" framePointer=").append(this.c);
                }
                this.q = 1;
            }
            if (this.q == 1 || this.q == 2) {
                if (Log.isLoggable(h, XZBDevice.DOWNLOAD_LIST_FAILED)) {
                    new StringBuilder("Unable to decode frame, status=").append(this.q);
                }
                bitmap = null;
            } else {
                b bVar;
                int i;
                this.q = 0;
                b bVar2 = (b) this.e.e.get(this.c);
                int i2 = this.c - 1;
                if (i2 >= 0) {
                    bVar = (b) this.e.e.get(i2);
                } else {
                    bVar = null;
                }
                if (bVar2.k == null) {
                    this.j = this.e.a;
                } else {
                    this.j = bVar2.k;
                    if (this.e.j == bVar2.h) {
                        this.e.l = 0;
                    }
                }
                if (bVar2.f) {
                    i2 = this.j[bVar2.h];
                    this.j[bVar2.h] = 0;
                    i = i2;
                }
                if (this.j == null) {
                    this.q = 1;
                    bitmap = null;
                } else {
                    Bitmap a = a(bVar2, bVar);
                    if (bVar2.f) {
                        this.j[bVar2.h] = i;
                    }
                    bitmap = a;
                }
            }
        }
        return bitmap;
    }

    public final void a(c cVar, byte[] bArr) {
        this.e = cVar;
        this.d = bArr;
        this.q = 0;
        this.c = -1;
        this.k = ByteBuffer.wrap(bArr);
        this.k.rewind();
        this.k.order(ByteOrder.LITTLE_ENDIAN);
        this.p = false;
        for (b bVar : cVar.e) {
            if (bVar.g == 3) {
                this.p = true;
                break;
            }
        }
        this.a = new byte[(cVar.f * cVar.g)];
        this.b = new int[(cVar.f * cVar.g)];
    }

    private Bitmap a(b bVar, b bVar2) {
        int i;
        int i2;
        int i3;
        int i4 = this.e.f;
        int i5 = this.e.g;
        int[] iArr = this.b;
        if (bVar2 != null && bVar2.g > 0) {
            if (bVar2.g == 2) {
                i = 0;
                if (!bVar.f) {
                    i = this.e.l;
                }
                Arrays.fill(iArr, i);
            } else if (bVar2.g == 3 && this.g != null) {
                this.g.getPixels(iArr, 0, i4, 0, 0, i4, i5);
            }
        }
        if (bVar != null) {
            this.k.position(bVar.j);
        }
        if (bVar == null) {
            i = this.e.f * this.e.g;
        } else {
            i = bVar.c * bVar.d;
        }
        if (this.a == null || this.a.length < i) {
            this.a = new byte[i];
        }
        if (this.m == null) {
            this.m = new short[4096];
        }
        if (this.n == null) {
            this.n = new byte[4096];
        }
        if (this.o == null) {
            this.o = new byte[4097];
        }
        int c = c();
        int i6 = 1 << c;
        int i7 = i6 + 1;
        int i8 = i6 + 2;
        int i9 = -1;
        int i10 = c + 1;
        int i11 = (1 << i10) - 1;
        for (i2 = 0; i2 < i6; i2++) {
            this.m[i2] = (short) 0;
            this.n[i2] = (byte) i2;
        }
        i2 = 0;
        Object obj = null;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = i10;
        int i16 = i11;
        int i17 = i8;
        i10 = 0;
        i11 = 0;
        i8 = 0;
        while (i3 < i) {
            if (i11 == 0) {
                i11 = d();
                if (i11 <= 0) {
                    this.q = 3;
                    break;
                }
                i10 = 0;
            }
            i2 += (this.l[i10] & 255) << i14;
            int i18 = i10 + 1;
            int i19 = i11 - 1;
            i10 = i15;
            i11 = i16;
            i15 = i13;
            int i20 = i14 + 8;
            i14 = i2;
            i2 = i8;
            i8 = i17;
            i17 = i20;
            while (i17 >= i10) {
                i13 = i14 & i11;
                i16 = i14 >> i10;
                i17 -= i10;
                if (i13 != i6) {
                    if (i13 <= i8) {
                        if (i13 == i7) {
                            i13 = i15;
                            i14 = i17;
                            i15 = i10;
                            i17 = i8;
                            i10 = i18;
                            i8 = i2;
                            i2 = i16;
                            i16 = i11;
                            i11 = i19;
                            break;
                        } else if (i9 == -1) {
                            i14 = i12 + 1;
                            this.o[i12] = this.n[i13];
                            i12 = i14;
                            i15 = i13;
                            i9 = i13;
                            i14 = i16;
                        } else {
                            if (i13 >= i8) {
                                i14 = i12 + 1;
                                this.o[i12] = (byte) i15;
                                i12 = i14;
                                i15 = i9;
                            } else {
                                i15 = i13;
                            }
                            while (i15 >= i6) {
                                i14 = i12 + 1;
                                this.o[i12] = this.n[i15];
                                short s = this.m[i15];
                                i12 = i14;
                            }
                            i15 = this.n[i15] & 255;
                            i14 = i12 + 1;
                            this.o[i12] = (byte) i15;
                            if (i8 < 4096) {
                                this.m[i8] = (short) i9;
                                this.n[i8] = (byte) i15;
                                i8++;
                                if ((i8 & i11) == 0 && i8 < 4096) {
                                    i10++;
                                    i11 += i8;
                                }
                            }
                            i12 = i3;
                            while (i14 > 0) {
                                i3 = i14 - 1;
                                i14 = i2 + 1;
                                this.a[i2] = this.o[i3];
                                i12++;
                                i2 = i14;
                                i14 = i3;
                            }
                            i3 = i12;
                            i9 = i13;
                            i12 = i14;
                            i14 = i16;
                        }
                    } else {
                        this.q = 3;
                        i13 = i15;
                        i14 = i17;
                        i15 = i10;
                        i17 = i8;
                        i10 = i18;
                        i8 = i2;
                        i2 = i16;
                        i16 = i11;
                        i11 = i19;
                        break;
                    }
                }
                i10 = c + 1;
                i11 = (1 << i10) - 1;
                i8 = i6 + 2;
                i14 = i16;
                i9 = -1;
            }
            i13 = i15;
            i16 = i11;
            i11 = i19;
            i15 = i10;
            i10 = i18;
            i20 = i17;
            i17 = i8;
            i8 = i2;
            i2 = i14;
            i14 = i20;
        }
        for (i10 = i8; i10 < i; i10++) {
            this.a[i10] = (byte) 0;
        }
        i8 = 1;
        i11 = XZBDevice.Wait;
        i10 = 0;
        for (i = 0; i < bVar.d; i++) {
            if (bVar.e) {
                if (i10 >= bVar.d) {
                    i8++;
                    switch (i8) {
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            i10 = XZBDevice.DOWNLOAD_LIST_ALL;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            i10 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                            i11 = XZBDevice.DOWNLOAD_LIST_ALL;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            i10 = 1;
                            i11 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                            break;
                    }
                }
                i20 = i10;
                i10 += i11;
                i2 = i20;
            } else {
                i2 = i;
            }
            i2 += bVar.b;
            if (i2 < this.e.g) {
                i3 = i2 * this.e.f;
                i14 = i3 + bVar.a;
                i2 = bVar.c + i14;
                if (this.e.f + i3 < i2) {
                    i2 = this.e.f + i3;
                }
                i3 = bVar.c * i;
                while (i14 < i2) {
                    i12 = i3 + 1;
                    i3 = this.j[this.a[i3] & 255];
                    if (i3 != 0) {
                        iArr[i14] = i3;
                    }
                    i14++;
                    i3 = i12;
                }
            }
        }
        if (this.p) {
            if (bVar.g == 0 || bVar.g == 1) {
                if (this.g == null) {
                    this.g = e();
                }
                this.g.setPixels(iArr, 0, i4, 0, 0, i4, i5);
            }
        }
        Bitmap e = e();
        e.setPixels(iArr, 0, i4, 0, 0, i4, i5);
        return e;
    }

    private int c() {
        int i = 0;
        try {
            i = this.k.get() & 255;
            return i;
        } catch (Exception e) {
            this.q = 1;
            return i;
        }
    }

    private int d() {
        int c = c();
        int i = 0;
        if (c > 0) {
            while (i < c) {
                int i2 = c - i;
                try {
                    this.k.get(this.l, i, i2);
                    i += i2;
                } catch (Exception e) {
                    this.q = 1;
                }
            }
        }
        return i;
    }

    private Bitmap e() {
        Bitmap a = this.f.a(this.e.f, this.e.g, i);
        if (a == null) {
            a = Bitmap.createBitmap(this.e.f, this.e.g, i);
        }
        if (VERSION.SDK_INT >= 12) {
            a.setHasAlpha(true);
        }
        return a;
    }
}
