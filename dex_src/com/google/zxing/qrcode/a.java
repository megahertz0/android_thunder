package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.c;
import com.google.zxing.common.d;
import com.google.zxing.common.f;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.n;
import com.google.zxing.o;
import com.google.zxing.p;
import com.google.zxing.qrcode.decoder.e;
import com.google.zxing.qrcode.decoder.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// compiled from: QRCodeReader.java
public final class a implements l {
    private static final o[] a;
    private final e b;

    public a() {
        this.b = new e();
    }

    static {
        a = new o[0];
    }

    public final n a(b bVar, Map<DecodeHintType, ?> map) throws j, c, com.google.zxing.e {
        int i;
        Object obj;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        d a;
        o[] oVarArr;
        n nVar;
        List list;
        String str;
        Object obj2;
        p pVar;
        int i7;
        boolean z2;
        int[] iArr;
        o oVar;
        boolean z3;
        float f;
        float f2;
        float f3;
        float max;
        f a2;
        if (map != null) {
            if (map.containsKey(DecodeHintType.PURE_BARCODE)) {
                com.google.zxing.common.b a3 = bVar.a();
                int[] b = a3.b();
                int[] c = a3.c();
                if (b == null || c == null) {
                    throw j.a();
                }
                int i8 = a3.b;
                i = a3.a;
                obj = 1;
                i2 = 0;
                i3 = b[0];
                i4 = b[1];
                while (i3 < i && i4 < i8) {
                    if (r4 != a3.a(i3, i4)) {
                        i5 = i2 + 1;
                        if (i5 == 5) {
                            break;
                        }
                        int i9 = i5;
                        Object obj3 = !r4 ? 1 : null;
                        i2 = i9;
                    } else {
                        z = r4;
                    }
                    i3++;
                    i4++;
                    boolean z4 = z;
                }
                if (i3 == i || i4 == i8) {
                    throw j.a();
                }
                float f4 = ((float) (i3 - b[0])) / 7.0f;
                i5 = b[1];
                i8 = c[1];
                i6 = b[0];
                i2 = c[0];
                if (i6 >= i2 || i5 >= i8) {
                    throw j.a();
                }
                if (i8 - i5 != i2 - i6) {
                    i2 = (i8 - i5) + i6;
                }
                int round = Math.round(((float) ((i2 - i6) + 1)) / f4);
                int round2 = Math.round(((float) ((i8 - i5) + 1)) / f4);
                if (round <= 0 || round2 <= 0) {
                    throw j.a();
                } else if (round2 != round) {
                    throw j.a();
                } else {
                    i = (int) (f4 / 2.0f);
                    i5 += i;
                    i6 += i;
                    i2 = (((int) (((float) (round - 1)) * f4)) + i6) - i2;
                    if (i2 <= 0) {
                        i4 = i6;
                    } else if (i2 > i) {
                        throw j.a();
                    } else {
                        i4 = i6 - i2;
                    }
                    i2 = (((int) (((float) (round2 - 1)) * f4)) + i5) - i8;
                    if (i2 <= 0) {
                        i2 = i5;
                    } else if (i2 > i) {
                        throw j.a();
                    } else {
                        i2 = i5 - i2;
                    }
                    com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(round, round2);
                    for (i6 = 0; i6 < round2; i6++) {
                        i = i2 + ((int) (((float) i6) * f4));
                        for (i5 = 0; i5 < round; i5++) {
                            if (a3.a(((int) (((float) i5) * f4)) + i4, i)) {
                                bVar2.b(i5, i6);
                            }
                        }
                    }
                    a = this.b.a(bVar2, (Map) map);
                    oVarArr = a;
                    if ((a.g instanceof g) && ((g) a.g).a && oVarArr != null && oVarArr.length >= 3) {
                        o oVar2 = oVarArr[0];
                        oVarArr[0] = oVarArr[2];
                        oVarArr[2] = oVar2;
                    }
                    nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.QR_CODE);
                    list = a.c;
                    if (list != null) {
                        nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                    }
                    str = a.d;
                    if (str != null) {
                        nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                    }
                    if (a.h >= 0 || a.i < 0) {
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        nVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.i));
                        nVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.h));
                    }
                    return nVar;
                }
            }
        }
        com.google.zxing.qrcode.a.c cVar = new com.google.zxing.qrcode.a.c(bVar.a());
        if (map == null) {
            pVar = null;
        } else {
            pVar = (p) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        cVar.b = pVar;
        com.google.zxing.qrcode.a.e eVar = new com.google.zxing.qrcode.a.e(cVar.a, cVar.b);
        if (map != null) {
            if (map.containsKey(DecodeHintType.TRY_HARDER)) {
                i6 = 1;
                if (map != null) {
                    if (map.containsKey(DecodeHintType.PURE_BARCODE)) {
                        z = true;
                        i = eVar.a.b;
                        i7 = eVar.a.a;
                        i2 = (i * 3) / 228;
                        if (i2 < 3 || r4 != null) {
                            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        }
                        z2 = false;
                        iArr = new int[5];
                        i3 = i2 - 1;
                        i6 = i2;
                        while (i3 < i && !r7) {
                            iArr[0] = 0;
                            iArr[1] = 0;
                            iArr[2] = 0;
                            iArr[3] = 0;
                            iArr[4] = 0;
                            i2 = 0;
                            i4 = 0;
                            while (i4 < i7) {
                                if (!eVar.a.a(i4, i3)) {
                                    if ((i2 & 1) == 1) {
                                        i2++;
                                    }
                                    iArr[i2] = iArr[i2] + 1;
                                } else if ((i2 & 1) != 0) {
                                    iArr[i2] = iArr[i2] + 1;
                                } else if (i2 != 4) {
                                    i2++;
                                    iArr[i2] = iArr[i2] + 1;
                                } else if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                                    iArr[0] = iArr[2];
                                    iArr[1] = iArr[3];
                                    iArr[2] = iArr[4];
                                    iArr[3] = 1;
                                    iArr[4] = 0;
                                    i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                                } else if (eVar.a(iArr, i3, i4, z)) {
                                    iArr[0] = iArr[2];
                                    iArr[1] = iArr[3];
                                    iArr[2] = iArr[4];
                                    iArr[3] = 1;
                                    iArr[4] = 0;
                                    i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                                } else {
                                    if (eVar.c) {
                                        if (eVar.b.size() > 1) {
                                            oVar = null;
                                            for (o oVar22 : eVar.b) {
                                                if (oVar22.d < 2) {
                                                    if (oVar == null) {
                                                        eVar.c = true;
                                                        i2 = ((int) (Math.abs(oVar.a - oVar22.a) - Math.abs(oVar.b - oVar22.b))) / 2;
                                                        break;
                                                    }
                                                    oVar = oVar22;
                                                }
                                            }
                                        }
                                        i2 = 0;
                                        if (i2 > iArr[2]) {
                                            i6 = i3 + ((i2 - iArr[2]) - 2);
                                            i2 = i7 - 1;
                                        } else {
                                            i2 = i4;
                                            i6 = i3;
                                        }
                                        i4 = i2;
                                        i3 = i6;
                                        z3 = z2;
                                    } else {
                                        z3 = eVar.a();
                                    }
                                    iArr[0] = 0;
                                    iArr[1] = 0;
                                    iArr[2] = 0;
                                    iArr[3] = 0;
                                    iArr[4] = 0;
                                    z2 = z3;
                                    i2 = 0;
                                    i6 = 2;
                                }
                                i4++;
                            }
                            if (!com.google.zxing.qrcode.a.e.a(iArr) && eVar.a(iArr, i3, i7, z)) {
                                i6 = iArr[0];
                                if (eVar.c) {
                                    z2 = eVar.a();
                                }
                            }
                            i3 += i6;
                        }
                        break;
                        i4 = eVar.b.size();
                        if (i4 >= 3) {
                            throw j.a();
                        }
                        if (i4 > 3) {
                            f = 0.0f;
                            f2 = 0.0f;
                            for (com.google.zxing.qrcode.a.d dVar : eVar.b) {
                                f3 = dVar.c;
                                f += f3;
                                f2 = (f3 * f3) + f2;
                            }
                            f /= (float) i4;
                            f3 = (float) Math.sqrt((double) ((f2 / ((float) i4)) - (f * f)));
                            Collections.sort(eVar.b, new b((byte) 0));
                            max = Math.max(0.2f * f, f3);
                            i5 = 0;
                            while (i5 < eVar.b.size() && eVar.b.size() > 3) {
                                if (Math.abs(((com.google.zxing.qrcode.a.d) eVar.b.get(i5)).c - f) <= max) {
                                    eVar.b.remove(i5);
                                    i5--;
                                }
                                i5++;
                            }
                            break;
                        }
                        if (eVar.b.size() > 3) {
                            f2 = 0.0f;
                            for (com.google.zxing.qrcode.a.d dVar2 : eVar.b) {
                                f2 = dVar2.c + f2;
                            }
                            Collections.sort(eVar.b, new a((byte) 0));
                            eVar.b.subList(XZBDevice.DOWNLOAD_LIST_FAILED, eVar.b.size()).clear();
                        }
                        oVarArr = new o[]{(com.google.zxing.qrcode.a.d) eVar.b.get(0), (com.google.zxing.qrcode.a.d) eVar.b.get(1), (com.google.zxing.qrcode.a.d) eVar.b.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)};
                        o.a(oVarArr);
                        a2 = cVar.a(new com.google.zxing.qrcode.a.f(oVarArr));
                        a = this.b.a(a2.d, (Map) map);
                        oVarArr = a2.e;
                        o oVar222 = oVarArr[0];
                        oVarArr[0] = oVarArr[2];
                        oVarArr[2] = oVar222;
                        nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.QR_CODE);
                        list = a.c;
                        if (list != null) {
                            nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                        }
                        str = a.d;
                        if (str != null) {
                            nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                        }
                        if (a.h >= 0) {
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            nVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.i));
                            nVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.h));
                        }
                        return nVar;
                    }
                }
                z = false;
                i = eVar.a.b;
                i7 = eVar.a.a;
                i2 = (i * 3) / 228;
                i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                z2 = false;
                iArr = new int[5];
                i3 = i2 - 1;
                i6 = i2;
                while (i3 < i) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i2 = 0;
                    i4 = 0;
                    while (i4 < i7) {
                        if (!eVar.a.a(i4, i3)) {
                            if ((i2 & 1) == 1) {
                                i2++;
                            }
                            iArr[i2] = iArr[i2] + 1;
                        } else if ((i2 & 1) != 0) {
                            iArr[i2] = iArr[i2] + 1;
                        } else if (i2 != 4) {
                            i2++;
                            iArr[i2] = iArr[i2] + 1;
                        } else if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = iArr[4];
                            iArr[3] = 1;
                            iArr[4] = 0;
                            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        } else if (eVar.a(iArr, i3, i4, z)) {
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = iArr[4];
                            iArr[3] = 1;
                            iArr[4] = 0;
                            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        } else {
                            if (eVar.c) {
                                if (eVar.b.size() > 1) {
                                    oVar = null;
                                    for (o oVar2222 : eVar.b) {
                                        if (oVar2222.d < 2) {
                                            if (oVar == null) {
                                                eVar.c = true;
                                                i2 = ((int) (Math.abs(oVar.a - oVar2222.a) - Math.abs(oVar.b - oVar2222.b))) / 2;
                                                break;
                                                if (i2 > iArr[2]) {
                                                    i2 = i4;
                                                    i6 = i3;
                                                } else {
                                                    i6 = i3 + ((i2 - iArr[2]) - 2);
                                                    i2 = i7 - 1;
                                                }
                                                i4 = i2;
                                                i3 = i6;
                                                z3 = z2;
                                            } else {
                                                oVar = oVar2222;
                                            }
                                        }
                                    }
                                }
                                i2 = 0;
                                if (i2 > iArr[2]) {
                                    i6 = i3 + ((i2 - iArr[2]) - 2);
                                    i2 = i7 - 1;
                                } else {
                                    i2 = i4;
                                    i6 = i3;
                                }
                                i4 = i2;
                                i3 = i6;
                                z3 = z2;
                            } else {
                                z3 = eVar.a();
                            }
                            iArr[0] = 0;
                            iArr[1] = 0;
                            iArr[2] = 0;
                            iArr[3] = 0;
                            iArr[4] = 0;
                            z2 = z3;
                            i2 = 0;
                            i6 = 2;
                        }
                        i4++;
                    }
                    if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                    }
                    i3 += i6;
                }
                i4 = eVar.b.size();
                if (i4 >= 3) {
                    if (i4 > 3) {
                        f = 0.0f;
                        f2 = 0.0f;
                        while (r6.hasNext()) {
                            f3 = dVar2.c;
                            f += f3;
                            f2 = (f3 * f3) + f2;
                        }
                        f /= (float) i4;
                        f3 = (float) Math.sqrt((double) ((f2 / ((float) i4)) - (f * f)));
                        Collections.sort(eVar.b, new b((byte) 0));
                        max = Math.max(0.2f * f, f3);
                        i5 = 0;
                        while (i5 < eVar.b.size()) {
                            if (Math.abs(((com.google.zxing.qrcode.a.d) eVar.b.get(i5)).c - f) <= max) {
                                eVar.b.remove(i5);
                                i5--;
                            }
                            i5++;
                        }
                    }
                    if (eVar.b.size() > 3) {
                        f2 = 0.0f;
                        while (r4.hasNext()) {
                            f2 = dVar2.c + f2;
                        }
                        Collections.sort(eVar.b, new a((byte) 0));
                        eVar.b.subList(XZBDevice.DOWNLOAD_LIST_FAILED, eVar.b.size()).clear();
                    }
                    oVarArr = new o[]{(com.google.zxing.qrcode.a.d) eVar.b.get(0), (com.google.zxing.qrcode.a.d) eVar.b.get(1), (com.google.zxing.qrcode.a.d) eVar.b.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)};
                    o.a(oVarArr);
                    a2 = cVar.a(new com.google.zxing.qrcode.a.f(oVarArr));
                    a = this.b.a(a2.d, (Map) map);
                    oVarArr = a2.e;
                    o oVar22222 = oVarArr[0];
                    oVarArr[0] = oVarArr[2];
                    oVarArr[2] = oVar22222;
                    nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.QR_CODE);
                    list = a.c;
                    if (list != null) {
                        nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                    }
                    str = a.d;
                    if (str != null) {
                        nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                    }
                    if (a.h >= 0) {
                    }
                    obj2 = null;
                    if (obj2 != null) {
                        nVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.i));
                        nVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.h));
                    }
                    return nVar;
                }
                throw j.a();
            }
        }
        obj = null;
        if (map != null) {
            if (map.containsKey(DecodeHintType.PURE_BARCODE)) {
                z = true;
                i = eVar.a.b;
                i7 = eVar.a.a;
                i2 = (i * 3) / 228;
                i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                z2 = false;
                iArr = new int[5];
                i3 = i2 - 1;
                i6 = i2;
                while (i3 < i) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i2 = 0;
                    i4 = 0;
                    while (i4 < i7) {
                        if (!eVar.a.a(i4, i3)) {
                            if ((i2 & 1) == 1) {
                                i2++;
                            }
                            iArr[i2] = iArr[i2] + 1;
                        } else if ((i2 & 1) != 0) {
                            iArr[i2] = iArr[i2] + 1;
                        } else if (i2 != 4) {
                            i2++;
                            iArr[i2] = iArr[i2] + 1;
                        } else if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = iArr[4];
                            iArr[3] = 1;
                            iArr[4] = 0;
                            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        } else if (eVar.a(iArr, i3, i4, z)) {
                            if (eVar.c) {
                                z3 = eVar.a();
                            } else {
                                if (eVar.b.size() > 1) {
                                    oVar = null;
                                    for (o oVar222222 : eVar.b) {
                                        if (oVar222222.d < 2) {
                                            if (oVar == null) {
                                                oVar = oVar222222;
                                            } else {
                                                eVar.c = true;
                                                i2 = ((int) (Math.abs(oVar.a - oVar222222.a) - Math.abs(oVar.b - oVar222222.b))) / 2;
                                                break;
                                                if (i2 > iArr[2]) {
                                                    i2 = i4;
                                                    i6 = i3;
                                                } else {
                                                    i6 = i3 + ((i2 - iArr[2]) - 2);
                                                    i2 = i7 - 1;
                                                }
                                                i4 = i2;
                                                i3 = i6;
                                                z3 = z2;
                                            }
                                        }
                                    }
                                }
                                i2 = 0;
                                if (i2 > iArr[2]) {
                                    i6 = i3 + ((i2 - iArr[2]) - 2);
                                    i2 = i7 - 1;
                                } else {
                                    i2 = i4;
                                    i6 = i3;
                                }
                                i4 = i2;
                                i3 = i6;
                                z3 = z2;
                            }
                            iArr[0] = 0;
                            iArr[1] = 0;
                            iArr[2] = 0;
                            iArr[3] = 0;
                            iArr[4] = 0;
                            z2 = z3;
                            i2 = 0;
                            i6 = 2;
                        } else {
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = iArr[4];
                            iArr[3] = 1;
                            iArr[4] = 0;
                            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        }
                        i4++;
                    }
                    if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                    }
                    i3 += i6;
                }
                i4 = eVar.b.size();
                if (i4 >= 3) {
                    throw j.a();
                }
                if (i4 > 3) {
                    f = 0.0f;
                    f2 = 0.0f;
                    while (r6.hasNext()) {
                        f3 = dVar2.c;
                        f += f3;
                        f2 = (f3 * f3) + f2;
                    }
                    f /= (float) i4;
                    f3 = (float) Math.sqrt((double) ((f2 / ((float) i4)) - (f * f)));
                    Collections.sort(eVar.b, new b((byte) 0));
                    max = Math.max(0.2f * f, f3);
                    i5 = 0;
                    while (i5 < eVar.b.size()) {
                        if (Math.abs(((com.google.zxing.qrcode.a.d) eVar.b.get(i5)).c - f) <= max) {
                            eVar.b.remove(i5);
                            i5--;
                        }
                        i5++;
                    }
                }
                if (eVar.b.size() > 3) {
                    f2 = 0.0f;
                    while (r4.hasNext()) {
                        f2 = dVar2.c + f2;
                    }
                    Collections.sort(eVar.b, new a((byte) 0));
                    eVar.b.subList(XZBDevice.DOWNLOAD_LIST_FAILED, eVar.b.size()).clear();
                }
                oVarArr = new o[]{(com.google.zxing.qrcode.a.d) eVar.b.get(0), (com.google.zxing.qrcode.a.d) eVar.b.get(1), (com.google.zxing.qrcode.a.d) eVar.b.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)};
                o.a(oVarArr);
                a2 = cVar.a(new com.google.zxing.qrcode.a.f(oVarArr));
                a = this.b.a(a2.d, (Map) map);
                oVarArr = a2.e;
                o oVar2222222 = oVarArr[0];
                oVarArr[0] = oVarArr[2];
                oVarArr[2] = oVar2222222;
                nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.QR_CODE);
                list = a.c;
                if (list != null) {
                    nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                }
                str = a.d;
                if (str != null) {
                    nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                }
                if (a.h >= 0) {
                }
                obj2 = null;
                if (obj2 != null) {
                    nVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.i));
                    nVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.h));
                }
                return nVar;
            }
        }
        z = false;
        i = eVar.a.b;
        i7 = eVar.a.a;
        i2 = (i * 3) / 228;
        i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
        z2 = false;
        iArr = new int[5];
        i3 = i2 - 1;
        i6 = i2;
        while (i3 < i) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            i2 = 0;
            i4 = 0;
            while (i4 < i7) {
                if (!eVar.a.a(i4, i3)) {
                    if ((i2 & 1) == 1) {
                        i2++;
                    }
                    iArr[i2] = iArr[i2] + 1;
                } else if ((i2 & 1) != 0) {
                    iArr[i2] = iArr[i2] + 1;
                } else if (i2 != 4) {
                    i2++;
                    iArr[i2] = iArr[i2] + 1;
                } else if (!com.google.zxing.qrcode.a.e.a(iArr)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                } else if (eVar.a(iArr, i3, i4, z)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
                } else {
                    if (eVar.c) {
                        if (eVar.b.size() > 1) {
                            oVar = null;
                            for (o oVar22222222 : eVar.b) {
                                if (oVar22222222.d < 2) {
                                    if (oVar == null) {
                                        eVar.c = true;
                                        i2 = ((int) (Math.abs(oVar.a - oVar22222222.a) - Math.abs(oVar.b - oVar22222222.b))) / 2;
                                        break;
                                        if (i2 > iArr[2]) {
                                            i2 = i4;
                                            i6 = i3;
                                        } else {
                                            i6 = i3 + ((i2 - iArr[2]) - 2);
                                            i2 = i7 - 1;
                                        }
                                        i4 = i2;
                                        i3 = i6;
                                        z3 = z2;
                                    } else {
                                        oVar = oVar22222222;
                                    }
                                }
                            }
                        }
                        i2 = 0;
                        if (i2 > iArr[2]) {
                            i6 = i3 + ((i2 - iArr[2]) - 2);
                            i2 = i7 - 1;
                        } else {
                            i2 = i4;
                            i6 = i3;
                        }
                        i4 = i2;
                        i3 = i6;
                        z3 = z2;
                    } else {
                        z3 = eVar.a();
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    z2 = z3;
                    i2 = 0;
                    i6 = 2;
                }
                i4++;
            }
            if (!com.google.zxing.qrcode.a.e.a(iArr)) {
            }
            i3 += i6;
        }
        i4 = eVar.b.size();
        if (i4 >= 3) {
            if (i4 > 3) {
                f = 0.0f;
                f2 = 0.0f;
                while (r6.hasNext()) {
                    f3 = dVar2.c;
                    f += f3;
                    f2 = (f3 * f3) + f2;
                }
                f /= (float) i4;
                f3 = (float) Math.sqrt((double) ((f2 / ((float) i4)) - (f * f)));
                Collections.sort(eVar.b, new b((byte) 0));
                max = Math.max(0.2f * f, f3);
                i5 = 0;
                while (i5 < eVar.b.size()) {
                    if (Math.abs(((com.google.zxing.qrcode.a.d) eVar.b.get(i5)).c - f) <= max) {
                        eVar.b.remove(i5);
                        i5--;
                    }
                    i5++;
                }
            }
            if (eVar.b.size() > 3) {
                f2 = 0.0f;
                while (r4.hasNext()) {
                    f2 = dVar2.c + f2;
                }
                Collections.sort(eVar.b, new a((byte) 0));
                eVar.b.subList(XZBDevice.DOWNLOAD_LIST_FAILED, eVar.b.size()).clear();
            }
            oVarArr = new o[]{(com.google.zxing.qrcode.a.d) eVar.b.get(0), (com.google.zxing.qrcode.a.d) eVar.b.get(1), (com.google.zxing.qrcode.a.d) eVar.b.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)};
            o.a(oVarArr);
            a2 = cVar.a(new com.google.zxing.qrcode.a.f(oVarArr));
            a = this.b.a(a2.d, (Map) map);
            oVarArr = a2.e;
            o oVar222222222 = oVarArr[0];
            oVarArr[0] = oVarArr[2];
            oVarArr[2] = oVar222222222;
            nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.QR_CODE);
            list = a.c;
            if (list != null) {
                nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
            }
            str = a.d;
            if (str != null) {
                nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
            }
            if (a.h >= 0) {
            }
            obj2 = null;
            if (obj2 != null) {
                nVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.i));
                nVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.h));
            }
            return nVar;
        }
        throw j.a();
    }

    public final void a() {
    }
}
