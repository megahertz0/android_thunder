package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;

final class ai {
    private String a;
    private String[] b;
    private String[] c;

    public ai(String str) throws IllegalArgumentException {
        this.a = str;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Word must not be empty");
        }
        this.b = b(this.a);
        this.c = c(this.a);
    }

    private static String[] b(String str) {
        String[] strArr;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            strArr = null;
        } else if (TextUtils.isEmpty(str)) {
            strArr = null;
        } else {
            ArrayList arrayList = new ArrayList();
            String[] split = str.split("[\\s\\+]");
            int length = split.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = split[i2];
                int i3 = -1;
                int i4 = -1;
                for (int i5 = 0; i5 < str2.length(); i5++) {
                    if (str2.charAt(i5) <= '\u00ff') {
                        if (i4 < 0) {
                            i4 = i5;
                        }
                        i3 = i5;
                    } else {
                        if (i3 >= i4 && i3 >= 0) {
                            arrayList.add(str2.substring(i4, i3));
                            i3 = -1;
                            i4 = -1;
                        }
                        arrayList.add(new String(new char[]{r11}));
                    }
                }
                if (i3 >= i4 && i3 >= 0) {
                    arrayList.add(str2.substring(i4, i3));
                }
            }
            strArr = arrayList.isEmpty() ? null : (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        if (strArr != null && strArr.length > 0) {
            while (i < strArr.length) {
                strArr[i] = strArr[i].toLowerCase();
                i++;
            }
        }
        return strArr;
    }

    private static String[] c(String str) {
        String[] b = b(str);
        if (b == null || b.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (b != null && b.length > 0) {
            String str2 = BuildConfig.VERSION_NAME;
            int length = b.length;
            String str3 = str2;
            for (int i = 0; i < b.length; i++) {
                String toLowerCase = b[i].toLowerCase();
                String c = ar.c(toLowerCase);
                if (toLowerCase.equals(c)) {
                    str3 = str3 + toLowerCase;
                    if (i == length - 1) {
                        arrayList.add(str3);
                        break;
                    }
                } else {
                    if (!str3.isEmpty()) {
                        arrayList.add(str3);
                        str3 = BuildConfig.VERSION_NAME;
                    }
                    arrayList.add(c);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public final boolean a(String str) {
        if (this.b == null || this.b.length == 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        int i;
        boolean z;
        Object toLowerCase = str.toLowerCase();
        String[] strArr = this.b;
        int length = strArr.length;
        int i2 = 0;
        for (i = 0; i < length; i++) {
            if (toLowerCase.contains(strArr[i])) {
                i2++;
            }
        }
        if (i2 <= 0 || (((double) i2) * 1.0d) / ((double) this.b.length) <= 0.2d || (((double) i2) * 1.0d) / ((double) d(toLowerCase)) <= 0.1d) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            return z;
        }
        if (this.c == null || this.c.length == 0 || TextUtils.isEmpty(toLowerCase)) {
            return false;
        }
        String toLowerCase2 = toLowerCase.toLowerCase();
        strArr = this.c;
        length = strArr.length;
        i2 = 0;
        for (i = 0; i < length; i++) {
            if (toLowerCase2.contains(strArr[i])) {
                i2++;
            }
        }
        return i2 > 0 && (((double) i2) * 1.0d) / ((double) this.c.length) > 0.2d && (((double) i2) * 1.0d) / ((double) d(toLowerCase2)) > 0.1d;
    }

    private static int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split("\\s\\+");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str2 = split[i];
            int i3 = -1;
            int i4 = -1;
            int i5 = i2;
            for (i2 = 0; i2 < str2.length(); i2++) {
                if (str2.charAt(i2) <= '\u00ff') {
                    if (i4 < 0) {
                        i4 = i2;
                    }
                    i3 = i2;
                } else {
                    if (i3 >= i4 && i3 >= 0) {
                        i5++;
                        i3 = -1;
                        i4 = -1;
                    }
                    i5++;
                }
            }
            if (i3 >= i4 && i3 >= 0) {
                i5++;
            }
            i++;
            i2 = i5;
        }
        return i2;
    }
}
