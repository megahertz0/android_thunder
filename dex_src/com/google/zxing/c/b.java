package com.google.zxing.c;

import com.xunlei.tdlive.R;

// compiled from: CodaBarWriter.java
public final class b extends r {
    private static final char[] a;
    private static final char[] b;
    private static final char[] c;
    private static final char d;

    static {
        a = new char[]{'A', 'B', 'C', 'D'};
        b = new char[]{'T', 'N', '*', 'E'};
        c = new char[]{'/', ':', '+', '.'};
        d = a[0];
    }

    public final boolean[] a(String str) {
        boolean a;
        if (str.length() < 2) {
            str = d + str + d;
        } else {
            char toUpperCase = Character.toUpperCase(str.charAt(0));
            char toUpperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean a2 = a.a(a, toUpperCase);
            boolean a3 = a.a(a, toUpperCase2);
            boolean a4 = a.a(b, toUpperCase);
            a = a.a(b, toUpperCase2);
            if (a2) {
                if (!a3) {
                    throw new IllegalArgumentException(new StringBuilder("Invalid start/end guards: ").append(str).toString());
                }
            } else if (a4) {
                if (!a) {
                    throw new IllegalArgumentException(new StringBuilder("Invalid start/end guards: ").append(str).toString());
                }
            } else if (a3 || a) {
                throw new IllegalArgumentException(new StringBuilder("Invalid start/end guards: ").append(str).toString());
            } else {
                str = d + str + d;
            }
        }
        int i = 20;
        int i2 = 1;
        while (i2 < str.length() - 1) {
            if (Character.isDigit(str.charAt(i2)) || str.charAt(i2) == '-' || str.charAt(i2) == '$') {
                i += 9;
            } else if (a.a(c, str.charAt(i2))) {
                i += 10;
            } else {
                throw new IllegalArgumentException(new StringBuilder("Cannot encode : '").append(str.charAt(i2)).append('\'').toString());
            }
            i2++;
        }
        boolean[] zArr = new boolean[((str.length() - 1) + i)];
        i2 = 0;
        i = 0;
        while (i2 < str.length()) {
            int i3;
            boolean z;
            int i4;
            char toUpperCase3 = Character.toUpperCase(str.charAt(i2));
            if (i2 == 0 || i2 == str.length() - 1) {
                switch (toUpperCase3) {
                    case R.styleable.AppCompatTheme_dialogTheme:
                        toUpperCase3 = 'C';
                        break;
                    case R.styleable.AppCompatTheme_listPreferredItemHeight:
                        toUpperCase3 = 'D';
                        break;
                    case R.styleable.AppCompatTheme_panelBackground:
                        toUpperCase3 = 'B';
                        break;
                    case R.styleable.AppCompatTheme_colorAccent:
                        toUpperCase3 = 'A';
                        break;
                }
            }
            int i5 = 0;
            while (i5 < a.a.length) {
                int i6;
                if (toUpperCase3 == a.a[i5]) {
                    i3 = a.b[i5];
                    i5 = 0;
                    z = true;
                    i4 = 0;
                    while (i5 < 7) {
                        zArr[i] = z;
                        i6 = i + 1;
                        if (((i3 >> (6 - i5)) & 1) != 0 || i4 == 1) {
                            if (z) {
                                i = 1;
                            } else {
                                a = false;
                            }
                            i5++;
                            z = a;
                            i4 = 0;
                            i = i6;
                        } else {
                            i4++;
                            i = i6;
                        }
                    }
                    if (i2 < str.length() - 1) {
                        zArr[i] = false;
                        i++;
                    }
                    i2++;
                } else {
                    i5++;
                }
            }
            i3 = 0;
            i5 = 0;
            z = true;
            i4 = 0;
            while (i5 < 7) {
                zArr[i] = z;
                i6 = i + 1;
                if (((i3 >> (6 - i5)) & 1) != 0) {
                }
                if (z) {
                    a = false;
                } else {
                    i = 1;
                }
                i5++;
                z = a;
                i4 = 0;
                i = i6;
            }
            if (i2 < str.length() - 1) {
                zArr[i] = false;
                i++;
            }
            i2++;
        }
        return zArr;
    }
}
