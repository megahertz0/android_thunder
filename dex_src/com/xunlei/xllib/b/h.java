package com.xunlei.xllib.b;

import com.umeng.message.proguard.j;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.tools.ConvertUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class h {
    static a a;
    static b b;

    public static class a {
        private static String[] a;

        static {
            a = new String[]{"%00", "%01", "%02", "%03", "%04", "%05", "%06", "%07", "%08", "%09", "%0A", "%0B", "%0C", "%0D", "%0E", "%0F", "%10", "%11", "%12", "%13", "%14", "%15", "%16", "%17", "%18", "%19", "%1A", "%1B", "%1C", "%1D", "%1E", "%1F", "%20", "!", "%22", "%23", "%24", "%25", "%26", "'", j.s, j.t, "*", "%2B", "%2C", c.q, ".", "%2F", "0", com.xunlei.analytics.b.c.f, com.xunlei.analytics.b.c.e, com.xunlei.analytics.b.c.c, com.xunlei.analytics.b.c.d, "5", "6", "7", "8", "9", "%3A", "%3B", "%3C", "%3D", "%3E", "%3F", "%40", "A", ConvertUtil.UNIT_BIT, "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "%5B", "%5C", "%5D", "%5E", "_", "%60", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "%7B", "%7C", "%7D", "~", "%7F", "%80", "%81", "%82", "%83", "%84", "%85", "%86", "%87", "%88", "%89", "%8A", "%8B", "%8C", "%8D", "%8E", "%8F", "%90", "%91", "%92", "%93", "%94", "%95", "%96", "%97", "%98", "%99", "%9A", "%9B", "%9C", "%9D", "%9E", "%9F", "%A0", "%A1", "%A2", "%A3", "%A4", "%A5", "%A6", "%A7", "%A8", "%A9", "%AA", "%AB", "%AC", "%AD", "%AE", "%AF", "%B0", "%B1", "%B2", "%B3", "%B4", "%B5", "%B6", "%B7", "%B8", "%B9", "%BA", "%BB", "%BC", "%BD", "%BE", "%BF", "%C0", "%C1", "%C2", "%C3", "%C4", "%C5", "%C6", "%C7", "%C8", "%C9", "%CA", "%CB", "%CC", "%CD", "%CE", "%CF", "%D0", "%D1", "%D2", "%D3", "%D4", "%D5", "%D6", "%D7", "%D8", "%D9", "%DA", "%DB", "%DC", "%DD", "%DE", "%DF", "%E0", "%E1", "%E2", "%E3", "%E4", "%E5", "%E6", "%E7", "%E8", "%E9", "%EA", "%EB", "%EC", "%ED", "%EE", "%EF", "%F0", "%F1", "%F2", "%F3", "%F4", "%F5", "%F6", "%F7", "%F8", "%F9", "%FA", "%FB", "%FC", "%FD", "%FE", "%FF"};
        }
    }

    private static class b {
        static Pattern a;

        private b() {
        }

        public static String a(String str, String str2) throws UnsupportedEncodingException {
            int i = 0;
            if (str == null || str.isEmpty()) {
                return str;
            }
            if (a == null) {
                a = Pattern.compile("([%][[A-F][a-f][0-9]][[A-F][a-f][0-9]])+");
            }
            Matcher matcher = a.matcher(str);
            StringBuilder stringBuilder = new StringBuilder(str.length());
            int i2 = 0;
            while (matcher.find(i) && i < str.length()) {
                MatchResult toMatchResult = matcher.toMatchResult();
                int start = toMatchResult.start();
                i = toMatchResult.end();
                if (start > i2) {
                    stringBuilder.append(str.substring(i2, start));
                }
                try {
                    stringBuilder.append(Charset.forName(str2).newDecoder().decode(ByteBuffer.wrap(b.a(str.substring(start, i).replaceAll("%", BuildConfig.VERSION_NAME)))).toString());
                    i2 = i;
                } catch (CharacterCodingException e) {
                    throw new UnsupportedEncodingException();
                }
            }
            i = str.length();
            if (i > i2) {
                stringBuilder.append(str.substring(i2, i));
            }
            return stringBuilder.toString();
        }
    }

    static {
        a = new i();
        b = new b();
    }

    public static String a(String str, String str2) throws UnsupportedEncodingException {
        return b.a(str, str2);
    }
}
