package com.google.zxing.a.c;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

// compiled from: HighLevelEncoder.java
public final class d {
    static final String[] a;
    static final int[][] b;
    static final int[][] c;
    private static final int[][] e;
    final byte[] d;

    static {
        int i;
        a = new String[]{"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
        b = new int[][]{new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{5, 256});
        e = iArr;
        iArr[0][32] = 1;
        for (i = R.styleable.AppCompatTheme_textAppearanceSearchResultTitle; i <= 90; i++) {
            e[0][i] = (i - 65) + 2;
        }
        e[1][32] = 1;
        for (i = R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle; i <= 122; i++) {
            e[1][i] = (i - 97) + 2;
        }
        e[2][32] = 1;
        for (i = R.styleable.AppCompatTheme_homeAsUpIndicator; i <= 57; i++) {
            e[2][i] = (i - 48) + 2;
        }
        e[2][44] = 12;
        e[2][46] = 13;
        int[] iArr2 = new int[]{0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (i = 0; i < 28; i++) {
            e[3][iArr2[i]] = i;
        }
        iArr2 = new int[]{0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (i = 0; i < 31; i++) {
            if (iArr2[i] > 0) {
                e[4][iArr2[i]] = i;
            }
        }
        iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{6, 6});
        c = iArr;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Arrays.fill(iArr[i2], -1);
        }
        c[0][4] = 0;
        c[1][4] = 0;
        c[1][0] = 28;
        c[3][4] = 0;
        c[2][4] = 0;
        c[2][0] = 15;
    }

    public d(byte[] bArr) {
        this.d = bArr;
    }

    final void a(g gVar, int i, Collection<g> collection) {
        Object obj;
        char c = (char) (this.d[i] & 255);
        if (e[gVar.b][c] > 0) {
            obj = 1;
        } else {
            obj = null;
        }
        g gVar2 = null;
        int i2 = 0;
        while (i2 <= 4) {
            int i3 = e[i2][c];
            if (i3 > 0) {
                if (gVar2 == null) {
                    gVar2 = gVar.b(i);
                }
                if (obj == null || i2 == gVar.b || i2 == 2) {
                    collection.add(gVar2.a(i2, i3));
                }
                if (obj == null && c[gVar.b][i2] >= 0) {
                    collection.add(gVar2.b(i2, i3));
                }
            }
            i2++;
        }
        if (gVar.c > 0 || e[gVar.b][c] == 0) {
            collection.add(gVar.a(i));
        }
    }

    static Collection<g> a(Iterable<g> iterable, int i, int i2) {
        Iterable linkedList = new LinkedList();
        for (g gVar : iterable) {
            g b = gVar.b(i);
            linkedList.add(b.a(XZBDevice.DOWNLOAD_LIST_ALL, i2));
            if (gVar.b != 4) {
                linkedList.add(b.b(XZBDevice.DOWNLOAD_LIST_ALL, i2));
            }
            if (i2 == 3 || i2 == 4) {
                linkedList.add(b.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, 16 - i2).a(XZBDevice.DOWNLOAD_LIST_RECYCLE, 1));
            }
            if (gVar.c > 0) {
                linkedList.add(gVar.a(i).a(i + 1));
            }
        }
        return a(linkedList);
    }

    static Collection<g> a(Iterable<g> iterable) {
        Collection linkedList = new LinkedList();
        for (g gVar : iterable) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                g gVar2 = (g) it.next();
                if (gVar2.a(gVar)) {
                    Object obj = null;
                    break;
                } else if (gVar.a(gVar2)) {
                    it.remove();
                }
            }
            int i = 1;
            if (obj != null) {
                linkedList.add(gVar);
            }
        }
        return linkedList;
    }
}
