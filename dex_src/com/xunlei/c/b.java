package com.xunlei.c;

import android.text.TextUtils;
import android.util.Base64;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.c.d.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b {
    private static a a;

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("http://") || toLowerCase.startsWith("https://") || toLowerCase.startsWith("ftp://")) {
            return 0;
        }
        if (toLowerCase.startsWith("thunder://")) {
            return 1;
        }
        if (toLowerCase.startsWith("magnet:?")) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        return toLowerCase.startsWith("ed2k://") ? XZBDevice.DOWNLOAD_LIST_FAILED : -1;
    }

    public static boolean b(String str) {
        return a(str) == 1;
    }

    public static boolean c(String str) {
        return a(str) == 2;
    }

    public static boolean d(String str) {
        return a(str) == 3;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("thunder://");
        String toString = new StringBuilder("AA").append(str).append("ZZ").toString();
        try {
            toString = Base64.encodeToString(toString.getBytes(GameManager.DEFAULT_CHARSET), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } catch (UnsupportedEncodingException e) {
            toString = Base64.encodeToString(toString.getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        stringBuilder.append(toString);
        return stringBuilder.toString();
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = "thunder://";
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            return null;
        }
        str2 = str.substring(str2.length() + indexOf);
        if (!TextUtils.isEmpty(str2)) {
            if (str2.endsWith("pa/") || str2.endsWith("=/")) {
                str2 = str2.substring(0, str2.length() - 1);
            }
        }
        try {
            byte[] decode = Base64.decode(str2.getBytes("ISO-8859-1"), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (UnsupportedEncodingException e2) {
            decode = Base64.decode(str2.getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        if (decode == null || decode.length <= 4) {
            return null;
        }
        String a = a(decode);
        if (TextUtils.isEmpty(a)) {
            return a;
        }
        return (a.startsWith("http%3A%2F%2F") || a.startsWith("https%3A%2F%2F") || a.startsWith("ftp%3A%2F%2F") || a.startsWith("magnet%3A%3F") || a.startsWith("ed2k%3A%2F%2F")) ? h(a) : a;
    }

    private static String a(byte[] bArr) {
        String toString;
        String str = null;
        try {
            CharBuffer decode = Charset.forName(GameManager.DEFAULT_CHARSET).newDecoder().decode(ByteBuffer.wrap(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE, bArr.length - 4));
            if (decode != null) {
                toString = decode.toString();
            } else {
                toString = null;
            }
            if (toString == null) {
                try {
                    CharBuffer decode2 = Charset.forName("GBK").newDecoder().decode(ByteBuffer.wrap(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE, bArr.length - 4));
                    if (decode2 != null) {
                        toString = decode2.toString();
                    }
                    if (toString == null) {
                        toString = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
                    }
                } catch (CharacterCodingException e) {
                    toString = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
                } catch (Throwable th) {
                    if (toString == null) {
                        toString = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
                    }
                }
            }
        } catch (CharacterCodingException e2) {
            try {
                decode = Charset.forName("GBK").newDecoder().decode(ByteBuffer.wrap(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE, bArr.length - 4));
                if (decode != null) {
                    toString = decode.toString();
                } else {
                    toString = null;
                }
                if (toString == null) {
                    toString = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
                }
            } catch (CharacterCodingException e3) {
                toString = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
            }
        } catch (Throwable th2) {
            try {
                CharBuffer decode3 = Charset.forName("GBK").newDecoder().decode(ByteBuffer.wrap(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE, bArr.length - 4));
                if (decode3 != null) {
                    str = decode3.toString();
                }
                if (str == null) {
                    str = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
                }
            } catch (CharacterCodingException e4) {
                str = new String(bArr, 2, bArr.length - 4, Charset.forName("ISO-8859-1"));
            }
        }
        return toString.trim();
    }

    public static String g(String str) {
        return h(str);
    }

    public static String h(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("%")) {
            return str;
        }
        try {
            str = d.a(str, GameManager.DEFAULT_CHARSET);
            return str;
        } catch (UnsupportedEncodingException e) {
            try {
                str = d.a(str, "GBK");
                return str;
            } catch (UnsupportedEncodingException e2) {
                try {
                    return d.a(str, "UTF-16");
                } catch (UnsupportedEncodingException e3) {
                    try {
                        return d.a(str, "ISO-8859-1");
                    } catch (UnsupportedEncodingException e4) {
                        return str;
                    } catch (Exception e5) {
                        return str;
                    }
                } catch (Exception e52) {
                    return str;
                }
            } catch (Exception e522) {
                return str;
            }
        } catch (Exception e5222) {
            return str;
        }
    }

    public static String i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Matcher matcher = Pattern.compile("thunder://[A-Za-z0-9\\+/]+(?:=)*").matcher(str);
            if (!matcher.find()) {
                return null;
            }
            String str2;
            MatchResult toMatchResult = matcher.toMatchResult();
            int start = toMatchResult.start();
            int end = toMatchResult.end();
            if (start == 0 && end == str.length() - 1) {
                str2 = str;
            } else {
                str2 = str.substring(start, end);
            }
            if (TextUtils.isEmpty(str2)) {
                return str2;
            }
            return (str2.endsWith("pa/") || str2.endsWith("=/")) ? str2.substring(0, str2.length() - 1) : str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        try {
            return a.a(str, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.umeng.a.d;
        }
    }

    public static String k(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.umeng.a.d;
        }
    }

    static {
        a = new c();
    }
}
