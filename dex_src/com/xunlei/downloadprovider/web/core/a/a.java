package com.xunlei.downloadprovider.web.core.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.xllib.b.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: AdBlockConfig.java
public final class a {
    private static String[] b;
    Map<String, List<String>> a;

    public a() {
        this.a = new HashMap();
    }

    private String a(String str) {
        for (String str2 : this.a.keySet()) {
            if (b(str, str2)) {
                return str2;
            }
        }
        return null;
    }

    private static boolean b(String str, String str2) {
        return str.contains(str2);
    }

    static {
        b = new String[]{"ftp:", "ed2k:", "thunder:", "magnet:?"};
    }

    public final boolean a(String str, String str2) {
        Object obj = null;
        if (TextUtils.isEmpty(str) || a(str) == null) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (obj2 == null) {
            return false;
        }
        if (!TextUtils.isEmpty(str2)) {
            String[] strArr = b;
            int length = strArr.length;
            for (i = 0; i < length; i++) {
                if (str2.startsWith(strArr[i])) {
                    i = 1;
                    break;
                }
            }
        }
        obj2 = null;
        if (obj2 != null) {
            return false;
        }
        CharSequence a;
        if (!TextUtils.isEmpty(str2)) {
            EFileCategoryType a2 = XLFileTypeUtil.a(str2);
            if (EFileCategoryType.E_VIDEO_CATEGORY.equals(a2) || EFileCategoryType.E_MUSIC_CATEGORY.equals(a2) || EFileCategoryType.E_BOOK_CATEGORY.equals(a2) || EFileCategoryType.E_TORRENT_CATEGORY.equals(a2) || EFileCategoryType.E_PICTURE_CATEGORY.equals(a2)) {
                i = 1;
                if (obj2 != null) {
                    return false;
                }
                if (!TextUtils.isEmpty(str)) {
                    a = a(str);
                    if (!TextUtils.isEmpty(a)) {
                        List list = (List) this.a.get(a);
                    }
                }
                if (!TextUtils.isEmpty(str2) || d.a(r0)) {
                    obj = null;
                } else {
                    for (String str3 : r0) {
                        if (b(str2, str3)) {
                            int i2 = 1;
                            break;
                        }
                    }
                    obj = null;
                }
                return obj != null;
            }
        }
        obj2 = null;
        if (obj2 != null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            a = a(str);
            if (TextUtils.isEmpty(a)) {
                List list2 = (List) this.a.get(a);
            }
        }
        if (TextUtils.isEmpty(str2)) {
        }
        obj = null;
        if (obj != null) {
        }
    }
}
