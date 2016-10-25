package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// compiled from: Code128Reader.java
public final class c extends q {
    static final int[][] a;

    static {
        a = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    }

    private static int a(a aVar, int[] iArr, int i) throws j {
        a(aVar, i, iArr);
        float f = 0.25f;
        int i2 = -1;
        for (int i3 = 0; i3 < a.length; i3++) {
            float a = a(iArr, a[i3], 0.7f);
            if (a < f) {
                i2 = i3;
                f = a;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw j.a();
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j, e, com.google.zxing.c {
        Object obj;
        int i2;
        int c;
        int i3;
        Object obj2;
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        Object obj3;
        float a;
        int i8;
        int[] iArr;
        int i9;
        List arrayList;
        StringBuilder stringBuilder;
        int i10;
        int[] iArr2;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        int a2;
        Object obj11;
        int i11;
        byte[] bArr;
        if (map != null) {
            if (map.containsKey(DecodeHintType.ASSUME_GS1)) {
                obj = 1;
                i2 = aVar.b;
                c = aVar.c(0);
                i3 = 0;
                obj2 = new Object[6];
                i4 = 0;
                for (i5 = c; i5 < i2; i5++) {
                    if ((aVar.a(i5) ^ i4) == 0) {
                        obj2[i3] = obj2[i3] + 1;
                        i6 = i4;
                        i7 = i3;
                    } else {
                        if (i3 != 5) {
                            f = 0.25f;
                            obj3 = -1;
                            i7 = R.styleable.AppCompatTheme_checkedTextViewStyle;
                            while (i7 <= 105) {
                                a = a(obj2, a[i7], 0.7f);
                                if (a >= f) {
                                    i8 = i7;
                                } else {
                                    a = f;
                                }
                                i7++;
                                f = a;
                            }
                            if (i8 >= 0) {
                                if (aVar.a(Math.max(0, c - ((i5 - c) / 2)), c)) {
                                    iArr = new int[3];
                                    iArr[0] = c;
                                    iArr[1] = i5;
                                    iArr[2] = i8;
                                    i9 = iArr[2];
                                    arrayList = new ArrayList(20);
                                    arrayList.add(Byte.valueOf((byte) i9));
                                    switch (i9) {
                                        case R.styleable.AppCompatTheme_checkedTextViewStyle:
                                            obj3 = R.styleable.AppCompatTheme_buttonStyleSmall;
                                            break;
                                        case R.styleable.AppCompatTheme_editTextStyle:
                                            obj3 = R.styleable.AppCompatTheme_buttonStyle;
                                            break;
                                        case R.styleable.AppCompatTheme_radioButtonStyle:
                                            obj3 = R.styleable.AppCompatTheme_autoCompleteTextViewStyle;
                                            break;
                                        default:
                                            throw e.a();
                                    }
                                    stringBuilder = new StringBuilder(20);
                                    i3 = iArr[0];
                                    i10 = iArr[1];
                                    iArr2 = new int[6];
                                    obj4 = 1;
                                    obj5 = null;
                                    obj6 = null;
                                    obj7 = null;
                                    obj8 = obj3;
                                    i8 = 0;
                                    i7 = i9;
                                    Object obj12 = null;
                                    obj9 = null;
                                    i2 = i3;
                                    i3 = 0;
                                    while (obj9 == null) {
                                        obj10 = null;
                                        a2 = a(aVar, iArr2, i10);
                                        arrayList.add(Byte.valueOf((byte) a2));
                                        if (a2 != 106) {
                                            obj4 = 1;
                                        }
                                        if (a2 != 106) {
                                            i8++;
                                            i7 += i8 * a2;
                                        }
                                        i9 = i10;
                                        for (i2 = 0; i2 < 6; i2++) {
                                            i9 += iArr2[i2];
                                        }
                                        switch (a2) {
                                            case R.styleable.AppCompatTheme_checkedTextViewStyle:
                                            case R.styleable.AppCompatTheme_editTextStyle:
                                            case R.styleable.AppCompatTheme_radioButtonStyle:
                                                throw e.a();
                                            default:
                                                switch (i5) {
                                                    case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                        if (a2 >= 100) {
                                                            if (a2 < 10) {
                                                                stringBuilder.append('0');
                                                            }
                                                            stringBuilder.append(a2);
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                        } else {
                                                            if (a2 != 106) {
                                                                obj4 = null;
                                                            }
                                                            switch (a2) {
                                                                case R.styleable.AppCompatTheme_buttonStyle:
                                                                    obj11 = obj9;
                                                                    i6 = 100;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                                    obj11 = obj9;
                                                                    i6 = 101;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_checkboxStyle:
                                                                    if (obj != null) {
                                                                        if (stringBuilder.length() != 0) {
                                                                            stringBuilder.append("]C1");
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        } else {
                                                                            stringBuilder.append('\u001d');
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        }
                                                                    }
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_ratingBarStyle:
                                                                    i6 = i5;
                                                                    i5 = 1;
                                                                    break;
                                                                default:
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case R.styleable.AppCompatTheme_buttonStyle:
                                                        if (a2 >= 96) {
                                                            if (obj6 != obj5) {
                                                                stringBuilder.append((char) (a2 + 32));
                                                            } else {
                                                                stringBuilder.append((char) ((a2 + 32) + 128));
                                                            }
                                                            obj6 = null;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                        } else {
                                                            if (a2 != 106) {
                                                                obj4 = null;
                                                            }
                                                            switch (a2) {
                                                                case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle:
                                                                case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                                                    obj10 = 1;
                                                                    obj11 = obj9;
                                                                    i6 = 101;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                                    obj11 = obj9;
                                                                    i6 = 99;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonStyle:
                                                                    if (obj5 != null && obj6 != null) {
                                                                        obj5 = 1;
                                                                        obj6 = null;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    } else if (obj5 != null || obj6 == null) {
                                                                        obj6 = 1;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    } else {
                                                                        obj5 = null;
                                                                        obj6 = null;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    }
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                                    obj11 = obj9;
                                                                    i6 = 101;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_checkboxStyle:
                                                                    if (obj != null) {
                                                                        if (stringBuilder.length() != 0) {
                                                                            stringBuilder.append("]C1");
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        } else {
                                                                            stringBuilder.append('\u001d');
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        }
                                                                    }
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_ratingBarStyle:
                                                                    obj9 = 1;
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                default:
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                        if (a2 < 64) {
                                                            if (obj6 != obj5) {
                                                                stringBuilder.append((char) (a2 + 32));
                                                            } else {
                                                                stringBuilder.append((char) ((a2 + 32) + 128));
                                                            }
                                                            obj6 = null;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                        } else if (a2 >= 96) {
                                                            if (obj6 != obj5) {
                                                                stringBuilder.append((char) (a2 - 64));
                                                            } else {
                                                                stringBuilder.append((char) (a2 + 64));
                                                            }
                                                            obj6 = null;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                        } else {
                                                            if (a2 != 106) {
                                                                obj4 = null;
                                                            }
                                                            switch (a2) {
                                                                case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle:
                                                                case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                                                    obj10 = 1;
                                                                    obj11 = obj9;
                                                                    i6 = 100;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                                    obj11 = obj9;
                                                                    i6 = 99;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonStyle:
                                                                    obj11 = obj9;
                                                                    i6 = 100;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                                    if (obj5 != null && obj6 != null) {
                                                                        obj5 = 1;
                                                                        obj6 = null;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    } else if (obj5 != null || obj6 == null) {
                                                                        obj6 = 1;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    } else {
                                                                        obj5 = null;
                                                                        obj6 = null;
                                                                        obj11 = obj9;
                                                                        i6 = i5;
                                                                        obj8 = obj11;
                                                                    }
                                                                    break;
                                                                case R.styleable.AppCompatTheme_checkboxStyle:
                                                                    if (obj != null) {
                                                                        if (stringBuilder.length() != 0) {
                                                                            stringBuilder.append("]C1");
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        } else {
                                                                            stringBuilder.append('\u001d');
                                                                            obj11 = obj9;
                                                                            i6 = i5;
                                                                            obj8 = obj11;
                                                                        }
                                                                    }
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                case R.styleable.AppCompatTheme_ratingBarStyle:
                                                                    obj9 = 1;
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                                default:
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    default:
                                                        obj11 = obj9;
                                                        i6 = i5;
                                                        obj8 = obj11;
                                                        break;
                                                }
                                                if (obj7 != null) {
                                                    obj9 = i6 != 101 ? R.styleable.AppCompatTheme_buttonStyle : R.styleable.AppCompatTheme_buttonStyleSmall;
                                                }
                                                i2 = i10;
                                                obj7 = obj10;
                                                i10 = i9;
                                                i3 = r14;
                                                int i12 = a2;
                                                obj11 = obj8;
                                                obj8 = obj9;
                                                obj9 = obj11;
                                                break;
                                        }
                                    }
                                    i11 = i10 - i2;
                                    i6 = aVar.d(i10);
                                    if (!aVar.a(i6, Math.min(aVar.b, ((i6 - i2) / 2) + i6))) {
                                        throw j.a();
                                    } else if ((i7 - (i8 * i3)) % 103 == i3) {
                                        throw com.google.zxing.c.a();
                                    } else {
                                        i8 = stringBuilder.length();
                                        if (i8 != 0) {
                                            throw j.a();
                                        }
                                        int size;
                                        if (i8 > 0 && r8 != null) {
                                            if (i5 != 99) {
                                                stringBuilder.delete(i8 - 2, i8);
                                            } else {
                                                stringBuilder.delete(i8 - 1, i8);
                                            }
                                        }
                                        float f2 = ((float) (iArr[1] + iArr[0])) / 2.0f;
                                        a = ((float) i2) + (((float) i11) / 2.0f);
                                        size = arrayList.size();
                                        bArr = new byte[size];
                                        for (i8 = 0; i8 < size; i8++) {
                                            bArr[i8] = ((Byte) arrayList.get(i8)).byteValue();
                                        }
                                        return new n(stringBuilder.toString(), bArr, new o[]{new o(f2, (float) i), new o(a, (float) i)}, BarcodeFormat.CODE_128);
                                    }
                                }
                            }
                            i8 = (obj2[0] + obj2[1]) + c;
                            System.arraycopy(obj2, XZBDevice.DOWNLOAD_LIST_RECYCLE, obj2, 0, XZBDevice.DOWNLOAD_LIST_ALL);
                            obj2[4] = null;
                            obj2[5] = null;
                            i7 = i3 - 1;
                        } else {
                            i7 = i3 + 1;
                            i8 = c;
                        }
                        obj2[i7] = 1;
                        obj9 = i4 != 0 ? 1 : null;
                        c = i8;
                    }
                    i4 = i6;
                    i3 = i7;
                }
                throw j.a();
            }
        }
        obj = null;
        i2 = aVar.b;
        c = aVar.c(0);
        i3 = 0;
        obj2 = new Object[6];
        i4 = 0;
        for (i5 = c; i5 < i2; i5++) {
            if ((aVar.a(i5) ^ i4) == 0) {
                if (i3 != 5) {
                    i7 = i3 + 1;
                    i8 = c;
                } else {
                    f = 0.25f;
                    obj3 = -1;
                    i7 = R.styleable.AppCompatTheme_checkedTextViewStyle;
                    while (i7 <= 105) {
                        a = a(obj2, a[i7], 0.7f);
                        if (a >= f) {
                            a = f;
                        } else {
                            i8 = i7;
                        }
                        i7++;
                        f = a;
                    }
                    if (i8 >= 0) {
                        if (aVar.a(Math.max(0, c - ((i5 - c) / 2)), c)) {
                            iArr = new int[3];
                            iArr[0] = c;
                            iArr[1] = i5;
                            iArr[2] = i8;
                            i9 = iArr[2];
                            arrayList = new ArrayList(20);
                            arrayList.add(Byte.valueOf((byte) i9));
                            switch (i9) {
                                case R.styleable.AppCompatTheme_checkedTextViewStyle:
                                    obj3 = R.styleable.AppCompatTheme_buttonStyleSmall;
                                    break;
                                case R.styleable.AppCompatTheme_editTextStyle:
                                    obj3 = R.styleable.AppCompatTheme_buttonStyle;
                                    break;
                                case R.styleable.AppCompatTheme_radioButtonStyle:
                                    obj3 = R.styleable.AppCompatTheme_autoCompleteTextViewStyle;
                                    break;
                                default:
                                    throw e.a();
                            }
                            stringBuilder = new StringBuilder(20);
                            i3 = iArr[0];
                            i10 = iArr[1];
                            iArr2 = new int[6];
                            obj4 = 1;
                            obj5 = null;
                            obj6 = null;
                            obj7 = null;
                            obj8 = obj3;
                            i8 = 0;
                            i7 = i9;
                            Object obj122 = null;
                            obj9 = null;
                            i2 = i3;
                            i3 = 0;
                            while (obj9 == null) {
                                obj10 = null;
                                a2 = a(aVar, iArr2, i10);
                                arrayList.add(Byte.valueOf((byte) a2));
                                if (a2 != 106) {
                                    obj4 = 1;
                                }
                                if (a2 != 106) {
                                    i8++;
                                    i7 += i8 * a2;
                                }
                                i9 = i10;
                                for (i2 = 0; i2 < 6; i2++) {
                                    i9 += iArr2[i2];
                                }
                                switch (a2) {
                                    case R.styleable.AppCompatTheme_checkedTextViewStyle:
                                    case R.styleable.AppCompatTheme_editTextStyle:
                                    case R.styleable.AppCompatTheme_radioButtonStyle:
                                        throw e.a();
                                    default:
                                        switch (i5) {
                                            case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                if (a2 >= 100) {
                                                    if (a2 != 106) {
                                                        obj4 = null;
                                                    }
                                                    switch (a2) {
                                                        case R.styleable.AppCompatTheme_buttonStyle:
                                                            obj11 = obj9;
                                                            i6 = 100;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                            obj11 = obj9;
                                                            i6 = 101;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_checkboxStyle:
                                                            if (obj != null) {
                                                                if (stringBuilder.length() != 0) {
                                                                    stringBuilder.append('\u001d');
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                } else {
                                                                    stringBuilder.append("]C1");
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                }
                                                            }
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_ratingBarStyle:
                                                            i6 = i5;
                                                            i5 = 1;
                                                            break;
                                                        default:
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                    }
                                                } else {
                                                    if (a2 < 10) {
                                                        stringBuilder.append('0');
                                                    }
                                                    stringBuilder.append(a2);
                                                    obj11 = obj9;
                                                    i6 = i5;
                                                    obj8 = obj11;
                                                }
                                                break;
                                            case R.styleable.AppCompatTheme_buttonStyle:
                                                if (a2 >= 96) {
                                                    if (a2 != 106) {
                                                        obj4 = null;
                                                    }
                                                    switch (a2) {
                                                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle:
                                                        case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                                            obj10 = 1;
                                                            obj11 = obj9;
                                                            i6 = 101;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                            obj11 = obj9;
                                                            i6 = 99;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonStyle:
                                                            if (obj5 != null) {
                                                            }
                                                            if (obj5 != null) {
                                                            }
                                                            obj6 = 1;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                            obj11 = obj9;
                                                            i6 = 101;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_checkboxStyle:
                                                            if (obj != null) {
                                                                if (stringBuilder.length() != 0) {
                                                                    stringBuilder.append('\u001d');
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                } else {
                                                                    stringBuilder.append("]C1");
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                }
                                                            }
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_ratingBarStyle:
                                                            obj9 = 1;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        default:
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                    }
                                                } else {
                                                    if (obj6 != obj5) {
                                                        stringBuilder.append((char) ((a2 + 32) + 128));
                                                    } else {
                                                        stringBuilder.append((char) (a2 + 32));
                                                    }
                                                    obj6 = null;
                                                    obj11 = obj9;
                                                    i6 = i5;
                                                    obj8 = obj11;
                                                }
                                                break;
                                            case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                if (a2 < 64) {
                                                    if (obj6 != obj5) {
                                                        stringBuilder.append((char) ((a2 + 32) + 128));
                                                    } else {
                                                        stringBuilder.append((char) (a2 + 32));
                                                    }
                                                    obj6 = null;
                                                    obj11 = obj9;
                                                    i6 = i5;
                                                    obj8 = obj11;
                                                } else if (a2 >= 96) {
                                                    if (a2 != 106) {
                                                        obj4 = null;
                                                    }
                                                    switch (a2) {
                                                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle:
                                                        case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                                            obj10 = 1;
                                                            obj11 = obj9;
                                                            i6 = 100;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                                            obj11 = obj9;
                                                            i6 = 99;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonStyle:
                                                            obj11 = obj9;
                                                            i6 = 100;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_buttonStyleSmall:
                                                            if (obj5 != null) {
                                                            }
                                                            if (obj5 != null) {
                                                            }
                                                            obj6 = 1;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_checkboxStyle:
                                                            if (obj != null) {
                                                                if (stringBuilder.length() != 0) {
                                                                    stringBuilder.append('\u001d');
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                } else {
                                                                    stringBuilder.append("]C1");
                                                                    obj11 = obj9;
                                                                    i6 = i5;
                                                                    obj8 = obj11;
                                                                }
                                                            }
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        case R.styleable.AppCompatTheme_ratingBarStyle:
                                                            obj9 = 1;
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                        default:
                                                            obj11 = obj9;
                                                            i6 = i5;
                                                            obj8 = obj11;
                                                            break;
                                                    }
                                                } else {
                                                    if (obj6 != obj5) {
                                                        stringBuilder.append((char) (a2 + 64));
                                                    } else {
                                                        stringBuilder.append((char) (a2 - 64));
                                                    }
                                                    obj6 = null;
                                                    obj11 = obj9;
                                                    i6 = i5;
                                                    obj8 = obj11;
                                                }
                                                break;
                                            default:
                                                obj11 = obj9;
                                                i6 = i5;
                                                obj8 = obj11;
                                                break;
                                        }
                                        if (obj7 != null) {
                                            if (i6 != 101) {
                                            }
                                        }
                                        i2 = i10;
                                        obj7 = obj10;
                                        i10 = i9;
                                        i3 = i12;
                                        int i122 = a2;
                                        obj11 = obj8;
                                        obj8 = obj9;
                                        obj9 = obj11;
                                        break;
                                }
                            }
                            i11 = i10 - i2;
                            i6 = aVar.d(i10);
                            if (!aVar.a(i6, Math.min(aVar.b, ((i6 - i2) / 2) + i6))) {
                                throw j.a();
                            } else if ((i7 - (i8 * i3)) % 103 == i3) {
                                i8 = stringBuilder.length();
                                if (i8 != 0) {
                                    if (i5 != 99) {
                                        stringBuilder.delete(i8 - 1, i8);
                                    } else {
                                        stringBuilder.delete(i8 - 2, i8);
                                    }
                                    float f22 = ((float) (iArr[1] + iArr[0])) / 2.0f;
                                    a = ((float) i2) + (((float) i11) / 2.0f);
                                    size = arrayList.size();
                                    bArr = new byte[size];
                                    for (i8 = 0; i8 < size; i8++) {
                                        bArr[i8] = ((Byte) arrayList.get(i8)).byteValue();
                                    }
                                    return new n(stringBuilder.toString(), bArr, new o[]{new o(f22, (float) i), new o(a, (float) i)}, BarcodeFormat.CODE_128);
                                }
                                throw j.a();
                            } else {
                                throw com.google.zxing.c.a();
                            }
                        }
                    }
                    i8 = (obj2[0] + obj2[1]) + c;
                    System.arraycopy(obj2, XZBDevice.DOWNLOAD_LIST_RECYCLE, obj2, 0, XZBDevice.DOWNLOAD_LIST_ALL);
                    obj2[4] = null;
                    obj2[5] = null;
                    i7 = i3 - 1;
                }
                obj2[i7] = 1;
                if (i4 != 0) {
                }
                c = i8;
            } else {
                obj2[i3] = obj2[i3] + 1;
                i6 = i4;
                i7 = i3;
            }
            i4 = i6;
            i3 = i7;
        }
        throw j.a();
    }
}
