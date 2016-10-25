package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.umeng.message.proguard.j;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.c.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class ResLinkParser {

    public static interface FoundCallback {
        void onFound(String str, int i, String str2);
    }

    static class HtmlParser {
        String a;
        Result b;

        public static interface Callback {
            boolean handleHrefLink(String str, int i, String str2);

            boolean handleVideoLink(String str, int i, String str2);
        }

        public static class Result {
            public List<z> mLinks;

            public Result() {
                this.mLinks = new ArrayList();
            }
        }

        static class a {
            private static a b;
            final Pattern a;

            a() {
                this.a = Pattern.compile("(?:<video\\s[^>]*?src=\"(http://[^\"]+)\"[^>]*?>)|(?:href=\"([^\"]+)\")");
            }

            public static a a() {
                if (b == null) {
                    synchronized (a.class) {
                        if (b == null) {
                            b = new a();
                        }
                    }
                }
                return b;
            }
        }

        public HtmlParser(String str) {
            this.a = str;
        }
    }

    static class a {
        static final String a;
        private static final String b;
        private static final String c;
        private static final String d;
        private static final String e;
        private static Pattern f;
        private static Pattern g;
        private static Pattern h;
        private static Pattern i;
        private static Pattern j;

        static {
            a = ab.a();
            b = new StringBuilder("(?:ftp|https|http)://[^\"'?/\\s]+/[^\"'?\\s]{0,4096}?\\.(?:").append(a).append(")(?:\\?[^\"\\s]+)?").toString();
            c = new StringBuilder("(thunder://[A-Za-z0-9\\+/]+(?:=)*)|(magnet:\\?xt=urn:btih:[A-Za-z0-9]{32,40})|(ed2k://\\|file\\|[^/\\|]+?\\|[^/\\|]+?\\|[^/\\|\"]+?\\|(?:[^\\s/]+\\|)?(?:/?))|(").append(b).append(j.t).toString();
            d = new StringBuilder("\\|file\\|([^\\|]+\\.(?:").append(a).append("))\\|").toString();
            e = new StringBuilder("([^/\\\\]+\\.(?:").append(a).append("))").toString();
            g = Pattern.compile(c, SimpleLog.LOG_LEVEL_DEBUG);
            h = Pattern.compile("(thunder://[A-Za-z0-9\\+/]+(?:=)*)|(magnet:\\?xt=urn:btih:[A-Za-z0-9]{32,40})|(ed2k://\\|file\\|[^/\\|]+?\\|[^/\\|]+?\\|[^/\\|\"]+?\\|(?:[^\\s/]+\\|)?(?:/?))|((?:ftp|https|http)://[^\"'?/\\s]+/[^\"'?]{0,4096}?(?:\\?[^\"\\s]+)?)", SimpleLog.LOG_LEVEL_DEBUG);
        }

        public static Pattern a() {
            if (i == null) {
                i = Pattern.compile(d, SimpleLog.LOG_LEVEL_DEBUG);
            }
            return i;
        }

        public static Pattern b() {
            if (j == null) {
                j = Pattern.compile(e, SimpleLog.LOG_LEVEL_DEBUG);
            }
            return j;
        }

        public static Pattern c() {
            if (f == null) {
                f = Pattern.compile("ed2k:(?:[^|]*)(\\|file\\|[^/\\|]+?\\|[^/\\|]+?\\|[^/\\|\"]+?\\|(?:[^\\s/]+\\|)?(?:/?))", SimpleLog.LOG_LEVEL_DEBUG);
            }
            return f;
        }

        public static Pattern d() {
            if (g == null) {
                g = Pattern.compile(c, SimpleLog.LOG_LEVEL_DEBUG);
            }
            return g;
        }

        public static Pattern e() {
            if (h == null) {
                h = Pattern.compile("(thunder://[A-Za-z0-9\\+/]+(?:=)*)|(magnet:\\?xt=urn:btih:[A-Za-z0-9]{32,40})|(ed2k://\\|file\\|[^/\\|]+?\\|[^/\\|]+?\\|[^/\\|\"]+?\\|(?:[^\\s/]+\\|)?(?:/?))|((?:ftp|https|http)://[^\"'?/\\s]+/[^\"'?]{0,4096}?(?:\\?[^\"\\s]+)?)", SimpleLog.LOG_LEVEL_DEBUG);
            }
            return h;
        }
    }

    private static void a(String str, FoundCallback foundCallback) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                Matcher matcher = a.d().matcher(str);
                while (matcher.find(i)) {
                    MatchResult toMatchResult = matcher.toMatchResult();
                    int end = toMatchResult.end() + 1;
                    int start = toMatchResult.start();
                    String substring = str.substring(start, toMatchResult.end());
                    String trim = substring.replace("&amp;", "&").trim();
                    int indexOf = trim.indexOf(":");
                    if (indexOf != -1) {
                        trim = trim.substring(0, indexOf).toLowerCase() + trim.substring(indexOf);
                    }
                    foundCallback.onFound(trim, start, substring);
                    i = end;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Matcher matcher = a.d().matcher(str);
            return matcher.matches() && matcher.start() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Matcher matcher = a.e().matcher(str);
            return matcher.find() && matcher.start() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        CharSequence replaceAll = str.trim().replaceAll(" ", "%20");
        if (TextUtils.isEmpty(replaceAll)) {
            return null;
        }
        try {
            Matcher matcher;
            CharSequence toString;
            if (replaceAll.contains("ed2k:")) {
                matcher = a.c().matcher(replaceAll);
                if (matcher.find()) {
                    toString = new StringBuilder("ed2k://").append(matcher.group(1)).toString();
                    if (!TextUtils.isEmpty(toString)) {
                        return toString;
                    }
                    matcher = a.d().matcher(replaceAll);
                    return matcher.find() ? matcher.group() : null;
                }
            }
            toString = null;
            if (!TextUtils.isEmpty(toString)) {
                return toString;
            }
            matcher = a.d().matcher(replaceAll);
            if (matcher.find()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static aa a(String str, String str2) {
        aa aaVar = new aa();
        try {
            HtmlParser htmlParser = new HtmlParser(str);
            Callback aVar = new a();
            htmlParser.b = new Result();
            if (!TextUtils.isEmpty(str2)) {
                HashSet hashSet = new HashSet();
                Matcher matcher = a.a().a.matcher(str2);
                int i = 0;
                while (matcher.find(i)) {
                    String substring;
                    MatchResult toMatchResult = matcher.toMatchResult();
                    i = toMatchResult.end() + 1;
                    int start = toMatchResult.start(1);
                    int end = toMatchResult.end(1);
                    if (start < end && start >= 0) {
                        substring = str2.substring(start, end);
                        String trim = substring.replace("&amp;", "&").trim();
                        if (!hashSet.contains(trim)) {
                            hashSet.add(trim);
                            if (aVar.handleVideoLink(trim, start, substring)) {
                                z zVar = new z(trim);
                                zVar.c = WeiXinShareContent.TYPE_VIDEO;
                                zVar.d = start;
                                zVar.e = substring;
                                htmlParser.b.mLinks.add(zVar);
                            }
                        }
                    }
                    start = toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG);
                    int end2 = toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG);
                    if (start < end2 && start >= 0) {
                        String substring2 = str2.substring(start, end2);
                        substring = substring2.replace("&amp;", "&").trim().replaceAll(" ", "%20");
                        if (aVar.handleHrefLink(substring, start, substring2)) {
                            z zVar2 = new z(substring);
                            zVar2.c = "a";
                            zVar2.d = start;
                            zVar2.e = substring2;
                            htmlParser.b.mLinks.add(zVar2);
                        }
                    }
                }
            }
            Result result = htmlParser.b;
            if (!(result == null || result.mLinks == null)) {
                for (z zVar3 : result.mLinks) {
                    if (!WeiXinShareContent.TYPE_VIDEO.equals(zVar3.c)) {
                        aaVar.a(zVar3);
                    } else if (zVar3 != null && !aaVar.b.contains(zVar3)) {
                        aaVar.b.add(zVar3);
                    }
                }
            }
            htmlParser.a = BuildConfig.VERSION_NAME;
            htmlParser.b = null;
            a(str2, new b(aaVar));
            aaVar.a();
        } catch (Exception e) {
            e.printStackTrace();
            aaVar.a();
        }
        return aaVar;
    }

    static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (b.b(str)) {
            str = b.f(str);
            if (str == null) {
                return null;
            }
        }
        int a = b.a(str);
        if (a != 2) {
            MatchResult toMatchResult;
            if (a == 3 || str.startsWith("ed2k:")) {
                Matcher matcher = a.a().matcher(str);
                if (matcher.find()) {
                    toMatchResult = matcher.toMatchResult();
                    try {
                        str = e(b.g(str.substring(toMatchResult.start(1), toMatchResult.end(1))));
                    } catch (Exception e) {
                    }
                }
                str = null;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    Matcher matcher2 = a.b().matcher(str);
                    a = str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                    if (a == -1 || a > str.length()) {
                        a = 0;
                    }
                    try {
                        if (matcher2.find(a)) {
                            toMatchResult = matcher2.toMatchResult();
                            try {
                                str = e(b.g(str.substring(toMatchResult.start(1), toMatchResult.end(1))));
                            } catch (Exception e2) {
                            }
                        }
                        str = null;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                str = null;
            }
        }
        return str;
    }

    static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        str = str.replaceFirst(new StringBuilder("[\\[\u3010][^\\[\\]\u3010\u3011]*[Ww]{3}?\\.[^\\[\\]\u3010\u3011]*[\\]\u3011](?!\\.(").append(a.a).append("))").toString(), BuildConfig.VERSION_NAME).replaceFirst(new StringBuilder("[\\[\u3010][^\\[\\]\u3010\u3011]*\\.([cC][cC]|[cC][oO][mM]|[nN][eE][tT])[^\\[\\]\u3010\u3011]*[\\]\u3011](?!\\.(").append(a.a).append("))").toString(), BuildConfig.VERSION_NAME).replaceAll("[/\\\\]", "_");
        return str.startsWith(".") ? str.substring(1) : str;
    }
}
