package com.xunlei.downloadprovider.util.c;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

// compiled from: UrlHelper.java
public abstract class a {
    public static final String[] a;
    private static final String b;

    static {
        b = a.class.getSimpleName();
        a = new String[]{"^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$", "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"};
    }

    public static String a(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        Header[] headers = httpResponse.getHeaders(HttpConstant.LOCATION);
        if (headers == null || headers.length == 0) {
            headers = httpResponse.getHeaders(ShareActivity.KEY_LOCATION);
        }
        return (headers == null || headers.length <= 0) ? null : headers[0].getValue();
    }

    public static String a(String str) {
        try {
            String e = e(str);
            Pattern compile = Pattern.compile("(mxlagnet:\\?\\w+.*)|(exld2k://\\|\\w+.*)|(txlhunder://[.-_/?&:=+a-z0-9]+)|(hxlttps?://\\w+.*)|(fxltp://[-a-zA-Z0-9+&@#%?=~_|!:,.;]+/.+)", XZBDevice.DOWNLOAD_LIST_RECYCLE);
            Pattern compile2 = Pattern.compile("(magnet:\\?\\w+.*)|(ed2k://\\|\\w+.*)|(thunder://[.-_/?&:=+a-z0-9]+)|(https?://\\w+.*)|(ftp://[-a-zA-Z0-9+&@#%?=~_|!:,.;]+/.+)", XZBDevice.DOWNLOAD_LIST_RECYCLE);
            Matcher matcher = compile.matcher(e);
            Matcher matcher2 = compile2.matcher(e);
            if (!matcher.find()) {
                return matcher2.find() ? matcher2.group() : e;
            } else {
                e = matcher.group();
                if (TextUtils.isEmpty(e)) {
                    return e;
                }
                if (e.startsWith("mxlagnet")) {
                    e = e.replaceFirst("mxlagnet", "magnet");
                } else if (e.startsWith("hxlttp")) {
                    e = e.replaceFirst("hxlttp", HttpConstant.HTTP);
                } else if (e.startsWith("hxlttps")) {
                    e = e.replaceFirst("hxlttps", b.a);
                } else if (e.startsWith("fxltp")) {
                    e = e.replaceFirst("fxltp", "ftp");
                } else if (e.startsWith("exld2k")) {
                    e = e.replaceFirst("exld2k", "ed2k");
                } else if (e.startsWith("txlhunder")) {
                    e = e.replaceFirst("txlhunder", "thunder");
                }
                return e.endsWith(h.b) ? e.substring(0, e.lastIndexOf(h.b)) : e;
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int b(String str) {
        String a = a(str);
        if (!(a == null || str.equals(a))) {
            str = a;
        }
        if (str.startsWith("ftp://") || str.startsWith("thunder://") || str.startsWith("ed2k://") || str.startsWith("magnet:?")) {
            return (str.endsWith("html") || str.endsWith("HTML") || str.endsWith("htm") || str.endsWith("HTM")) ? 2 : 1;
        } else {
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            }
            if (!EFileCategoryType.E_OTHER_CATEGORY.equals(XLFileTypeUtil.a(str))) {
                return 1;
            }
            int i;
            boolean e = com.xunlei.downloadprovider.url.b.e(str);
            boolean f = com.xunlei.downloadprovider.url.b.f(str);
            boolean d = com.xunlei.downloadprovider.url.b.d(str);
            boolean b = com.xunlei.downloadprovider.url.b.b(str);
            boolean a2 = com.xunlei.downloadprovider.url.b.a(str);
            boolean g = com.xunlei.downloadprovider.url.b.g(str);
            if (e || f || d || b || a2 || g) {
                i = 1;
            } else {
                i = 2;
            }
            return i;
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new StringBuilder("magnet:?xt=urn:btih:").append(str.substring(str.lastIndexOf("/") + 1, str.length())).toString().toString();
    }

    public static String d(String str) throws UnsupportedEncodingException {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = str;
        } else {
            str2 = str.trim();
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        try {
            return URLDecoder.decode(str2, GameManager.DEFAULT_CHARSET);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String e(String str) throws UnsupportedEncodingException {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = str;
        } else {
            str2 = str.trim();
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        String decode;
        boolean z;
        boolean contains = str2.contains(SocializeConstants.OP_DIVIDER_PLUS);
        try {
            Object obj;
            decode = URLDecoder.decode(str2, GameManager.DEFAULT_CHARSET);
            for (int i = 0; i < decode.length(); i++) {
                if (decode.charAt(i) == '\ufffd') {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                str2 = URLDecoder.decode(str2, "gb2312");
            } else {
                str2 = decode;
            }
            decode = str2;
            z = contains;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            decode = str2;
            Object obj2 = null;
        }
        return (TextUtils.isEmpty(decode) || !z) ? decode : decode.replaceAll(" ", SocializeConstants.OP_DIVIDER_PLUS);
    }

    public static boolean f(String str) {
        if (str != null) {
            CharSequence trim = str.trim();
            if (trim == null || trim.isEmpty()) {
                return false;
            }
            if (Pattern.compile("^(http|https|ftp:\\/\\/)").matcher(trim).find() || Pattern.compile("^(www)\\.[\\w\\d_]+").matcher(trim).find() || Pattern.compile("[^\u4e00-\u9fa5\\s]+\\.(aero|biz|cc|cn|com|coop|edu|gov|html|idv|info|int|jp|mil|mobi|museum|name|net|org|pro)(/)?").matcher(trim).find() || Pattern.compile("^\\d+\\.\\d+\\.\\d+\\.\\d+").matcher(trim).find()) {
                return true;
            }
        }
        return false;
    }

    public static String g(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://") || str.startsWith("ftp://")) ? str : new StringBuilder("http://").append(str).toString();
    }
}
