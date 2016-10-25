package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.a;
import com.google.zxing.j;
import com.google.zxing.m;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.tdlive.R;
import java.util.EnumMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: UPCEANExtensionSupport.java
final class w {
    private static final int[] a;
    private final u b;
    private final v c;

    w() {
        this.b = new u();
        this.c = new v();
    }

    static {
        a = new int[]{1, 1, 2};
    }

    final n a(int i, a aVar, int i2) throws j {
        int[] a = x.a(aVar, i2, false, a);
        try {
            v vVar = this.c;
            StringBuilder stringBuilder = vVar.c;
            stringBuilder.setLength(0);
            int[] iArr = vVar.b;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            int i3 = aVar.b;
            int i4 = 0;
            int i5 = a[1];
            int i6 = 0;
            while (i6 < 5 && i5 < i3) {
                int a2 = x.a(aVar, iArr, i5, x.e);
                stringBuilder.append((char) ((a2 % 10) + 48));
                int i7 = 0;
                int i8 = i5;
                while (i7 < iArr.length) {
                    i5 = iArr[i7] + i8;
                    i7++;
                    i8 = i5;
                }
                if (a2 >= 10) {
                    i7 = (1 << (4 - i6)) | i4;
                } else {
                    i7 = i4;
                }
                if (i6 != 4) {
                    i8 = aVar.d(aVar.c(i8));
                }
                i6++;
                i4 = i7;
                i5 = i8;
            }
            if (stringBuilder.length() != 5) {
                throw j.a();
            }
            for (i7 = 0; i7 < 10; i7++) {
                if (i4 == v.a[i7]) {
                    CharSequence toString = stringBuilder.toString();
                    int length = toString.length();
                    i6 = 0;
                    for (i8 = length - 2; i8 >= 0; i8 -= 2) {
                        i6 += toString.charAt(i8) - 48;
                    }
                    i6 *= 3;
                    for (i8 = length - 1; i8 >= 0; i8 -= 2) {
                        i6 += toString.charAt(i8) - 48;
                    }
                    if ((i6 * 3) % 10 != i7) {
                        throw j.a();
                    }
                    Map map;
                    String toString2 = stringBuilder.toString();
                    if (toString2.length() != 5) {
                        map = null;
                    } else {
                        String str;
                        String valueOf;
                        Object obj;
                        Map enumMap;
                        switch (toString2.charAt(0)) {
                            case R.styleable.AppCompatTheme_homeAsUpIndicator:
                                str = "\u00a3";
                                i8 = Integer.parseInt(toString2.substring(1));
                                valueOf = String.valueOf(i8 / 100);
                                i8 %= 100;
                                str = str + valueOf + '.' + (i8 >= 10 ? String.valueOf(i8) : new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(i8).toString());
                                if (obj != null) {
                                    enumMap = new EnumMap(ResultMetadataType.class);
                                    enumMap.put(ResultMetadataType.SUGGESTED_PRICE, obj);
                                    map = enumMap;
                                } else {
                                    map = null;
                                }
                                break;
                            case R.styleable.AppCompatTheme_selectableItemBackgroundBorderless:
                                str = "$";
                                i8 = Integer.parseInt(toString2.substring(1));
                                valueOf = String.valueOf(i8 / 100);
                                i8 %= 100;
                                if (i8 >= 10) {
                                }
                                str = str + valueOf + '.' + (i8 >= 10 ? String.valueOf(i8) : new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(i8).toString());
                                if (obj != null) {
                                    map = null;
                                } else {
                                    enumMap = new EnumMap(ResultMetadataType.class);
                                    enumMap.put(ResultMetadataType.SUGGESTED_PRICE, obj);
                                    map = enumMap;
                                }
                                break;
                            case R.styleable.AppCompatTheme_activityChooserViewStyle:
                                if ("90000".equals(toString2)) {
                                    obj = null;
                                } else if ("99991".equals(toString2)) {
                                    str = "0.00";
                                } else if ("99990".equals(toString2)) {
                                    str = "Used";
                                } else {
                                    str = com.umeng.a.d;
                                    i8 = Integer.parseInt(toString2.substring(1));
                                    valueOf = String.valueOf(i8 / 100);
                                    i8 %= 100;
                                    if (i8 >= 10) {
                                    }
                                    str = str + valueOf + '.' + (i8 >= 10 ? String.valueOf(i8) : new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(i8).toString());
                                }
                                if (obj != null) {
                                    enumMap = new EnumMap(ResultMetadataType.class);
                                    enumMap.put(ResultMetadataType.SUGGESTED_PRICE, obj);
                                    map = enumMap;
                                } else {
                                    map = null;
                                }
                                break;
                            default:
                                str = com.umeng.a.d;
                                i8 = Integer.parseInt(toString2.substring(1));
                                valueOf = String.valueOf(i8 / 100);
                                i8 %= 100;
                                if (i8 >= 10) {
                                }
                                str = str + valueOf + '.' + (i8 >= 10 ? String.valueOf(i8) : new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(i8).toString());
                                if (obj != null) {
                                    map = null;
                                } else {
                                    enumMap = new EnumMap(ResultMetadataType.class);
                                    enumMap.put(ResultMetadataType.SUGGESTED_PRICE, obj);
                                    map = enumMap;
                                }
                                break;
                        }
                    }
                    n nVar = new n(toString2, null, new o[]{new o(((float) (a[0] + a[1])) / 2.0f, (float) i), new o((float) i5, (float) i)}, BarcodeFormat.UPC_EAN_EXTENSION);
                    if (map == null) {
                        return nVar;
                    }
                    nVar.a(map);
                    return nVar;
                }
            }
            throw j.a();
        } catch (m e) {
            return this.b.a(i, aVar, a);
        }
    }
}
