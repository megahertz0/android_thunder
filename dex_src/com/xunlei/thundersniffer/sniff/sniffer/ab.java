package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.SniffingResource$Category;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.HashMap;

final class ab {
    static final String[] a;
    static final String[] b;
    static final String[] c;
    static final String[] d;
    static final String[] e;
    static final String[] f;
    static final HashMap<String, SniffingResource$Category> g;

    static {
        a = new String[]{"torrent"};
        b = new String[]{"mp4", "avi", "rmvb", "rm", "3gp", "3pg", "flv", "wmv", "mkv", "mpg", "mov"};
        c = new String[]{"mp3", "wav", "ram", "wma", "amr", "aac"};
        d = new String[]{"txt", "pdf", "rtf", "docx", "doc", "xlsx", "xls", "pptx", "ppt"};
        e = new String[]{"zip", "7z", "rar"};
        f = new String[]{"apk"};
        g = new ac();
    }

    static String a() {
        int i = 0;
        String str = BuildConfig.VERSION_NAME;
        String[] strArr = a;
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str2 = strArr[i2];
            StringBuilder append = new StringBuilder().append(str);
            int i4 = i3 + 1;
            str2 = append.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str2).toString();
            i2++;
            i3 = i4;
            str = str2;
        }
        strArr = b;
        length = strArr.length;
        i2 = 0;
        while (i2 < length) {
            str2 = strArr[i2];
            append = new StringBuilder().append(str);
            i4 = i3 + 1;
            str2 = append.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str2).toString();
            i2++;
            i3 = i4;
            str = str2;
        }
        strArr = c;
        length = strArr.length;
        i2 = 0;
        while (i2 < length) {
            str2 = strArr[i2];
            append = new StringBuilder().append(str);
            i4 = i3 + 1;
            str2 = append.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str2).toString();
            i2++;
            i3 = i4;
            str = str2;
        }
        strArr = d;
        length = strArr.length;
        i2 = 0;
        while (i2 < length) {
            str2 = strArr[i2];
            append = new StringBuilder().append(str);
            i4 = i3 + 1;
            str2 = append.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str2).toString();
            i2++;
            i3 = i4;
            str = str2;
        }
        strArr = e;
        length = strArr.length;
        i2 = 0;
        while (i2 < length) {
            str2 = strArr[i2];
            append = new StringBuilder().append(str);
            i4 = i3 + 1;
            str2 = append.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str2).toString();
            i2++;
            i3 = i4;
            str = str2;
        }
        String[] strArr2 = f;
        int length2 = strArr2.length;
        while (i < length2) {
            String str3 = strArr2[i];
            StringBuilder append2 = new StringBuilder().append(str);
            i4 = i3 + 1;
            str3 = append2.append(i3 == 0 ? BuildConfig.VERSION_NAME : "|").append(str3).toString();
            i++;
            i3 = i4;
            str = str3;
        }
        return str;
    }

    static SniffingResource$Category a(String str) {
        return g.containsKey(str) ? (SniffingResource$Category) g.get(str) : SniffingResource$Category.NONE;
    }

    static SniffingResource$Category b(String str) {
        return a(c(str));
    }

    static String c(String str) {
        String str2 = BuildConfig.VERSION_NAME;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
        return (lastIndexOf == -1 || lastIndexOf + 1 >= str.length()) ? str2 : str.substring(lastIndexOf + 1).toLowerCase();
    }
}
