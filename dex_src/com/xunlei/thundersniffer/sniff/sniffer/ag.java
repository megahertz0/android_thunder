package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.c.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class ag implements ad {
    private static ag a;
    private final Pattern b;
    private Pattern c;

    static {
        a = new ag();
    }

    public static ag b() {
        return a;
    }

    private ag() {
        this.b = Pattern.compile("(?:m|www)\\.sm\\.cn[\\S]*/s\\?");
        this.c = Pattern.compile("(?:\\?|&)q=([^&]*)");
    }

    public final int a() {
        return MqttConnectOptions.MQTT_VERSION_3_1;
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
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Collection hashSet = new HashSet();
        try {
            Matcher matcher = Pattern.compile("<a\\s(?:[^>]+)?class=\"(?:title|sc-title|content)\"(?:[^>]+)?>").matcher(str);
            while (matcher.find(i)) {
                MatchResult toMatchResult = matcher.toMatchResult();
                int end = toMatchResult.end() + 1;
                int start = toMatchResult.start(0);
                i = toMatchResult.end(0);
                if (i >= start && start >= 0) {
                    Object a = ar.a(str.substring(start, i).replace("&amp;", "&"), Pattern.compile("href=\"([^\">]+)\""));
                    if (!TextUtils.isEmpty(a)) {
                        if (a.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                            a = new StringBuilder("http://m.sm.cn").append(a).toString();
                        } else if (a.startsWith("./")) {
                            a = new StringBuilder("http://m.sm.cn").append(a.substring(1)).toString();
                        }
                        hashSet.add(a);
                    }
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
