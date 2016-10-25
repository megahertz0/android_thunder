package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.c.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class ae implements ad {
    private static ae a;
    private final Pattern b;
    private Pattern c;

    ae() {
        this.b = Pattern.compile("((?:m|www)\\.so\\.com[\\S]*/s\\?)|((?:m|www)\\.so\\.com[\\S]*/index[\\.\\w]*\\?a=index)");
        this.c = Pattern.compile("(?:\\?|&)q=([^&]*)");
    }

    public static ae b() {
        if (a == null) {
            a = new ae();
        }
        return a;
    }

    public static boolean e(String str) {
        return !TextUtils.isEmpty(str) && (str.contains(".so.com/jump?") || str.contains(".so.com/index.php?a=newTranscode"));
    }

    private static boolean g(String str) {
        return !TextUtils.isEmpty(str) && str.contains(".so.com/jump?");
    }

    private static boolean h(String str) {
        return !TextUtils.isEmpty(str) && str.contains(".so.com/index.php?a=newTranscode");
    }

    private static String i(String str) {
        if (!g(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("(?:\\?|&)(u)=([^&]*)").matcher(str);
        if (!matcher.find()) {
            return null;
        }
        try {
            MatchResult toMatchResult = matcher.toMatchResult();
            return b.h(str.substring(toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG), toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String j(String str) {
        if (!h(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("(?:\\?|&)(u)=([^&]*)").matcher(str);
        if (!matcher.find()) {
            return null;
        }
        try {
            MatchResult toMatchResult = matcher.toMatchResult();
            return b.h(str.substring(toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG), toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String f(String str) {
        if (!g(str)) {
            return h(str) ? j(str) : str;
        } else {
            String i = i(str);
            if (h(i)) {
                str = j(i);
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
            return i;
        }
    }

    public final int a() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    public final boolean a(String str) {
        return !TextUtils.isEmpty(str) && this.b.matcher(str).find();
    }

    public final String b(String str) {
        if (TextUtils.isEmpty(str) || !a(str)) {
            return null;
        }
        Matcher matcher = this.c.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        try {
            MatchResult toMatchResult = matcher.toMatchResult();
            String g = b.g(str.substring(toMatchResult.start(1), toMatchResult.end(1)));
            return !TextUtils.isEmpty(g) ? g.replace('+', ' ') : g;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String c(String str) {
        String b = b(str);
        return !TextUtils.isEmpty(b) ? b.split("[\\s\\+]")[0] : b;
    }

    public final ArrayList<String> d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Collection hashSet = new HashSet();
        try {
            Matcher matcher = Pattern.compile("class=\"alink\"[\\s]*?href=\"([^\"]*)\"").matcher(str);
            int i = 0;
            while (matcher.find(i)) {
                MatchResult toMatchResult = matcher.toMatchResult();
                int end = toMatchResult.end() + 1;
                int start = toMatchResult.start(1);
                i = toMatchResult.end(1);
                if (start > i || start < 0 || i < 0) {
                    start = toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG);
                    i = toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG);
                }
                if (i >= start && start >= 0) {
                    Object replace = str.substring(start, i).replace("&amp;", "&");
                    if (replace.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        replace = new StringBuilder("http://m.so.com").append(replace).toString();
                    } else if (replace.startsWith("./")) {
                        replace = new StringBuilder("http://m.so.com").append(replace.substring(1)).toString();
                    }
                    hashSet.add(replace);
                }
                i = end;
            }
            return new ArrayList(hashSet);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList(hashSet);
        }
    }
}
