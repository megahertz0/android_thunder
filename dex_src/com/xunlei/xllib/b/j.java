package com.xunlei.xllib.b;

import android.text.TextUtils;
import android.text.format.DateFormat;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;

public abstract class j {
    public static String a(byte[] bArr) {
        String toString;
        String str = null;
        try {
            CharBuffer decode = Charset.forName(CharsetConvert.UTF_8).newDecoder().decode(ByteBuffer.wrap(bArr, SimpleLog.LOG_LEVEL_DEBUG, bArr.length - 4));
            if (decode != null) {
                toString = decode.toString();
            } else {
                toString = null;
            }
            if (toString == null) {
                try {
                    CharBuffer decode2 = Charset.forName(CharsetConvert.GBK).newDecoder().decode(ByteBuffer.wrap(bArr, SimpleLog.LOG_LEVEL_DEBUG, bArr.length - 4));
                    if (decode2 != null) {
                        toString = decode2.toString();
                    }
                    if (toString == null) {
                        toString = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
                    }
                } catch (CharacterCodingException e) {
                    toString = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
                } catch (Throwable th) {
                    if (toString == null) {
                        toString = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
                    }
                }
            }
        } catch (CharacterCodingException e2) {
            try {
                decode = Charset.forName(CharsetConvert.GBK).newDecoder().decode(ByteBuffer.wrap(bArr, SimpleLog.LOG_LEVEL_DEBUG, bArr.length - 4));
                if (decode != null) {
                    toString = decode.toString();
                } else {
                    toString = null;
                }
                if (toString == null) {
                    toString = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
                }
            } catch (CharacterCodingException e3) {
                toString = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
            }
        } catch (Throwable th2) {
            try {
                CharBuffer decode3 = Charset.forName(CharsetConvert.GBK).newDecoder().decode(ByteBuffer.wrap(bArr, SimpleLog.LOG_LEVEL_DEBUG, bArr.length - 4));
                if (decode3 != null) {
                    str = decode3.toString();
                }
                if (str == null) {
                    str = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
                }
            } catch (CharacterCodingException e4) {
                str = new String(bArr, 2, bArr.length - 4, Charset.forName(CharsetConvert.ISO_8859_1));
            }
        }
        return toString.trim();
    }

    public static CharSequence a(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis <= 1000) {
            return "1\u79d2\u524d";
        }
        if (currentTimeMillis < 60000) {
            return (currentTimeMillis / 1000) + "\u79d2\u524d";
        }
        if (currentTimeMillis < 3600000) {
            return (currentTimeMillis / 60000) + "\u5206\u949f\u524d";
        }
        if (currentTimeMillis < 86400000) {
            return (currentTimeMillis / 3600000) + "\u5c0f\u65f6\u524d";
        }
        return currentTimeMillis < 172800000 ? (currentTimeMillis / 86400000) + "\u5929\u524d" : DateFormat.format("MM\u6708dd\u65e5", j);
    }

    public static String b(long j) {
        return a(j >= 3600000 ? "HH:mm:ss" : "mm:ss", j);
    }

    public static String a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("Null or empty format string");
        }
        String str2;
        int i;
        long max = Math.max(0, (500 + j) / 1000);
        Object obj = null;
        Object obj2 = null;
        String str3 = BuildConfig.VERSION_NAME;
        if (str.contains("HH")) {
            str3 = "HH";
            obj = 1;
        } else {
            if (str.contains("H")) {
                str3 = "H";
                obj = 1;
            }
        }
        String str4 = BuildConfig.VERSION_NAME;
        if (str.contains("mm")) {
            str4 = "mm";
            obj2 = 1;
        } else {
            if (str.contains("m")) {
                str4 = "m";
                obj2 = 1;
            }
        }
        String str5 = BuildConfig.VERSION_NAME;
        if (str.contains("ss")) {
            str2 = "ss";
            i = 1;
        } else {
            if (str.contains("s")) {
                str2 = "s";
                i = 1;
            } else {
                str2 = str5;
                Object obj3 = null;
            }
        }
        if (obj == null && obj2 == null && obj3 == null) {
            throw new IllegalArgumentException("Illegal format string");
        }
        long j2 = max % 60;
        max /= 60;
        long j3 = 0;
        if (obj != null) {
            j3 = max / 60;
            max %= 60;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        if (obj3 != null) {
            int indexOf = stringBuilder.indexOf(str2);
            if (indexOf != -1) {
                String valueOf = String.valueOf(j2);
                if (j2 < 10 && str2.length() > 1) {
                    valueOf = new StringBuilder("0").append(valueOf).toString();
                }
                stringBuilder.replace(indexOf, str2.length() + indexOf, valueOf);
            }
        }
        if (obj2 != null) {
            i = stringBuilder.indexOf(str4);
            if (i != -1) {
                String valueOf2 = String.valueOf(max);
                if (max < 10 && str4.length() > 1) {
                    valueOf2 = new StringBuilder("0").append(valueOf2).toString();
                }
                stringBuilder.replace(i, str4.length() + i, valueOf2);
            }
        }
        if (obj != null) {
            int indexOf2 = stringBuilder.indexOf(str3);
            if (indexOf2 != -1) {
                String valueOf3 = String.valueOf(j3);
                if (j3 < 10 && str3.length() > 1) {
                    valueOf3 = new StringBuilder("0").append(valueOf3).toString();
                }
                stringBuilder.replace(indexOf2, str3.length() + indexOf2, valueOf3);
            }
        }
        return stringBuilder.toString();
    }

    public static List<String> a(String str) {
        if (str == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("\u300a(.+?)\u300b").matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group().substring(1, matcher.group().length() - 1));
        }
        return arrayList;
    }
}
