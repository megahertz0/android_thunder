package android.support.a.a;

import android.graphics.Path;
import com.alipay.sdk.util.h;
import com.xunlei.tdlive.R;
import java.util.ArrayList;

// compiled from: PathParser.java
final class d {

    // compiled from: PathParser.java
    private static class a {
        int a;
        boolean b;

        private a() {
        }
    }

    // compiled from: PathParser.java
    public static class b {
        char a;
        float[] b;

        private b(char c, float[] fArr) {
            this.a = c;
            this.b = fArr;
        }

        private b(android.support.a.a.d.b bVar) {
            this.a = bVar.a;
            this.b = d.a(bVar.b, bVar.b.length);
        }

        static void a(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double toRadians;
            double cos;
            double sin;
            double d;
            double d2;
            double d3;
            double d4;
            double d5;
            double d6;
            double d7;
            double d8;
            double d9;
            Path path2 = path;
            while (true) {
                toRadians = Math.toRadians((double) f7);
                cos = Math.cos(toRadians);
                sin = Math.sin(toRadians);
                d = ((((double) f) * cos) + (((double) f2) * sin)) / ((double) f5);
                d2 = ((((double) (-f)) * sin) + (((double) f2) * cos)) / ((double) f6);
                d3 = ((((double) f3) * cos) + (((double) f4) * sin)) / ((double) f5);
                d4 = ((((double) (-f3)) * sin) + (((double) f4) * cos)) / ((double) f6);
                d5 = d - d3;
                d6 = d2 - d4;
                d7 = (d + d3) / 2.0d;
                double d10 = (d2 + d4) / 2.0d;
                d8 = (d5 * d5) + (d6 * d6);
                if (d8 != 0.0d) {
                    d9 = (1.0d / d8) - 0.25d;
                    if (d9 >= 0.0d) {
                        break;
                    }
                    float sqrt = (float) (Math.sqrt(d8) / 1.99999d);
                    f5 *= sqrt;
                    f6 *= sqrt;
                } else {
                    return;
                }
            }
            d8 = Math.sqrt(d9);
            d5 *= d8;
            d6 *= d8;
            if (z == z2) {
                d6 = d7 - d6;
                d5 += d10;
            } else {
                d6 += d7;
                d5 = d10 - d5;
            }
            d2 = Math.atan2(d2 - d5, d - d6);
            d = Math.atan2(d4 - d5, d3 - d6) - d2;
            if (z2 != (d >= 0.0d ? 1 : null)) {
                if (d > 0.0d) {
                    d -= 6.283185307179586d;
                } else {
                    d += 6.283185307179586d;
                }
            }
            d6 *= (double) f5;
            d5 *= (double) f6;
            d8 = (d6 * cos) - (d5 * sin);
            d9 = (d6 * sin) + (d5 * cos);
            double d11 = (double) f5;
            double d12 = (double) f6;
            sin = (double) f;
            cos = (double) f2;
            int ceil = (int) Math.ceil(Math.abs((4.0d * d) / 3.141592653589793d));
            double cos2 = Math.cos(toRadians);
            double sin2 = Math.sin(toRadians);
            d5 = Math.cos(d2);
            toRadians = Math.sin(d2);
            d6 = (((-d11) * cos2) * toRadians) - ((d12 * sin2) * d5);
            d5 = (d5 * (d12 * cos2)) + (toRadians * ((-d11) * sin2));
            double d13 = d / ((double) ceil);
            d = d2;
            int i = 0;
            d3 = cos;
            d7 = sin;
            while (i < ceil) {
                sin = d + d13;
                d2 = Math.sin(sin);
                double cos3 = Math.cos(sin);
                d10 = (((d11 * cos2) * cos3) + d8) - ((d12 * sin2) * d2);
                d4 = ((d12 * cos2) * d2) + (((d11 * sin2) * cos3) + d9);
                cos = (((-d11) * cos2) * d2) - ((d12 * sin2) * cos3);
                d2 = (d2 * ((-d11) * sin2)) + (cos3 * (d12 * cos2));
                cos3 = Math.tan((sin - d) / 2.0d);
                d = (Math.sin(sin - d) * (Math.sqrt((cos3 * (3.0d * cos3)) + 4.0d) - 1.0d)) / 3.0d;
                path2.cubicTo((float) ((d6 * d) + d7), (float) ((d5 * d) + d3), (float) (d10 - (d * cos)), (float) (d4 - (d * d2)), (float) d10, (float) d4);
                d5 = d2;
                d6 = cos;
                d = sin;
                i++;
                d3 = d4;
                d7 = d10;
            }
        }
    }

    static float[] a(float[] fArr, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = i + 0;
        length = Math.min(i2, length + 0);
        Object obj = new Object[i2];
        System.arraycopy(fArr, 0, obj, 0, length);
        return obj;
    }

    public static b[] a(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int a = a(str, i);
            String trim = str.substring(i2, a).trim();
            if (trim.length() > 0) {
                a(arrayList, trim.charAt(0), b(trim));
            }
            i = a + 1;
            i2 = a;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            a(arrayList, str.charAt(i2), new float[0]);
        }
        return (b[]) arrayList.toArray(new b[arrayList.size()]);
    }

    public static b[] a(b[] bVarArr) {
        if (bVarArr == null) {
            return null;
        }
        b[] bVarArr2 = new b[bVarArr.length];
        for (int i = 0; i < bVarArr.length; i++) {
            bVarArr2[i] = new b((byte) 0);
        }
        return bVarArr2;
    }

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 65) * (charAt - 90) <= 0 || (charAt - 97) * (charAt - 122) <= 0) && charAt != 'e' && charAt != 'E') {
                break;
            }
            i++;
        }
        return i;
    }

    private static void a(ArrayList<b> arrayList, char c, float[] fArr) {
        arrayList.add(new b(fArr, (byte) 0));
    }

    private static float[] b(String str) {
        if (((str.charAt(0) == 'z' ? 1 : 0) | (str.charAt(0) == 'Z' ? 1 : 0)) != 0) {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            a aVar = new a();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (i < length) {
                aVar.b = false;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = i;
                while (i6 < str.length()) {
                    switch (str.charAt(i6)) {
                        case R.styleable.AppCompatTheme_actionModeCutDrawable:
                        case R.styleable.AppCompatTheme_listDividerAlertDialog:
                            i5 = i4;
                            i3 = 1;
                            i4 = 0;
                            break;
                        case R.styleable.AppCompatTheme_actionDropDownStyle:
                            if (i6 != i && r5 == 0) {
                                aVar.b = true;
                                i5 = i4;
                                i3 = 1;
                                i4 = 0;
                            }
                            i3 = i5;
                            i5 = i4;
                            i4 = 0;
                            break;
                        case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight:
                            if (i4 == 0) {
                                i4 = 0;
                                i3 = i5;
                                i5 = 1;
                            } else {
                                aVar.b = true;
                                i5 = i4;
                                i3 = 1;
                                i4 = 0;
                            }
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemHeight:
                        case R.styleable.AppCompatTheme_buttonStyleSmall:
                            i3 = i5;
                            i5 = i4;
                            i4 = 1;
                            break;
                        default:
                            i3 = i5;
                            i5 = i4;
                            i4 = 0;
                            break;
                    }
                    if (i3 == 0) {
                        i6++;
                        int i7 = i4;
                        i4 = i5;
                        i5 = i3;
                        i3 = i7;
                    } else {
                        aVar.a = i6;
                        i5 = aVar.a;
                        if (i >= i5) {
                            i4 = i2 + 1;
                            fArr[i2] = Float.parseFloat(str.substring(i, i5));
                        } else {
                            i4 = i2;
                        }
                        if (aVar.b) {
                            i = i5 + 1;
                            i2 = i4;
                        } else {
                            i = i5;
                            i2 = i4;
                        }
                    }
                }
                aVar.a = i6;
                i5 = aVar.a;
                if (i >= i5) {
                    i4 = i2;
                } else {
                    i4 = i2 + 1;
                    fArr[i2] = Float.parseFloat(str.substring(i, i5));
                }
                if (aVar.b) {
                    i = i5 + 1;
                    i2 = i4;
                } else {
                    i = i5;
                    i2 = i4;
                }
            }
            return a(fArr, i2);
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder("error in parsing \"").append(str).append(h.f).toString(), e);
        }
    }
}
