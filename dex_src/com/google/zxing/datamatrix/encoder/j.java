package com.google.zxing.datamatrix.encoder;

import com.google.zxing.d;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;

// compiled from: HighLevelEncoder.java
public final class j {
    public static String a(String str, SymbolShapeHint symbolShapeHint, d dVar, d dVar2) {
        int i = 0;
        g[] gVarArr = new g[]{new a(), new c(), new l(), new m(), new f(), new b()};
        h hVar = new h(str);
        hVar.b = symbolShapeHint;
        hVar.c = dVar;
        hVar.d = dVar2;
        if (str.startsWith("[)>\u001e05\u001d") && str.endsWith("\u001e\u0004")) {
            hVar.a('\u00ec');
            hVar.i = 2;
            hVar.f += 7;
        } else if (str.startsWith("[)>\u001e06\u001d") && str.endsWith("\u001e\u0004")) {
            hVar.a('\u00ed');
            hVar.i = 2;
            hVar.f += 7;
        }
        while (hVar.b()) {
            gVarArr[i].a(hVar);
            if (hVar.g >= 0) {
                i = hVar.g;
                hVar.g = -1;
            }
        }
        int length = hVar.e.length();
        hVar.d();
        int i2 = hVar.h.b;
        if (!(length >= i2 || i == 0 || i == 5)) {
            hVar.a('\u00fe');
        }
        StringBuilder stringBuilder = hVar.e;
        if (stringBuilder.length() < i2) {
            stringBuilder.append('\u0081');
        }
        while (stringBuilder.length() < i2) {
            char c;
            i = ((((stringBuilder.length() + 1) * 149) % 253) + 1) + 129;
            if (i <= 254) {
                c = (char) i;
            } else {
                c = (char) (i - 254);
            }
            stringBuilder.append(c);
        }
        return hVar.e.toString();
    }

    static int a(CharSequence charSequence, int i, int i2) {
        if (i >= charSequence.length()) {
            return i2;
        }
        float[] fArr;
        int[] iArr;
        int i3;
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i2] = 0.0f;
        }
        int i4 = 0;
        while (i + i4 != charSequence.length()) {
            Object obj;
            byte[] bArr;
            int a;
            char charAt = charSequence.charAt(i + i4);
            i4++;
            if (a(charAt)) {
                fArr[0] = (float) (((double) fArr[0]) + 0.5d);
            } else if (b(charAt)) {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 1.0f;
            }
            if (charAt != ' ') {
                if ((charAt < '0' || charAt > '9') && (charAt < 'A' || charAt > 'Z')) {
                    obj = null;
                    if (obj != null) {
                        fArr[1] = fArr[1] + 0.6666667f;
                    } else if (b(charAt)) {
                        fArr[1] = fArr[1] + 1.3333334f;
                    } else {
                        fArr[1] = fArr[1] + 2.6666667f;
                    }
                    if (charAt != ' ') {
                        if ((charAt < '0' || charAt > '9') && (charAt < 'a' || charAt > 'z')) {
                            obj = null;
                            if (obj == null) {
                                fArr[2] = fArr[2] + 0.6666667f;
                            } else if (b(charAt)) {
                                fArr[2] = fArr[2] + 1.3333334f;
                            } else {
                                fArr[2] = fArr[2] + 2.6666667f;
                            }
                            if (!d(charAt)) {
                                fArr[3] = fArr[3] + 0.6666667f;
                            } else if (b(charAt)) {
                                fArr[3] = fArr[3] + 3.3333333f;
                            } else {
                                fArr[3] = fArr[3] + 4.3333335f;
                            }
                            if (charAt >= ' ' || charAt > '^') {
                                obj = null;
                            } else {
                                obj = 1;
                            }
                            if (obj == null) {
                                fArr[4] = fArr[4] + 0.75f;
                            } else if (b(charAt)) {
                                fArr[4] = fArr[4] + 3.25f;
                            } else {
                                fArr[4] = fArr[4] + 4.25f;
                            }
                            fArr[5] = fArr[5] + 1.0f;
                            if (i4 < 4) {
                                iArr = new int[6];
                                bArr = new byte[6];
                                a(fArr, iArr, (int) InMobiClientPositioning.NO_REPEAT, bArr);
                                a = a(bArr);
                                if (iArr[0] >= iArr[5] && iArr[0] < iArr[1] && iArr[0] < iArr[2] && iArr[0] < iArr[3] && iArr[0] < iArr[4]) {
                                    return 0;
                                }
                                if (iArr[5] < iArr[0] && ((bArr[1] + bArr[2]) + bArr[3]) + bArr[4] != 0) {
                                    if (a == 1 && bArr[4] > null) {
                                        return XZBDevice.DOWNLOAD_LIST_ALL;
                                    }
                                    if (a == 1 && bArr[2] > null) {
                                        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
                                    }
                                    if (a == 1 && bArr[3] > null) {
                                        return XZBDevice.DOWNLOAD_LIST_FAILED;
                                    }
                                    if (iArr[1] + 1 < iArr[0] && iArr[1] + 1 < iArr[5] && iArr[1] + 1 < iArr[4] && iArr[1] + 1 < iArr[2]) {
                                        if (iArr[1] < iArr[3]) {
                                            return 1;
                                        }
                                        if (iArr[1] == iArr[3]) {
                                            i3 = (i + i4) + 1;
                                            while (i3 < charSequence.length()) {
                                                char charAt2 = charSequence.charAt(i3);
                                                if (!e(charAt2)) {
                                                    if (!d(charAt2)) {
                                                        break;
                                                    }
                                                    i3++;
                                                } else {
                                                    return XZBDevice.DOWNLOAD_LIST_FAILED;
                                                }
                                            }
                                            return 1;
                                        }
                                    }
                                }
                                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                            }
                        }
                    }
                    obj = 1;
                    if (obj == null) {
                        fArr[2] = fArr[2] + 0.6666667f;
                    } else if (b(charAt)) {
                        fArr[2] = fArr[2] + 1.3333334f;
                    } else {
                        fArr[2] = fArr[2] + 2.6666667f;
                    }
                    if (!d(charAt)) {
                        fArr[3] = fArr[3] + 0.6666667f;
                    } else if (b(charAt)) {
                        fArr[3] = fArr[3] + 3.3333333f;
                    } else {
                        fArr[3] = fArr[3] + 4.3333335f;
                    }
                    if (charAt >= ' ') {
                    }
                    obj = null;
                    if (obj == null) {
                        fArr[4] = fArr[4] + 0.75f;
                    } else if (b(charAt)) {
                        fArr[4] = fArr[4] + 3.25f;
                    } else {
                        fArr[4] = fArr[4] + 4.25f;
                    }
                    fArr[5] = fArr[5] + 1.0f;
                    if (i4 < 4) {
                        iArr = new int[6];
                        bArr = new byte[6];
                        a(fArr, iArr, (int) InMobiClientPositioning.NO_REPEAT, bArr);
                        a = a(bArr);
                        if (iArr[0] >= iArr[5]) {
                        }
                        if (iArr[5] < iArr[0]) {
                        }
                        return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    }
                }
            }
            obj = 1;
            if (obj != null) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[1] = fArr[1] + 1.3333334f;
            } else {
                fArr[1] = fArr[1] + 2.6666667f;
            }
            if (charAt != ' ') {
                obj = null;
                if (obj == null) {
                    fArr[2] = fArr[2] + 0.6666667f;
                } else if (b(charAt)) {
                    fArr[2] = fArr[2] + 2.6666667f;
                } else {
                    fArr[2] = fArr[2] + 1.3333334f;
                }
                if (!d(charAt)) {
                    fArr[3] = fArr[3] + 0.6666667f;
                } else if (b(charAt)) {
                    fArr[3] = fArr[3] + 4.3333335f;
                } else {
                    fArr[3] = fArr[3] + 3.3333333f;
                }
                if (charAt >= ' ') {
                }
                obj = null;
                if (obj == null) {
                    fArr[4] = fArr[4] + 0.75f;
                } else if (b(charAt)) {
                    fArr[4] = fArr[4] + 4.25f;
                } else {
                    fArr[4] = fArr[4] + 3.25f;
                }
                fArr[5] = fArr[5] + 1.0f;
                if (i4 < 4) {
                    iArr = new int[6];
                    bArr = new byte[6];
                    a(fArr, iArr, (int) InMobiClientPositioning.NO_REPEAT, bArr);
                    a = a(bArr);
                    if (iArr[0] >= iArr[5]) {
                    }
                    if (iArr[5] < iArr[0]) {
                    }
                    return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                }
            }
            obj = 1;
            if (obj == null) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[2] = fArr[2] + 1.3333334f;
            } else {
                fArr[2] = fArr[2] + 2.6666667f;
            }
            if (!d(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[3] = fArr[3] + 3.3333333f;
            } else {
                fArr[3] = fArr[3] + 4.3333335f;
            }
            if (charAt >= ' ') {
            }
            obj = null;
            if (obj == null) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (b(charAt)) {
                fArr[4] = fArr[4] + 3.25f;
            } else {
                fArr[4] = fArr[4] + 4.25f;
            }
            fArr[5] = fArr[5] + 1.0f;
            if (i4 < 4) {
                iArr = new int[6];
                bArr = new byte[6];
                a(fArr, iArr, (int) InMobiClientPositioning.NO_REPEAT, bArr);
                a = a(bArr);
                if (iArr[0] >= iArr[5]) {
                }
                if (iArr[5] < iArr[0]) {
                }
                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            }
        }
        byte[] bArr2 = new byte[6];
        iArr = new int[6];
        i3 = a(fArr, iArr, (int) InMobiClientPositioning.NO_REPEAT, bArr2);
        int a2 = a(bArr2);
        if (iArr[0] == i3) {
            return 0;
        }
        if (a2 == 1 && bArr2[5] > null) {
            return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        }
        if (a2 == 1 && bArr2[4] > null) {
            return XZBDevice.DOWNLOAD_LIST_ALL;
        }
        if (a2 != 1 || bArr2[2] <= null) {
            return (a2 != 1 || bArr2[3] <= null) ? 1 : XZBDevice.DOWNLOAD_LIST_FAILED;
        } else {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
    }

    private static int a(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i2 = i;
        for (int i3 = 0; i3 < 6; i3++) {
            iArr[i3] = (int) Math.ceil((double) fArr[i3]);
            int i4 = iArr[i3];
            if (i2 > i4) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i4;
            }
            if (i2 == i4) {
                bArr[i3] = (byte) (bArr[i3] + 1);
            }
        }
        return i2;
    }

    private static int a(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 6) {
            i2 += bArr[i];
            i++;
        }
        return i2;
    }

    static boolean a(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean b(char c) {
        return c >= '\u0080' && c <= '\u00ff';
    }

    private static boolean d(char c) {
        if (!(e(c) || c == ' ')) {
            if ((c < '0' || c > '9') && (c < 'A' || c > 'Z')) {
                return false;
            }
        }
        return true;
    }

    private static boolean e(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    static void c(char c) {
        String toHexString = Integer.toHexString(c);
        throw new IllegalArgumentException(new StringBuilder("Illegal character: ").append(c).append(" (0x").append("0000".substring(0, 4 - toHexString.length()) + toHexString).append(')').toString());
    }
}
