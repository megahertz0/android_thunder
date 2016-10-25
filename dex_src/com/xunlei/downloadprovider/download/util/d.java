package com.xunlei.downloadprovider.download.util;

import com.xunlei.downloadprovider.download.util.c.a;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: EpisodeUtil.java
final class d extends a {
    final /* synthetic */ c$b a;

    d(c$b com_xunlei_downloadprovider_download_util_c_b) {
        this.a = com_xunlei_downloadprovider_download_util_c_b;
    }

    public final Pattern a() {
        if (this.b == null) {
            this.b = Pattern.compile(c$b.a[0] + "|" + c$b.a[1] + "|" + c$b.a[2] + "|" + c$b.a[3], SimpleLog.LOG_LEVEL_DEBUG);
        }
        return this.b;
    }

    protected final a a(String str, Matcher matcher) {
        a aVar = new a();
        if (matcher.find()) {
            MatchResult toMatchResult = matcher.toMatchResult();
            int i = 1;
            int i2 = -1;
            while (i < toMatchResult.groupCount()) {
                if (toMatchResult.start(i) <= toMatchResult.end(i) && toMatchResult.start(i) >= 0) {
                    try {
                        i2 = c$b.a(str.substring(toMatchResult.start(i), toMatchResult.end(i)));
                    } catch (NumberFormatException e) {
                        i2 = -1;
                    }
                }
                if (i == 1) {
                    aVar.c = str.substring(toMatchResult.end() - 1, toMatchResult.end());
                }
                i++;
            }
            aVar.b = i2;
            Matcher matcher2 = Pattern.compile("\u7b2c(.+)(?:\u5b63|\u90e8)").matcher(str);
            if (matcher2.find()) {
                aVar.d = str.substring(matcher2.start(), matcher2.end());
            }
        }
        return aVar;
    }
}
