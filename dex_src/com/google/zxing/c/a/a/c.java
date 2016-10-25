package com.google.zxing.c.a.a;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.c.a.a;
import com.google.zxing.c.a.a.a.b;
import com.google.zxing.c.a.a.a.d;
import com.google.zxing.c.a.a.a.g;
import com.google.zxing.c.a.a.a.k;
import com.google.zxing.c.a.a.a.s;
import com.google.zxing.c.a.f;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import com.tencent.connect.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// compiled from: RSSExpandedReader.java
public final class c extends a {
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[][] j;
    private static final int[][] k;
    private static final int[][] l;
    private final List<a> m;
    private final List<b> n;
    private final int[] o;
    private boolean p;

    public c() {
        this.m = new ArrayList(11);
        this.n = new ArrayList();
        this.o = new int[2];
    }

    static {
        g = new int[]{7, 5, 4, 3, 1};
        h = new int[]{4, 20, 52, 104, 204};
        i = new int[]{0, 348, 1388, 2948, 3988};
        j = new int[][]{new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
        k = new int[][]{new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
        l = new int[][]{new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    }

    public final n a(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws j, e {
        this.m.clear();
        this.p = false;
        try {
            return a(a(i, aVar));
        } catch (j e) {
            this.m.clear();
            this.p = true;
            return a(a(i, aVar));
        }
    }

    public final void a() {
        this.m.clear();
        this.n.clear();
    }

    private List<a> a(int i, com.google.zxing.common.a aVar) throws j {
        while (true) {
            try {
                this.m.add(a(aVar, this.m, i));
            } catch (j e) {
                if (this.m.isEmpty()) {
                    throw e;
                } else if (b()) {
                    return this.m;
                } else {
                    boolean z;
                    b bVar;
                    boolean a;
                    if (this.n.isEmpty()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    boolean z2 = false;
                    int i2 = 0;
                    while (i2 < this.n.size()) {
                        bVar = (b) this.n.get(i2);
                        if (bVar.b > i) {
                            a = bVar.a(this.m);
                            break;
                        }
                        i2++;
                        z2 = bVar.a(this.m);
                    }
                    a = false;
                    if (!(a || r1)) {
                        Iterable<a> iterable = this.m;
                        for (b bVar2 : this.n) {
                            for (a aVar2 : iterable) {
                                for (a aVar3 : bVar2.a) {
                                    if (aVar2.equals(aVar3)) {
                                        a = true;
                                        break;
                                        continue;
                                    }
                                }
                                a = false;
                                continue;
                                if (!a) {
                                    a = false;
                                    break;
                                    continue;
                                }
                            }
                            a = true;
                            continue;
                            if (a) {
                                a = true;
                                break;
                            }
                        }
                        a = false;
                        if (!a) {
                            this.n.add(i2, new b(this.m, i));
                            List<a> list = this.m;
                            Iterator it = this.n.iterator();
                            while (it.hasNext()) {
                                bVar = (b) it.next();
                                if (bVar.a.size() != list.size()) {
                                    for (a aVar22 : bVar.a) {
                                        for (a aVar32 : list) {
                                            if (aVar22.equals(aVar32)) {
                                                a = true;
                                                break;
                                                continue;
                                            }
                                        }
                                        a = false;
                                        continue;
                                        if (!a) {
                                            a = false;
                                            break;
                                        }
                                    }
                                    a = true;
                                    if (a) {
                                        it.remove();
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        List<a> a2 = a(false);
                        if (a2 != null) {
                            return a2;
                        }
                        a2 = a(true);
                        if (a2 != null) {
                            return a2;
                        }
                    }
                    throw j.a();
                }
            }
        }
    }

    private List<a> a(boolean z) {
        List<a> list = null;
        if (this.n.size() > 25) {
            this.n.clear();
        } else {
            this.m.clear();
            if (z) {
                Collections.reverse(this.n);
            }
            try {
                list = a(new ArrayList(), 0);
            } catch (j e) {
            }
            if (z) {
                Collections.reverse(this.n);
            }
        }
        return list;
    }

    private List<a> a(List<b> list, int i) throws j {
        while (i < this.n.size()) {
            int i2;
            Object obj;
            b bVar = (b) this.n.get(i);
            this.m.clear();
            int size = list.size();
            for (i2 = 0; i2 < size; i2++) {
                this.m.addAll(((b) list.get(i2)).a);
            }
            this.m.addAll(bVar.a);
            List list2 = this.m;
            int[][] iArr = l;
            int length = iArr.length;
            for (size = 0; size < length; size++) {
                int[] iArr2 = iArr[size];
                if (list2.size() <= iArr2.length) {
                    for (i2 = 0; i2 < list2.size(); i2++) {
                        if (((a) list2.get(i2)).c.a != iArr2[i2]) {
                            obj = null;
                            break;
                        }
                    }
                    int i3 = 1;
                    if (obj != null) {
                        i3 = 1;
                        break;
                    }
                }
            }
            obj = null;
            if (obj == null) {
                i++;
            } else if (b()) {
                return this.m;
            } else {
                List arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(bVar);
                try {
                    return a(arrayList, i + 1);
                } catch (j e) {
                }
            }
        }
        throw j.a();
    }

    private static n a(List<a> list) throws j, e {
        int i;
        com.google.zxing.c.a.a.a.j gVar;
        int size = (list.size() * 2) - 1;
        if (((a) list.get(list.size() - 1)).b == null) {
            i = size - 1;
        } else {
            i = size;
        }
        com.google.zxing.common.a aVar = new com.google.zxing.common.a(i * 12);
        int i2 = ((a) list.get(0)).b.a;
        size = 11;
        i = 0;
        while (size >= 0) {
            if (((1 << size) & i2) != 0) {
                aVar.b(i);
            }
            size--;
            i++;
        }
        int i3 = 1;
        size = i;
        while (i3 < list.size()) {
            a aVar2 = (a) list.get(i3);
            int i4 = aVar2.a.a;
            i2 = 11;
            while (i2 >= 0) {
                if (((1 << i2) & i4) != 0) {
                    aVar.b(size);
                }
                i2--;
                size++;
            }
            if (aVar2.b != null) {
                int i5 = aVar2.b.a;
                i = size;
                size = 11;
                while (size >= 0) {
                    if (((1 << size) & i5) != 0) {
                        aVar.b(i);
                    }
                    size--;
                    i++;
                }
            } else {
                i = size;
            }
            i3++;
            size = i;
        }
        if (aVar.a(1)) {
            gVar = new g(aVar);
        } else if (aVar.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            switch (s.a(aVar, 1, XZBDevice.DOWNLOAD_LIST_ALL)) {
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    gVar = new com.google.zxing.c.a.a.a.a(aVar);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    gVar = new b(aVar);
                    break;
                default:
                    switch (s.a(aVar, 1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                        case XZBDevice.Fail:
                            gVar = new com.google.zxing.c.a.a.a.c(aVar);
                            break;
                        case XZBDevice.Upload:
                            gVar = new d(aVar);
                            break;
                        default:
                            switch (s.a(aVar, 1, R.styleable.Toolbar_contentInsetLeft)) {
                                case R.styleable.AppCompatTheme_dividerHorizontal:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "310", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
                                    break;
                                case R.styleable.AppCompatTheme_activityChooserViewStyle:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "320", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
                                    break;
                                case R.styleable.AppCompatTheme_toolbarStyle:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "310", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
                                    break;
                                case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "320", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
                                    break;
                                case R.styleable.AppCompatTheme_popupMenuStyle:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "310", Constants.VIA_REPORT_TYPE_WPA_STATE);
                                    break;
                                case R.styleable.AppCompatTheme_popupWindowStyle:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "320", Constants.VIA_REPORT_TYPE_WPA_STATE);
                                    break;
                                case R.styleable.AppCompatTheme_editTextColor:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "310", Constants.VIA_REPORT_TYPE_START_GROUP);
                                    break;
                                case R.styleable.AppCompatTheme_editTextBackground:
                                    gVar = new com.google.zxing.c.a.a.a.e(aVar, "320", Constants.VIA_REPORT_TYPE_START_GROUP);
                                    break;
                                default:
                                    throw new IllegalStateException(new StringBuilder("unknown decoder: ").append(aVar).toString());
                            }
                    }
            }
        } else {
            gVar = new k(aVar);
        }
        String a = gVar.a();
        o[] oVarArr = ((a) list.get(0)).c.c;
        o[] oVarArr2 = ((a) list.get(list.size() - 1)).c.c;
        return new n(a, null, new o[]{oVarArr[0], oVarArr[1], oVarArr2[0], oVarArr2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean b() {
        a aVar = (a) this.m.get(0);
        com.google.zxing.c.a.b bVar = aVar.a;
        com.google.zxing.c.a.b bVar2 = aVar.b;
        if (bVar2 == null) {
            return false;
        }
        int i = 2;
        int i2 = bVar2.b;
        for (int i3 = 1; i3 < this.m.size(); i3++) {
            aVar = (a) this.m.get(i3);
            i2 += aVar.a.b;
            i++;
            bVar2 = aVar.b;
            if (bVar2 != null) {
                i2 += bVar2.b;
                i++;
            }
        }
        return (i2 % 211) + ((i + -4) * 211) == bVar.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.c.a.a.a a(com.google.zxing.common.a r15, java.util.List<com.google.zxing.c.a.a.a> r16, int r17) throws com.google.zxing.j {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.c.a.a.c.a(com.google.zxing.common.a, java.util.List, int):com.google.zxing.c.a.a.a");
        /*
        this = this;
        r1 = r16.size();
        r1 = r1 % 2;
        if (r1 != 0) goto L_0x0054;
    L_0x0008:
        r1 = 1;
    L_0x0009:
        r2 = r14.p;
        if (r2 == 0) goto L_0x0141;
    L_0x000d:
        if (r1 != 0) goto L_0x0056;
    L_0x000f:
        r1 = 1;
    L_0x0010:
        r2 = r1;
    L_0x0011:
        r3 = 1;
        r1 = -1;
        r4 = r3;
        r3 = r1;
    L_0x0015:
        r9 = r14.a;
        r1 = 0;
        r5 = 0;
        r9[r1] = r5;
        r1 = 1;
        r5 = 0;
        r9[r1] = r5;
        r1 = 2;
        r5 = 0;
        r9[r1] = r5;
        r1 = 3;
        r5 = 0;
        r9[r1] = r5;
        r10 = r15.b;
        if (r3 >= 0) goto L_0x013e;
    L_0x002b:
        r1 = r16.isEmpty();
        if (r1 == 0) goto L_0x0058;
    L_0x0031:
        r1 = 0;
    L_0x0032:
        r5 = r16.size();
        r5 = r5 % 2;
        if (r5 == 0) goto L_0x006e;
    L_0x003a:
        r5 = 1;
    L_0x003b:
        r6 = r14.p;
        if (r6 == 0) goto L_0x0042;
    L_0x003f:
        if (r5 != 0) goto L_0x0070;
    L_0x0041:
        r5 = 1;
    L_0x0042:
        r6 = 0;
        r13 = r6;
        r6 = r1;
        r1 = r13;
    L_0x0046:
        if (r6 >= r10) goto L_0x0074;
    L_0x0048:
        r1 = r15.a(r6);
        if (r1 != 0) goto L_0x0072;
    L_0x004e:
        r1 = 1;
    L_0x004f:
        if (r1 == 0) goto L_0x0074;
    L_0x0051:
        r6 = r6 + 1;
        goto L_0x0046;
    L_0x0054:
        r1 = 0;
        goto L_0x0009;
    L_0x0056:
        r1 = 0;
        goto L_0x0010;
    L_0x0058:
        r1 = r16.size();
        r1 = r1 + -1;
        r0 = r16;
        r1 = r0.get(r1);
        r1 = (com.google.zxing.c.a.a.a) r1;
        r1 = r1.c;
        r1 = r1.b;
        r5 = 1;
        r1 = r1[r5];
        goto L_0x0032;
    L_0x006e:
        r5 = 0;
        goto L_0x003b;
    L_0x0070:
        r5 = 0;
        goto L_0x0042;
    L_0x0072:
        r1 = 0;
        goto L_0x004f;
    L_0x0074:
        r7 = 0;
        r8 = r6;
        r13 = r7;
        r7 = r1;
        r1 = r6;
        r6 = r13;
    L_0x007a:
        if (r8 >= r10) goto L_0x0118;
    L_0x007c:
        r11 = r15.a(r8);
        r11 = r11 ^ r7;
        if (r11 == 0) goto L_0x008c;
    L_0x0083:
        r11 = r9[r6];
        r11 = r11 + 1;
        r9[r6] = r11;
    L_0x0089:
        r8 = r8 + 1;
        goto L_0x007a;
    L_0x008c:
        r11 = 3;
        if (r6 != r11) goto L_0x0112;
    L_0x008f:
        if (r5 == 0) goto L_0x0094;
    L_0x0091:
        c(r9);
    L_0x0094:
        r11 = b(r9);
        if (r11 == 0) goto L_0x00e7;
    L_0x009a:
        r5 = r14.o;
        r6 = 0;
        r5[r6] = r1;
        r1 = r14.o;
        r5 = 1;
        r1[r5] = r8;
        r0 = r17;
        r5 = r14.a(r15, r0, r2);
        if (r5 != 0) goto L_0x0126;
    L_0x00ac:
        r1 = r14.o;
        r3 = 0;
        r1 = r1[r3];
        r3 = r15.a(r1);
        if (r3 == 0) goto L_0x011d;
    L_0x00b7:
        r1 = r15.d(r1);
        r1 = r15.c(r1);
    L_0x00bf:
        r3 = r4;
    L_0x00c0:
        if (r3 != 0) goto L_0x013a;
    L_0x00c2:
        r1 = 1;
        r3 = r14.a(r15, r5, r2, r1);
        r1 = r16.isEmpty();
        if (r1 != 0) goto L_0x012c;
    L_0x00cd:
        r1 = r16.size();
        r1 = r1 + -1;
        r0 = r16;
        r1 = r0.get(r1);
        r1 = (com.google.zxing.c.a.a.a) r1;
        r1 = r1.b;
        if (r1 != 0) goto L_0x012a;
    L_0x00df:
        r1 = 1;
    L_0x00e0:
        if (r1 == 0) goto L_0x012c;
    L_0x00e2:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x00e7:
        if (r5 == 0) goto L_0x00ec;
    L_0x00e9:
        c(r9);
    L_0x00ec:
        r11 = 0;
        r11 = r9[r11];
        r12 = 1;
        r12 = r9[r12];
        r11 = r11 + r12;
        r1 = r1 + r11;
        r11 = 0;
        r12 = 2;
        r12 = r9[r12];
        r9[r11] = r12;
        r11 = 1;
        r12 = 3;
        r12 = r9[r12];
        r9[r11] = r12;
        r11 = 2;
        r12 = 0;
        r9[r11] = r12;
        r11 = 3;
        r12 = 0;
        r9[r11] = r12;
        r6 = r6 + -1;
    L_0x010a:
        r11 = 1;
        r9[r6] = r11;
        if (r7 != 0) goto L_0x0115;
    L_0x010f:
        r7 = 1;
        goto L_0x0089;
    L_0x0112:
        r6 = r6 + 1;
        goto L_0x010a;
    L_0x0115:
        r7 = 0;
        goto L_0x0089;
    L_0x0118:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x011d:
        r1 = r15.c(r1);
        r1 = r15.d(r1);
        goto L_0x00bf;
    L_0x0126:
        r4 = 0;
        r1 = r3;
        r3 = r4;
        goto L_0x00c0;
    L_0x012a:
        r1 = 0;
        goto L_0x00e0;
    L_0x012c:
        r1 = 0;
        r1 = r14.a(r15, r5, r2, r1);	 Catch:{ j -> 0x0137 }
    L_0x0131:
        r2 = new com.google.zxing.c.a.a.a;
        r2.<init>(r3, r1, r5);
        return r2;
    L_0x0137:
        r1 = move-exception;
        r1 = 0;
        goto L_0x0131;
    L_0x013a:
        r4 = r3;
        r3 = r1;
        goto L_0x0015;
    L_0x013e:
        r1 = r3;
        goto L_0x0032;
    L_0x0141:
        r2 = r1;
        goto L_0x0011;
        */
    }

    private static void c(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(length - i) - 1];
            iArr[(length - i) - 1] = i2;
        }
    }

    private com.google.zxing.c.a.c a(com.google.zxing.common.a aVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            i2 = this.o[0] - 1;
            while (i2 >= 0 && !aVar.a(i2)) {
                i2--;
            }
            i3 = i2 + 1;
            i2 = this.o[0] - i3;
            i4 = this.o[1];
        } else {
            i3 = this.o[0];
            i4 = aVar.d(this.o[1] + 1);
            i2 = i4 - this.o[1];
        }
        Object obj = this.a;
        System.arraycopy(obj, 0, obj, 1, obj.length - 1);
        obj[0] = i2;
        try {
            return new com.google.zxing.c.a.c(a(obj, j), new int[]{i3, i4}, i3, i4, i);
        } catch (j e) {
            return null;
        }
    }

    private com.google.zxing.c.a.b a(com.google.zxing.common.a aVar, com.google.zxing.c.a.c cVar, boolean z, boolean z2) throws j {
        int i;
        int length;
        int[] iArr = this.b;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        if (z2) {
            b(aVar, cVar.b[0], iArr);
        } else {
            a(aVar, cVar.b[1], iArr);
            i = 0;
            for (length = iArr.length - 1; i < length; length--) {
                int i2 = iArr[i];
                iArr[i] = iArr[length];
                iArr[length] = i2;
                i++;
            }
        }
        float a = ((float) a(iArr)) / 17.0f;
        float f = ((float) (cVar.b[1] - cVar.b[0])) / 15.0f;
        if (Math.abs(a - f) / f > 0.3f) {
            throw j.a();
        }
        int i3;
        int i4;
        int length2;
        int[] iArr2 = this.e;
        int[] iArr3 = this.f;
        float[] fArr = this.c;
        float[] fArr2 = this.d;
        for (length = 0; length < iArr.length; length++) {
            float f2 = (1.0f * ((float) iArr[length])) / a;
            i = (int) (0.5f + f2);
            if (i <= 0) {
                if (f2 < 0.3f) {
                    throw j.a();
                }
                i = 1;
            } else if (i > 8) {
                if (f2 > 8.7f) {
                    throw j.a();
                }
                i = XZBDevice.Wait;
            }
            i3 = length / 2;
            if ((length & 1) == 0) {
                iArr2[i3] = i;
                fArr[i3] = f2 - ((float) i);
            } else {
                iArr3[i3] = i;
                fArr2[i3] = f2 - ((float) i);
            }
        }
        int a2 = a(this.e);
        i3 = a(this.f);
        int i5 = (a2 + i3) - 17;
        if ((a2 & 1) == 1) {
            int i6 = 1;
        } else {
            Object obj = null;
        }
        if ((i3 & 1) == 0) {
            i4 = 1;
        } else {
            Object obj2 = null;
        }
        Object obj3 = null;
        Object obj4 = null;
        if (a2 > 13) {
            obj4 = 1;
        } else if (a2 < 4) {
            obj3 = 1;
        }
        Object obj5 = null;
        Object obj6 = null;
        if (i3 > 13) {
            obj6 = 1;
        } else if (i3 < 4) {
            obj5 = 1;
        }
        if (i5 == 1) {
            if (obj != null) {
                if (obj2 != null) {
                    throw j.a();
                }
                obj4 = 1;
            } else if (obj2 == null) {
                throw j.a();
            } else {
                obj6 = 1;
            }
        } else if (i5 == -1) {
            if (obj != null) {
                if (obj2 != null) {
                    throw j.a();
                }
                obj3 = 1;
            } else if (obj2 == null) {
                throw j.a();
            } else {
                obj5 = 1;
            }
        } else if (i5 != 0) {
            throw j.a();
        } else if (obj != null) {
            if (obj2 == null) {
                throw j.a();
            } else if (a2 < i3) {
                obj3 = 1;
                obj6 = 1;
            } else {
                obj4 = 1;
                obj5 = 1;
            }
        } else if (obj2 != null) {
            throw j.a();
        }
        if (obj3 != null) {
            if (obj4 != null) {
                throw j.a();
            }
            a(this.e, this.c);
        }
        if (obj4 != null) {
            b(this.e, this.c);
        }
        if (obj5 != null) {
            if (obj6 != null) {
                throw j.a();
            }
            a(this.f, this.c);
        }
        if (obj6 != null) {
            b(this.f, this.d);
        }
        i = (cVar.a * 4) + (z ? 0 : XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (z2) {
            length = 0;
        } else {
            length = 1;
        }
        i4 = (length + i) - 1;
        i2 = 0;
        i = iArr2.length - 1;
        length = 0;
        while (i >= 0) {
            if (a(cVar, z, z2)) {
                length += k[i4][i * 2] * iArr2[i];
            }
            i--;
            i2 = iArr2[i] + i2;
        }
        i = 0;
        for (length2 = iArr3.length - 1; length2 >= 0; length2--) {
            if (a(cVar, z, z2)) {
                i += k[i4][(length2 * 2) + 1] * iArr3[length2];
            }
        }
        length += i;
        if ((i2 & 1) != 0 || i2 > 13 || i2 < 4) {
            throw j.a();
        }
        i = (13 - i2) / 2;
        length2 = g[i];
        i2 = 9 - length2;
        return new com.google.zxing.c.a.b(i[i] + ((f.a(iArr2, length2, true) * h[i]) + f.a(iArr3, i2, false)), length);
    }

    private static boolean a(com.google.zxing.c.a.c cVar, boolean z, boolean z2) {
        return (cVar.a == 0 && z && z2) ? false : true;
    }
}
