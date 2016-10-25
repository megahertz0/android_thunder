package anet.channel.util;

import android.text.TextUtils;
import anet.channel.security.ISecurity;
import com.alipay.sdk.cons.b;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: Taobao
public class StringUtils {
    private static final char[] DIGITS_LOWER;

    public static String[] parseURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("//")) {
            str = new StringBuilder("http:").append(str).toString();
        }
        int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (indexOf == -1) {
            return null;
        }
        String[] strArr = new String[2];
        String substring = str.substring(0, indexOf);
        if (!HttpConstant.HTTP.equalsIgnoreCase(substring) && !b.a.equalsIgnoreCase(substring)) {
            return null;
        }
        strArr[0] = substring;
        int length = str.length();
        int i = indexOf + 3;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '/' && charAt != ':' && charAt != '?' && charAt != '#') {
                i++;
            }
            strArr[1] = str.substring(indexOf + 3, i);
            return strArr;
        }
        if (i != length) {
            return null;
        }
        strArr[1] = str.substring(indexOf + 3);
        return strArr;
    }

    public static String encodeQueryParams(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        try {
            for (Entry entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str)).append("=").append(URLEncoder.encode(stringNull2Empty((String) entry.getValue()), str).replace(SocializeConstants.OP_DIVIDER_PLUS, "%20")).append(com.alipay.sdk.sys.a.b);
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } catch (Throwable e) {
            ALog.e("Request", "format params failed", null, e, new Object[0]);
        }
        return stringBuilder.toString();
    }

    public static String buildString(String... strArr) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            i2 += strArr[i3] == null ? 0 : strArr[i3].length();
        }
        StringBuilder stringBuilder = new StringBuilder(i2);
        while (i < strArr.length) {
            stringBuilder.append(strArr[i]);
            i++;
        }
        return stringBuilder.toString();
    }

    public static String stringNull2Empty(String str) {
        return str == null ? a.d : str;
    }

    public static String md5ToHex(String str) {
        if (str == null) {
            return null;
        }
        try {
            return bytesToHexString(MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static {
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    private static String bytesToHexString(byte[] bArr, char[] cArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return new String(cArr2);
    }

    public static String bytesToHexString(byte[] bArr) {
        return bArr == null ? a.d : bytesToHexString(bArr, DIGITS_LOWER);
    }

    public static String longToIP(long j) {
        StringBuilder stringBuilder = new StringBuilder(16);
        long j2 = 1000000000;
        do {
            stringBuilder.append(j / j2);
            stringBuilder.append('.');
            j %= j2;
            j2 /= 1000;
        } while (j2 > 0);
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public static String[] splitString(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (!isBlank(str2)) {
            return str.split(str2);
        }
        return new String[]{str2};
    }

    @Deprecated
    public static String concatStr2LowerCase(String str, String... strArr) {
        return a.d;
    }
}
