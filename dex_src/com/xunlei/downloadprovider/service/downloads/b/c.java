package com.xunlei.downloadprovider.service.downloads.b;

import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.c.b;
import java.util.Collection;
import java.util.regex.PatternSyntaxException;

// compiled from: DownloadsUtil.java
public abstract class c {
    public static String a(String str) {
        String str2 = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                str2 = str.replaceAll("[:\uff1a\\*\\?\\|\"\\<\\>\u201c\u201d!\uff01\uff1f]", "_");
            }
        } catch (PatternSyntaxException e) {
            RuntimeException e2 = e;
            e2.printStackTrace();
            if (str2 == null) {
                return str2;
            }
            return TextUtils.isEmpty(str) ? a.d : str;
        } catch (NullPointerException e3) {
            e2 = e3;
            e2.printStackTrace();
            if (str2 == null) {
                return str2;
            }
            if (TextUtils.isEmpty(str)) {
            }
        }
        if (str2 == null) {
            return str2;
        }
        if (TextUtils.isEmpty(str)) {
        }
    }

    public static String b(String str) {
        return new StringBuilder("magnet:?xt=urn:btih:").append(str).toString();
    }

    public static String a(String str, long j, String str2, String str3) {
        StringBuilder append = new StringBuilder("cid://").append(str).append("|size|").append(j).append("|gcid|");
        if (str2 == null) {
            str2 = a.d;
        }
        String toString = append.append(str2).toString();
        return !TextUtils.isEmpty(str3) ? toString + "|file|" + str3 : toString;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return a.d;
        }
        if (b.b(str)) {
            CharSequence f = b.f(str);
            if (!TextUtils.isEmpty(f)) {
                CharSequence charSequence = f;
            }
        }
        return b.g(str);
    }

    public static long[] a(Collection<Long> collection) {
        int i = 0;
        long[] jArr = new long[(collection == null ? 0 : collection.size())];
        if (collection != null) {
            for (Long l : collection) {
                int i2 = i + 1;
                jArr[i] = l.longValue();
                i = i2;
            }
        }
        return jArr;
    }
}
