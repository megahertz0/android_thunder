package com.xunlei.tdlive.util;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.lang.Character.UnicodeBlock;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: StringInfoUtil.java
public class v {
    public static String a(String str, int i) {
        return a(str, i, SimpleLog.LOG_LEVEL_DEBUG, 1, "...");
    }

    public static String a(String str, int i, int i2, int i3, String str2) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.VERSION_NAME;
        }
        if (str2 == null) {
            str2 = BuildConfig.VERSION_NAME;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            String substring = str.substring(i5, i5 + 1);
            if (a(substring) || b(substring)) {
                i4 += i2;
            } else {
                i4 += i3;
            }
            if (i4 > i) {
                return str.substring(0, i5) + str2;
            }
        }
        return str;
    }

    public static int a(String str, int i, int i2) {
        int i3 = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i4 = 0;
        while (i3 < str.length()) {
            String substring = str.substring(i3, i3 + 1);
            if (a(substring) || b(substring)) {
                i4 += i;
            } else {
                i4 += i2;
            }
            i3++;
        }
        return i4;
    }

    public static boolean a(String str) {
        char[] toCharArray = str.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            if (!a(toCharArray[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(char c) {
        UnicodeBlock of = UnicodeBlock.of(c);
        return of == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == UnicodeBlock.GENERAL_PUNCTUATION || of == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean b(String str) {
        return Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle).matcher(str).find();
    }
}
