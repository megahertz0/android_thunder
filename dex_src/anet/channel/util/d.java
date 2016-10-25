package anet.channel.util;

import android.text.TextUtils;
import anet.channel.request.Request;
import com.xunlei.tdlive.R;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

// compiled from: Taobao
public class d {
    static final Pattern a;

    static {
        a = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
    }

    public static boolean a(String str) {
        return str == null ? false : a.matcher(str).matches();
    }

    public static Map<String, List<String>> a(Map<String, List<String>> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), new ArrayList((Collection) entry.getValue()));
        }
        return hashMap;
    }

    public static List<String> a(Map<String, List<String>> map, String str) {
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            return null;
        }
        for (Entry entry : map.entrySet()) {
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                return (List) entry.getValue();
            }
        }
        return null;
    }

    public static String b(Map<String, List<String>> map, String str) {
        List a = a(map, str);
        return (a == null || a.isEmpty()) ? null : (String) a.get(0);
    }

    public static boolean a(int i) {
        return i >= 300 && i < 400 && i != 304;
    }

    public static boolean a(Request request, int i, Map<String, List<String>> map) {
        if (request.isRedirectEnable() && request.isRedirectAllow() && a(i)) {
            String b = b(map, HttpConstant.LOCATION);
            if (b != null) {
                if (!b.startsWith(HttpConstant.HTTP)) {
                    if (b.startsWith("//")) {
                        b = new StringBuilder("http:").append(b).toString();
                    } else {
                        b = null;
                    }
                }
                request.redirectToUrl(b);
                return true;
            }
        }
        return false;
    }

    public static boolean b(Map<String, List<String>> map) {
        try {
            if (HttpConstant.GZIP.equalsIgnoreCase(b(map, HttpConstant.CONTENT_ENCODING))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static int c(Map<String, List<String>> map) {
        try {
            return Integer.parseInt(b(map, HttpConstant.CONTENT_LENGTH));
        } catch (Exception e) {
            return 0;
        }
    }

    public static String a(URL url) {
        if (url == null) {
            return null;
        }
        try {
            String path = url.getPath();
            int length = path.length();
            if (length <= 1) {
                return null;
            }
            int lastIndexOf = path.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
            if (lastIndexOf == -1 || lastIndexOf == length - 1) {
                return null;
            }
            int lastIndexOf2 = path.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            return (lastIndexOf2 == -1 || lastIndexOf2 <= lastIndexOf) ? null : path.substring(lastIndexOf2 + 1, length);
        } catch (Exception e) {
            return null;
        }
    }
}
