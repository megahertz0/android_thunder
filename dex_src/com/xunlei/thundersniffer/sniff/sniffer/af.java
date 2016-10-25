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
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class af implements ad {
    private static af b;
    protected final Pattern a;

    af() {
        this.a = Pattern.compile("(?:(m|www)\\.baidu\\.com[\\S]*?(/s\\?|/#|/src_))|(?:(m|www)\\.baidu\\.com[\\S]*?(/\\?[\\S]*#\\|src_[^\\|]+\\|))");
    }

    public static af b() {
        if (b == null) {
            b = new af();
        }
        return b;
    }

    public final int a() {
        return 1;
    }

    public final boolean a(String str) {
        return !TextUtils.isEmpty(str) ? this.a.matcher(str).find() : false;
    }

    public final String b(String str) {
        if (TextUtils.isEmpty(str) || !a(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("(\\?|&)(wd|word)=([^&]*)").matcher(str);
        if (matcher.find()) {
            try {
                MatchResult toMatchResult = matcher.toMatchResult();
                String g = b.g(str.substring(toMatchResult.start(MqttConnectOptions.MQTT_VERSION_3_1), toMatchResult.end(MqttConnectOptions.MQTT_VERSION_3_1)));
                return !TextUtils.isEmpty(g) ? g.replace('+', ' ') : g;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        matcher = Pattern.compile("#\\|src_([^\\|]+)\\|").matcher(str);
        if (!matcher.find()) {
            return null;
        }
        try {
            toMatchResult = matcher.toMatchResult();
            g = b.g(str.substring(toMatchResult.start(1), toMatchResult.end(1)));
            return !TextUtils.isEmpty(g) ? g.replace('+', ' ') : g;
        } catch (Exception e2) {
            e2.printStackTrace();
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
            Matcher matcher = Pattern.compile("class=\"result_title\"[\\s]*?href=\"([^\"]*)\".*?class=\"site\">[^<>]*</span|class=\"result\\s+[A-Za-z0-9\\-_\\s]+\".*?href=\"([^\"]*)\"").matcher(str);
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
                        replace = new StringBuilder("http://m.baidu.com").append(replace).toString();
                    } else if (replace.startsWith("./")) {
                        replace = new StringBuilder("http://m.baidu.com").append(replace.substring(1)).toString();
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
