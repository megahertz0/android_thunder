package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.xiaomi.channel.commonutils.string.a;
import com.xunlei.tdlive.R;
import java.util.Random;

public class g {
    private static final char[] a;
    private static final char[] b;
    private static final char[] c;
    private static final char[] d;
    private static final char[] e;
    private static Random f;
    private static char[] g;

    static {
        a = "&quot;".toCharArray();
        b = "&apos;".toCharArray();
        c = "&amp;".toCharArray();
        d = "&lt;".toCharArray();
        e = "&gt;".toCharArray();
        f = new Random();
        g = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public static String a(int i) {
        if (i <= 0) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = g[f.nextInt(R.styleable.AppCompatTheme_listPreferredItemHeightLarge)];
        }
        return new String(cArr);
    }

    public static String a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        StringBuilder stringBuilder = new StringBuilder((int) (((double) length) * 1.3d));
        int i2 = 0;
        while (i2 < length) {
            char c = toCharArray[i2];
            if (c <= '>') {
                if (c == '<') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(d);
                } else if (c == '>') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(e);
                } else if (c == '&') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    if (length <= i2 + 5 || toCharArray[i2 + 1] != '#' || !Character.isDigit(toCharArray[i2 + 2]) || !Character.isDigit(toCharArray[i2 + 3]) || !Character.isDigit(toCharArray[i2 + 4]) || toCharArray[i2 + 5] != ';') {
                        i = i2 + 1;
                        stringBuilder.append(c);
                    }
                } else if (c == '\"') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(a);
                } else if (c == '\'') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(b);
                }
            }
            i2++;
        }
        if (i == 0) {
            return str;
        }
        if (i2 > i) {
            stringBuilder.append(toCharArray, i, i2 - i);
        }
        return stringBuilder.toString();
    }

    public static final String a(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(str2, 0);
        if (indexOf < 0) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        char[] toCharArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuilder stringBuilder = new StringBuilder(toCharArray.length);
        stringBuilder.append(toCharArray, 0, indexOf).append(toCharArray2);
        indexOf += length;
        int i = indexOf;
        while (true) {
            i = str.indexOf(str2, i);
            if (i > 0) {
                stringBuilder.append(toCharArray, indexOf, i - indexOf).append(toCharArray2);
                indexOf = i + length;
                i = indexOf;
            } else {
                stringBuilder.append(toCharArray, indexOf, toCharArray.length - indexOf);
                return stringBuilder.toString();
            }
        }
    }

    public static String a(byte[] bArr) {
        return String.valueOf(a.a(bArr));
    }

    public static boolean a(char c) {
        if (c < ' ' || c > '\ud7ff') {
            if (c < '\ue000' || c > '\ufffd') {
                if (!((c >= '\u0000' && c <= '\uffff') || c == '\t' || c == '\n' || c == '\r')) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final String b(String str) {
        return a(a(a(a(a(str, "&lt;", "<"), "&gt;", ">"), "&quot;", h.f), "&apos;", "'"), "&amp;", com.alipay.sdk.sys.a.b);
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (a(charAt)) {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
