package com.google.zxing.datamatrix.encoder;

import com.google.zxing.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: SymbolInfo.java
public class k {
    static final k[] a;
    private static k[] g;
    final int b;
    final int c;
    public final int d;
    public final int e;
    final int f;
    private final boolean h;
    private final int i;
    private final int j;

    static {
        k[] kVarArr = new k[]{new k(false, 3, 5, 8, 8, 1), new k(false, 5, 7, 10, 10, 1), new k(true, 5, 7, 16, 6, 1), new k(false, 8, 10, 12, 12, 1), new k(true, 10, 11, 14, 6, 2), new k(false, 12, 12, 14, 14, 1), new k(true, 16, 14, 24, 10, 1), new k(false, 18, 14, 16, 16, 1), new k(false, 22, 18, 18, 18, 1), new k(true, 22, 18, 16, 10, 2), new k(false, 30, 20, 20, 20, 1), new k(true, 32, 24, 16, 14, 2), new k(false, 36, 24, 22, 22, 1), new k(false, 44, 28, 24, 24, 1), new k(true, 49, 28, 22, 14, 2), new k(false, 62, 36, 14, 14, 4), new k(false, 86, 42, 16, 16, 4), new k(false, 114, 48, 18, 18, 4), new k(false, 144, 56, 20, 20, 4), new k(false, 174, 68, 22, 22, 4), new k(false, 204, 84, 24, 24, 4, 102, 42), new k(false, 280, 112, 14, 14, 16, 140, 56), new k(false, 368, 144, 16, 16, 16, 92, 36), new k(false, 456, 192, 18, 18, 16, 114, 48), new k(false, 576, 224, 20, 20, 16, 144, 56), new k(false, 696, 272, 22, 22, 16, 174, 68), new k(false, 816, 336, 24, 24, 16, 136, 56), new k(false, 1050, 408, 18, 18, 36, 175, 68), new k(false, 1304, 496, 20, 20, 36, 163, 62), new d()};
        a = kVarArr;
        g = kVarArr;
    }

    private k(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    k(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.h = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.i = i5;
        this.j = i6;
        this.f = i7;
    }

    public static k a(int i, SymbolShapeHint symbolShapeHint, d dVar, d dVar2) {
        for (k kVar : g) {
            if (symbolShapeHint != SymbolShapeHint.FORCE_SQUARE || !kVar.h) {
                if (symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || kVar.h) {
                    if (dVar == null || (kVar.d() >= dVar.a && kVar.e() >= dVar.b)) {
                        if ((dVar2 == null || (kVar.d() <= dVar2.a && kVar.e() <= dVar2.b)) && i <= kVar.b) {
                            return kVar;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException(new StringBuilder("Can't find a symbol arrangement that matches the message. Data codewords: ").append(i).toString());
    }

    private int f() {
        switch (this.i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return 1;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case R.styleable.Toolbar_titleMarginBottom:
                return XZBDevice.DOWNLOAD_LIST_ALL;
            case R.styleable.AppCompatTheme_actionModeShareDrawable:
                return R.styleable.Toolbar_contentInsetEnd;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    private int g() {
        switch (this.i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 1;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case R.styleable.Toolbar_titleMarginBottom:
                return XZBDevice.DOWNLOAD_LIST_ALL;
            case R.styleable.AppCompatTheme_actionModeShareDrawable:
                return R.styleable.Toolbar_contentInsetEnd;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int b() {
        return f() * this.d;
    }

    public final int c() {
        return g() * this.e;
    }

    public final int d() {
        return b() + (f() * 2);
    }

    public final int e() {
        return c() + (g() * 2);
    }

    public int a() {
        return this.b / this.j;
    }

    public int a(int i) {
        return this.j;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.h ? "Rectangular Symbol:" : "Square Symbol:");
        stringBuilder.append(" data region ").append(this.d).append('x').append(this.e);
        stringBuilder.append(", symbol size ").append(d()).append('x').append(e());
        stringBuilder.append(", symbol data size ").append(b()).append('x').append(c());
        stringBuilder.append(", codewords ").append(this.b).append('+').append(this.c);
        return stringBuilder.toString();
    }
}
